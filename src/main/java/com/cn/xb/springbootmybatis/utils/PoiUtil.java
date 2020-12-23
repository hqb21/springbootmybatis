package com.cn.xb.springbootmybatis.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class PoiUtil {
    public static void main(String[] args) throws IOException {
        List<String[]> list = new ArrayList<>();
        String[] arr = {"部门", "云速"};
        String[] arr1 = {"总计", "10000000"};
        String[] arr2 = {"单位", "元"};
        list.add(arr);
        list.add(arr1);
        list.add(arr2);
        XSSFWorkbook workbook = new XSSFWorkbook();
        workbook = workbookList(workbook, "come", list);

        String[] arra0 = {"name", "名称", "1", "1"};
        String[] arra1 = {"Person", "人类", "1", "2"};
        String[] arrb1 = {"Amions", "动物", "1", "1"};
        List<String[]> lista = new ArrayList<>();
        List<List<String[]>> titles = new ArrayList<>();
        lista.add(arra0);
        lista.add(arra1);
        lista.add(arrb1);
        titles.add(lista);
        String[] arr11 = {"", "", "2", "1"};
        List<String[]> list1 = new ArrayList<>();
        list1.add(arr11);
        String[] arr21 = {"sex", "性别", "1", "1"};
        list1.add(arr21);
        String[] arr31 = {"kaka", "咳咳", "1", "1"};
        list1.add(arr31);
        String[] arr41 = {"kaka", "小米", "1", "1"};
        list1.add(arr41);
        titles.add(list1);

        workbook = workbookinList(workbook, "come",  titles, new ArrayList<>());
        workbook = workbookinList(workbook, "come",  titles, new ArrayList<>());

        String path = "d:/data/2019-02-19-04.xls";
        wirteOutWorkbook(workbook, path);
    }
    /**
     * 将数据写入指定Excel对象中
     *
     * @param workbook  Excel对象
     * @param sheetName sheet名
     * @param titles    标题串
     *                  {{['person','人类',1,2]},
     *                  {['name','名称',1,1],
     *                  ['sex','性别',1,1]}}   【 字段名称，标题，所占行数，所占列数】
     * @param values    内容集
     * @return True\False
     */
    public static XSSFWorkbook workbookinList(XSSFWorkbook workbook, String sheetName, List<List<String[]>> titles, List<Map<String, Object>> values) {
        // 生成一个表格
        Sheet sheet;
        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            if (null == sheetName || "".equals(sheetName)) {
                sheet = workbook.createSheet(); // sheetName 为空则使用默认值
            } else {
                sheet = workbook.createSheet(sheetName);
            }
        }
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 第一行生成账单标题
        Row row = null;
        int rowNumCount = 0 == sheet.getLastRowNum() ? 0 : sheet.getLastRowNum() + 2;//获得总行数
        // 存储标题在Excel文件中的序号
        Map<String, Integer> titleOrder = new HashMap<>();
        for (int j = 0; j < titles.size(); j++) {
            List<String[]> list = titles.get(j);
            row = sheet.createRow(rowNumCount + j);
            int curCol = 0; //当前列数名称
            for (int i = 0; i < list.size(); i++) {
                org.apache.poi.ss.usermodel.Cell cell = row.createCell(curCol);
                String[] title = list.get(i);
                cell.setCellValue(title[1]); // 标题值
                // 需要记录每次的列位置
                int rowNum = Integer.valueOf(title[2]) - 1; // 需要占用的行数
                int colNum = Integer.valueOf(title[3]) - 1; // 需要占用的列数
                if (0 != rowNum || 0 != colNum) {
                    int curRow = rowNumCount + j;
                    CellRangeAddress region = new CellRangeAddress(curRow - rowNum, curRow, curCol, curCol + colNum);
                    sheet.addMergedRegion(region);
                    curCol += Integer.valueOf(title[3]);
                } else {
                    curCol++;
                }
                // 最后一行包含所有列
                if (j == titles.size() - 1) {
                    titleOrder.put(title[0], i);
                }
            }
        }
        /*
         * 写入正文
         */
        Iterator<Map<String, Object>> iterator = values.iterator();
        int index = rowNumCount + 1; // 行号
        while (iterator.hasNext()) {
            index++; // 出去标题行，从第一行开始写
            row = sheet.createRow(index);
            Map<String, Object> value = iterator.next();
            for (Map.Entry<String, Object> map : value.entrySet()) {
                // 获取列名
                String title = map.getKey();
                // 根据列名获取序号
                Integer i = titleOrder.get(title);
                if (i == null) {
                    continue;
                }
                // 在指定序号处创建cell
                Cell cell = row.createCell(i);
                // 获取列的值
                Object object = map.getValue();
                // 判断object的类型
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (object instanceof Double) {
                    cell.setCellValue((Double) object);
                } else if (object instanceof Date) {
                    String time = simpleDateFormat.format((Date) object);
                    cell.setCellValue(time);
                } else if (object instanceof Calendar) {
                    Calendar calendar = (Calendar) object;
                    String time = simpleDateFormat.format(calendar.getTime());
                    cell.setCellValue(time);
                } else if (object instanceof Boolean) {
                    cell.setCellValue((Boolean) object);
                } else {
                    if (object == null) {
                        cell.setCellValue("");
                    } else {
                        cell.setCellValue(object.toString());
                    }
                }
            }
        }
        return workbook;
    }

    /**
     * 将数据写入Excel文件中
     *
     * @param workbook  Excel对象
     * @param sheetName sheet名
     * @param dataList  标题串+内容集 {{'部门','201930'},{'总计','10000万'}}
     * @return True\False
     */
    public static XSSFWorkbook workbookList(XSSFWorkbook workbook, String sheetName, List<String[]> dataList) {
        // 生成一个表格
        Sheet sheet;
        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            if (null == sheetName || "".equals(sheetName)) {
                sheet = workbook.createSheet(); // sheetName 为空则使用默认值
            } else {
                sheet = workbook.createSheet(sheetName);
            }
        }
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 第一行生成账单标题
        Row row = null;
        int rowNumCount = 0 == sheet.getLastRowNum() ? 0 : sheet.getLastRowNum() + 2;//获得总行数
        // 存储标题在Excel文件中的序号
        for (int i = 0; i < dataList.size(); i++) {
            row = sheet.createRow(rowNumCount + i);
            row.createCell(0).setCellValue(dataList.get(i)[0]);
            row.createCell(1).setCellValue(dataList.get(i)[1]);
        }
        return workbook;
    }

    /**
     * 文件写出
     *
     * @param workbook
     * @param path
     * @throws IOException
     */
    public static void wirteOutWorkbook(XSSFWorkbook workbook, String path) throws IOException {
        String dirPath = path.substring(0, path.lastIndexOf('.'));
        File dirFile = new File(dirPath);
        if (!dirFile.exists() && !dirFile.isDirectory()) {
            dirFile.mkdirs();
        }
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        OutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

}

