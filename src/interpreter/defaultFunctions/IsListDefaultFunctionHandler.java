package interpreter.defaultFunctions;

import java.util.List;

public class IsListDefaultFunctionHandler extends DefaultFunctionHandler {
    public IsListDefaultFunctionHandler(List<Object> parameters) {
        super(parameters);
        this.expectedParametersCount = 1;
    }

    @Override
    public Object handle() {
        // TODO: Implement this
        return null;
    }
}