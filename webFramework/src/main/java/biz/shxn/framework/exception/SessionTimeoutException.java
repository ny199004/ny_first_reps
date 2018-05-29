package biz.shxn.framework.exception;

public class SessionTimeoutException extends Exception {

	private static final long serialVersionUID = 1L;

	public SessionTimeoutException() {
		super();
	}
	
	public SessionTimeoutException(String message) {
		super(message);
	}
	
	public SessionTimeoutException(Throwable throwable) {
		super(throwable);
	}
	
	public SessionTimeoutException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}
