package com.cn.xb.springbootmybatis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @ProjectName: springbootmybatis
 * @Package: com.cn.xb.springbootmybatis.utils
 * @ClassName: FileUtil
 * @Author: huiqb
 * @Description: 文件流相关操作
 * @Date: 2020/12/8 17:01
 * @Version: 1.0
 */
public class FileUtils {
    private static Logger logger= LoggerFactory.getLogger(FileUtils.class);

    /**
     * @Method multipartFileToFile
     * @Author huiqb
     * @Version  1.0
     * @Description //TODO 将MultipartFile流转为File文件流
     * @Param   multipartFile前端传递文件流
     * @Return File
     * @Date 2020/12/8
     */
    public static File multipartFileToFile(MultipartFile multipartFile){
        File file = null;
        try {
            if(multipartFile.equals("")||multipartFile.getSize()<=0){
                return file;
            }else {
                InputStream ins = multipartFile.getInputStream();
                file = new File(multipartFile.getOriginalFilename());
                inputStreamToFile(ins, file);
                ins.close();
            }
            return file;
        }catch (Exception e){
            logger.error("MultipartFile转为File文件流失败：",e);
            return file;
        }
    }
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            logger.error("inputStream转为File文件流失败：",e);
        }
    }
}
