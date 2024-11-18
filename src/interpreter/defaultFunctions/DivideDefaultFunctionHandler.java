package interpreter.defaultFunctions;

import java.util.List;

public class DivideDefaultFunctionHandler extends DefaultFunctionHandler {
    public DivideDefaultFunctionHandler(List<Object> parameters) {
        super(parameters);
    }

    @Override
    public Object handle() {
        if (parameters.size() != 2) {
            throw new IllegalArgumentException("Divide function should have exactly 2 parameters");
        }

        Object first = parameters.get(0);
        Object second = parameters.get(1);

        try {
            long firstInt = (long) first;
            long secondInt = (long) second;
            if (secondInt == 0) {
                throw new IllegalArgumentException("Divide by zero");
            }
            return firstInt / secondInt;
        } catch (ClassCastException e) {
            try {
                double firstDouble = (double) first;
                double secondDouble = (double) second;
                if (secondDouble == 0) {
                    throw new IllegalArgumentException("Divide by zero");
                }
                return firstDouble / secondDouble;
            } catch (ClassCastException e2) {
                throw new IllegalArgumentException("Divide function should have integer or real parameters");
            }
        }
    }
}
