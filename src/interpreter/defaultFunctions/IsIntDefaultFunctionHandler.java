package interpreter.defaultFunctions;

import java.util.List;

public class IsIntDefaultFunctionHandler extends DefaultFunctionHandler {
    public IsIntDefaultFunctionHandler(List<Object> parameters) {
        super(parameters);
        this.expectedParametersCount = 1;
    }

    @Override
    public Object handle() {
        return this.parameters.getFirst() instanceof Integer;
    }
}
