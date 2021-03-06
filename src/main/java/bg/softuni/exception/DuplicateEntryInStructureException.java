package bg.softuni.exception;

public class DuplicateEntryInStructureException extends RuntimeException {
    private static final long serialVersionUID = 5873179126452259733L;

    private static final String DUPLICATE_ENTRY = "The %s already exists in %s";

    public DuplicateEntryInStructureException(String entryName, String structureName) {
        super(String.format(DUPLICATE_ENTRY, entryName, structureName));
    }

    public DuplicateEntryInStructureException(String message) {
        super(message);
    }
}
