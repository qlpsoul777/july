package com.qlp.core.Exception;

/**
 * 异常细节
 * @author qlp
 *
 */
public interface ErrorDetail {
	
	/**
	 * 错误码
	 * @return
	 */
	public abstract String getCode();
	/**
	 * 错误码描述
	 * @return
	 */
	public abstract String getMsg();
	/**
	 * 判断当前异常是否是系统异常
	 * @return
	 */
	public abstract boolean isSysError();
	
	/**
	 * 系统级异常枚举
	 * @author qlp
	 *
	 */
	public enum SysErrorEnum implements ErrorDetail{
		NULL_EXCEPTION("NullPointerException","空指针异常");
		
		private final String code;
	    private final String msg;
	    
	    SysErrorEnum(String code,String msg){
	    	this.code = code;
	    	this.msg = msg;
	    }
		@Override
		public String getCode() {
			return this.code;
		}

		@Override
		public String getMsg() {
			return this.msg;
		}
		@Override
		public boolean isSysError() {
			return true;
		}
		
	}
	
	/**
	 * 业务级异常枚举
	 * @author qlp
	 *
	 */
	public enum BusiErrorEnum implements ErrorDetail{
		INPUT_NOT_EXIST("NULL","输入参数不能为空"),
		OUTPUT_NOT_FOUND("NOT_FOUND","输出不能为空");
		
		private final String code;
	    private final String msg;
	    
	    BusiErrorEnum(String code,String msg){
	    	this.code = code;
	    	this.msg = msg;
	    }
		@Override
		public String getCode() {
			return this.code;
		}

		@Override
		public String getMsg() {
			return this.msg;
		}
		
		@Override
		public boolean isSysError() {
			return false;
		}
		
	}

}