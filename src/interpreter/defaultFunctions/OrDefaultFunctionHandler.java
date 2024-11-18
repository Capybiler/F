package interpreter.defaultFunctions;

import java.util.List;

public class OrDefaultFunctionHandler extends DefaultFunctionHandler {
    public OrDefaultFunctionHandler(List<Object> parameters) {
        super(parameters);
        this.expectedParametersCount = 2;
    }

    @Override
    public Object handle() {
        return (boolean) parameters.get(0) || (boolean) parameters.get(1);
    }
}
