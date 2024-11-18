package interpreter.defaultFunctions;

import java.util.List;

public class PlusDefaultFunctionHandler extends DefaultFunctionHandler {
    public PlusDefaultFunctionHandler(List<Object> parameters) {
        super(parameters);
        this.expectedParametersCount = 2;
    }

    @Override
    public Object handle() {
        Object first = parameters.get(0);
        Object second = parameters.get(1);

        try {
            long firstInt = (long) first;
            long secondInt = (long) second;
            return firstInt + secondInt;
        } catch (ClassCastException e) {
            try {
                double firstDouble = (double) first;
                double secondDouble = (double) second;
                return firstDouble + secondDouble;
            } catch (ClassCastException e2) {
                throw new IllegalArgumentException("Plus function should have integer or real parameters");
            }
        }
    }
}
