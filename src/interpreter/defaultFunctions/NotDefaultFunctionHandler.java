package interpreter.defaultFunctions;

import java.util.List;

public class NotDefaultFunctionHandler extends DefaultFunctionHandler {
    public NotDefaultFunctionHandler(List<Object> parameters) {
        super(parameters);
        this.expectedParametersCount = 1;
    }

    @Override
    public Object handle() {
        return !(boolean) parameters.getFirst();
    }
}
