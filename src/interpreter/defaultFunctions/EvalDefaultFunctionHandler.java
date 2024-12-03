package interpreter.defaultFunctions;

import parser.nodes.ASTNode;

import java.util.List;
import java.util.Map;

public class EvalDefaultFunctionHandler extends DefaultFunctionHandler {
    private Map<String, Object> context;

    public EvalDefaultFunctionHandler(List<Object> parameters, Map<String, Object> context) {
        super(parameters);
        this.expectedParametersCount = 1;
        this.context = context;
    }

    @Override
    public Object handle() {
        if (!(parameters.getFirst() instanceof ASTNode)) {
            return parameters.getFirst();
        }

        return ((ASTNode) parameters.getFirst()).interpret(context);
    }
}
