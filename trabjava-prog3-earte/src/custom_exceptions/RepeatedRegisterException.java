package custom_exceptions;

public class RepeatedRegisterException extends RuntimeException{
	public RepeatedRegisterException(String msgErro, Throwable e) {
		super(msgErro, e);
	}
}
