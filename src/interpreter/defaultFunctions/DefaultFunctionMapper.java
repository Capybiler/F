package interpreter.defaultFunctions;

import java.util.List;

public class DefaultFunctionMapper {
    public static DefaultFunctionHandler getHandler(String functionName, List<Object> parameters) {
        return switch (functionName) {
            case "plus" -> new PlusDefaultFunctionHandler(parameters);
            case "minus" -> new MinusDefaultFunctionHandler(parameters);
            case "times" -> new MultiplyDefaultFunctionHandler(parameters);
            case "divide" -> new DivideDefaultFunctionHandler(parameters);

            case "head" -> new HeadDefaultFunctionHandler(parameters);
            case "tail" -> new TailDefaultFunctionHandler(parameters);
            case "cons" -> new ConsDefaultFunctionHandler(parameters);

            case "equal" -> new EqualDefaultFunctionHandler(parameters);
            case "nonequal" -> new NonEqualDefaultFunctionHandler(parameters);
            case "less" -> new LessDefaultFunctionHandler(parameters);
            case "lessEq" -> new LessEqDefaultFunctionHandler(parameters);
            case "greater" -> new GreaterDefaultFunctionHandler(parameters);
            case "greaterEq" -> new GreaterEqDefaultFunctionHandler(parameters);

            case "isint" -> new IsIntDefaultFunctionHandler(parameters);
            case "isreal" -> new IsRealDefaultFunctionHandler(parameters);
            case "isbool" -> new IsBoolDefaultFunctionHandler(parameters);
            case "isnull" -> new IsNullDefaultFunctionHandler(parameters);
            case "isatom" -> new IsAtomDefaultFunctionHandler(parameters);
            case "islist" -> new IsListDefaultFunctionHandler(parameters);

            case "and" -> new AndDefaultFunctionHandler(parameters);
            case "or" -> new OrDefaultFunctionHandler(parameters);
            case "xor" -> new XorDefaultFunctionHandler(parameters);
            case "not" -> new NotDefaultFunctionHandler(parameters);

            case "eval" -> new EvalDefaultFunctionHandler(parameters);

            default -> null;
        };
    }
}
