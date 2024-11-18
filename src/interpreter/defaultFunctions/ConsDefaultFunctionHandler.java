package interpreter.defaultFunctions;

import java.util.List;

public class ConsDefaultFunctionHandler extends DefaultFunctionHandler {
    public ConsDefaultFunctionHandler(List<Object> parameters) {
        super(parameters);
        this.expectedParametersCount = 2;
    }

    @Override
    public Object handle() {
        // TODO: Implement this
        return null;
    }
}
