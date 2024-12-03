package interpreter.defaultFunctions;

import java.util.List;

public class MultiplyDefaultFunctionHandler extends DefaultFunctionHandler {
    public MultiplyDefaultFunctionHandler(List<Object> parameters) {
        super(parameters);
        this.expectedParametersCount = 2;
    }

    @Override
    public Object handle() {
        Object first = parameters.get(0);
        Object second = parameters.get(1);

        try {
            int firstInt = (int) first;
            int secondInt = (int) second;
            return firstInt * secondInt;
        } catch (ClassCastException e) {
            try {
                double firstDouble = (double) first;
                double secondDouble = (double) second;
                return firstDouble * secondDouble;
            } catch (ClassCastException e2) {
                throw new IllegalArgumentException("Multiply function should have integer or real parameters");
            }
        }
    }
}
