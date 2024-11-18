package interpreter.defaultFunctions;

import java.util.List;

public abstract class DefaultFunctionHandler {
    protected final List<Object> parameters;
    protected int expectedParametersCount;

    DefaultFunctionHandler(List<Object> parameters) {
        this.parameters = parameters;
    }

    public Object handleWithCommonChecks() {
        checkParametersCount(expectedParametersCount);
        return handle();
    }

    public abstract Object handle();

    protected double convertBoolIntDoubleToDouble(Object value, String errorMessage) {
        return switch (value) {
            case Boolean b -> (boolean) value ? 1 : 0;
            case Integer i -> (double) (int) value;
            case Double v -> (double) value;
            case null, default -> throw new IllegalArgumentException(errorMessage);
        };
    }

    protected void checkParametersCount(int expectedCount) {
        if (parameters.size() != expectedCount) {
            throw new IllegalArgumentException("Expected " + expectedCount + " parameters, but got " + parameters.size());
        }
    }
}
