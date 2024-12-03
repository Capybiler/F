package interpreter.defaultFunctions;

import java.util.List;

public class IsBoolDefaultFunctionHandler extends DefaultFunctionHandler {
    public IsBoolDefaultFunctionHandler(List<Object> parameters) {
        super(parameters);
        this.expectedParametersCount = 1;
    }

    @Override
    public Object handle() {
        return this.parameters.getFirst() instanceof Boolean;
    }
}
