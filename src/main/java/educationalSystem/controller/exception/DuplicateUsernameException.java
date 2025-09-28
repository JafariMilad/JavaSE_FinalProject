package educationalSystem.controller.exception;

public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException() {
        super("Duplicate Username !!!");
    }
}
