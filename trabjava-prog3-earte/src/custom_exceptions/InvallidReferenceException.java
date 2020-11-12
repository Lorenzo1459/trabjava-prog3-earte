package custom_exceptions;

public class InvallidReferenceException extends RuntimeException{
	public InvallidReferenceException(String msgErro, Throwable e) {
		super(msgErro, e);
	}
}
