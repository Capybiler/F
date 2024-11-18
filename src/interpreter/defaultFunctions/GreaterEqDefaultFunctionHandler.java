package interpreter.defaultFunctions;

import java.util.List;

public class GreaterEqDefaultFunctionHandler extends DefaultFunctionHandler {
    public GreaterEqDefaultFunctionHandler(List<Object> parameters) {
        super(parameters);
        this.expectedParametersCount = 2;
    }

    @Override
    public Object handle() {
        String errorMessage = "Greater Eq function only accepts boolean, integer, or double values.";
        double value1 = convertBoolIntDoubleToDouble(parameters.get(0), errorMessage);
        double value2 = convertBoolIntDoubleToDouble(parameters.get(1), errorMessage);
        return value1 >= value2;
    }
}
