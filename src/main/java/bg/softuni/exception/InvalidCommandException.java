package bg.softuni.exception;

public class InvalidCommandException extends RuntimeException {
    private static final long serialVersionUID = -86360165981010101L;
    private static final String INVALID_COMMAND = "The command '%s' is invalid";

    public InvalidCommandException(String command) {
        super(String.format(INVALID_COMMAND, command));
    }
}
