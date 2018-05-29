package biz.shxn.framework.exception;

import biz.shxn.framework.util.UtilMessage;

/**
 * @author wangjf
 * @version 1.0, 2017/04/24
*/
public class CheckedException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errorCode = null;
	private Object[] errorParam = null;

//	public CheckedException(String errorCode) {
//		this.errorCode = errorCode;
//	}
	
	public CheckedException(String message)
	{
		super(message);
	}

	public CheckedException(String errorCode, Object[] errorParam) {
		this.errorCode = errorCode;
		this.errorParam = errorParam;
	}

	public CheckedException(String errorCode, Object[] errorParam, Throwable t) {
		super(t);
		this.errorCode = errorCode;
		this.errorParam = errorParam;
	}

	//	public UncheckedException(String message) {
	//		super(message);
	//	}

	public CheckedException(String message, Throwable t) {
		super(message, t);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Object[] getErrorParam() {
		return errorParam;
	}

	public String getMessage4ErrorCode() {
		String result = null;
		if (getErrorCode() != null && getErrorCode().trim().length() > 0) {
			result = UtilMessage.getMessage(getErrorCode(), getErrorParam());
		} else {
			result = super.getMessage();
		}
		return result;
	}

	public String getMessage() {
		if(getErrorCode()!=null){
			return getMessage4ErrorCode();//local
		}else{
			return super.getMessage();
		}
	}
	
}
