package interpreter.defaultFunctions;

import java.util.List;

public class MinusDefaultFunctionHandler extends DefaultFunctionHandler {
    public MinusDefaultFunctionHandler(List<Object> parameters) {
        super(parameters);
    }

    @Override
    public Object handle() {
        if (parameters.size() != 2) {
            throw new IllegalArgumentException("Minus function should have exactly 2 parameters");
        }

        Object first = parameters.get(0);
        Object second = parameters.get(1);

        try {
            long firstInt = (long) first;
            long secondInt = (long) second;
            return firstInt - secondInt;
        } catch (ClassCastException e) {
            try {
                double firstDouble = (double) first;
                double secondDouble = (double) second;
                return firstDouble - secondDouble;
            } catch (ClassCastException e2) {
                throw new IllegalArgumentException("Minus function should have integer or real parameters");
            }
        }
    }
}
