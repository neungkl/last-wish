package exception;

@SuppressWarnings("serial")
public class ResourceException extends RuntimeException {

	public static final int FILE_NOT_FOUND = 0;
	public static final int EXTENSION_INCORRECT = 1;
	public static final int KEY_NOT_FOUND = 2;
	
	private int errorType;
	private String message;
	
	public ResourceException(int type) {
		this(type, "");
	}
	
	public ResourceException(int type, String message) {
		this.errorType = type;
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		if(errorType == FILE_NOT_FOUND) return "file not found " + message;
		if(errorType == EXTENSION_INCORRECT) return "file extension ." + message + " incorrect";
		if(errorType == KEY_NOT_FOUND) return message + " key not found";
		return "wrong format exception";
	}
}
