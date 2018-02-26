package Exception;

public class NotFoundException extends Exception{
	
	private static final long serialVersionUID = -683615199613899314L;
	
	private static final String MSG = "No se han encontrado elementos";

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException() {
		super(MSG);
	}
}