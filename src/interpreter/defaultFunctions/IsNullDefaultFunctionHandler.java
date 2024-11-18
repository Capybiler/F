package interpreter.defaultFunctions;

import java.util.List;

public class IsNullDefaultFunctionHandler extends DefaultFunctionHandler {
    public IsNullDefaultFunctionHandler(List<Object> parameters) {
        super(parameters);
        this.expectedParametersCount = 1;
    }

    @Override
    public Object handle() {
        return this.parameters.getFirst() == null;
    }
}
