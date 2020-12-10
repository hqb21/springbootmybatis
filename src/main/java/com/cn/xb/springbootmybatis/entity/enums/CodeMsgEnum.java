package com.cn.xb.springbootmybatis.entity.enums;

public class CodeMsgEnum {

    private int code;
	private String msg;
	
	//通用的错误码
	public static final CodeMsgEnum SUCCESS = new CodeMsgEnum(0, "success");
	public static final CodeMsgEnum SERVER_ERROR = new CodeMsgEnum(1, "服务端异常");
	public static final CodeMsgEnum UNDEFINED_CODE_ERROR = new CodeMsgEnum(1, "%s");
	public static final CodeMsgEnum NEED_LOGIN = new CodeMsgEnum(10, "请重新登录");
	public static final CodeMsgEnum BIND_ERROR = new CodeMsgEnum(500101, "参数校验异常：%s");
	public static final CodeMsgEnum REQUEST_ILLEGAL = new CodeMsgEnum(500102, "无权访问该资源");
	public static final CodeMsgEnum ACCESS_LIMIT_REACHED= new CodeMsgEnum(500104, "访问太频繁！");
	
	private CodeMsgEnum( ) {
	}
			
	private CodeMsgEnum(int code, String msg ) {
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	
	public CodeMsgEnum fillArgs(Object... args) {
		String message = String.format(this.msg, args);
		return new CodeMsgEnum(this.code, message);
	}

	@Override
	public String toString() {
		return "CodeMsg [code=" + code + ", msg=" + msg + "]";
	}
	
	
}
