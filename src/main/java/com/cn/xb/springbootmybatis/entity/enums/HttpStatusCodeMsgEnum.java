package com.cn.xb.springbootmybatis.entity.enums;

public class HttpStatusCodeMsgEnum {

    private int code;
	private String msg;
	
	//通用的错误码
	public static final HttpStatusCodeMsgEnum SUCCESS = new HttpStatusCodeMsgEnum(200, "success");
	public static final HttpStatusCodeMsgEnum SERVER_ERROR = new HttpStatusCodeMsgEnum(1, "服务端异常");
	public static final HttpStatusCodeMsgEnum UNDEFINED_CODE_ERROR = new HttpStatusCodeMsgEnum(1, "%s");
	public static final HttpStatusCodeMsgEnum NEED_LOGIN = new HttpStatusCodeMsgEnum(10, "请重新登录");
	public static final HttpStatusCodeMsgEnum BIND_ERROR = new HttpStatusCodeMsgEnum(500101, "参数校验异常：%s");
	public static final HttpStatusCodeMsgEnum REQUEST_ILLEGAL = new HttpStatusCodeMsgEnum(500102, "无权访问该资源");
	public static final HttpStatusCodeMsgEnum ACCESS_LIMIT_REACHED= new HttpStatusCodeMsgEnum(500104, "访问太频繁！");
	
	private HttpStatusCodeMsgEnum( ) {
	}
			
	private HttpStatusCodeMsgEnum(int code, String msg ) {
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	
	public HttpStatusCodeMsgEnum fillArgs(Object... args) {
		String message = String.format(this.msg, args);
		return new HttpStatusCodeMsgEnum(this.code, message);
	}

	@Override
	public String toString() {
		return "CodeMsg [code=" + code + ", msg=" + msg + "]";
	}
	
	
}
