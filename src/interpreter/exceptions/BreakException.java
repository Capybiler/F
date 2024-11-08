package interpreter.exceptions;

public class BreakException extends RuntimeException {
    public BreakException() {
        super("Break statement");
    }
}
