package interpreter.defaultFunctions;

import java.util.List;

public class DefaultFunctionMapper {
    public static DefaultFunctionHandler getHandler(String functionName, List<Object> parameters) {
        return switch (functionName) {
            case "plus" -> new PlusDefaultFunctionHandler(parameters);
            case "minus" -> new MinusDefaultFunctionHandler(parameters);
            case "divide" -> new DivideDefaultFunctionHandler(parameters);
            case "times" -> new MultiplyDefaultFunctionHandler(parameters);
            default -> null;
        };
    }
}
