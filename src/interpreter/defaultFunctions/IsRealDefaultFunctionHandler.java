package interpreter.defaultFunctions;

import java.util.List;

public class IsRealDefaultFunctionHandler extends DefaultFunctionHandler {
    public IsRealDefaultFunctionHandler(List<Object> parameters) {
        super(parameters);
        this.expectedParametersCount = 1;
    }

    @Override
    public Object handle() {
        // TODO: Implement this
        return null;
    }
}
