package vn.locdt.jquestion.exception;

public class UnspecifiedAnswerException extends RuntimeException {
	public UnspecifiedAnswerException(String message) {
		super(message);
	}

	public UnspecifiedAnswerException(String message, Throwable cause) {
		super(message, cause);
	}
}
