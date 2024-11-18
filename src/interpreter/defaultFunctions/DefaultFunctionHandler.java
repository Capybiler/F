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

    protected void checkParametersCount(int expectedCount) {
        if (parameters.size() != expectedCount) {
            throw new IllegalArgumentException("Expected " + expectedCount + " parameters, but got " + parameters.size());
        }
    }
}
