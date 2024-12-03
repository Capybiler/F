package interpreter.defaultFunctions;

import java.util.List;

public class EqualDefaultFunctionHandler extends DefaultFunctionHandler {
    public EqualDefaultFunctionHandler(List<Object> parameters) {
        super(parameters);
        this.expectedParametersCount = 2;
    }

    @Override
    public Object handle() {
        String errorMessage = "Equal function only accepts boolean, integer, or double values.";
        double value1 = convertBoolIntDoubleToDouble(parameters.get(0), errorMessage);
        double value2 = convertBoolIntDoubleToDouble(parameters.get(1), errorMessage);
        return value1 == value2;
    }
}
