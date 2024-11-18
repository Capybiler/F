package interpreter.defaultFunctions;

import java.util.List;

public class XorDefaultFunctionHandler extends DefaultFunctionHandler {
    public XorDefaultFunctionHandler(List<Object> parameters) {
        super(parameters);
        this.expectedParametersCount = 2;
    }

    @Override
    public Object handle() {
        return (boolean) parameters.get(0) ^ (boolean) parameters.get(1);
    }
}
