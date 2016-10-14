package exception;

public class DomainException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -5565646063739195919L;

	public DomainException() {

	}

	public DomainException(String message) {
		super(message);
	}

}
