package exception;

public class ResourceException extends RuntimeException {

	private static final long serialVersionUID = 3593891638366707588L;

	public ResourceException(String exception) {
		super(exception);
	}
}
