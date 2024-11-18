package interpreter.defaultFunctions;

import java.util.List;

public abstract class DefaultFunctionHandler {
    protected final List<Object> parameters;

    DefaultFunctionHandler(List<Object> parameters) {
        this.parameters = parameters;
    }

    public abstract Object handle();
}
