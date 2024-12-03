package interpreter.defaultFunctions;

import parser.nodes.ListASTNode;

import java.util.List;

public class IsListDefaultFunctionHandler extends DefaultFunctionHandler {
    public IsListDefaultFunctionHandler(List<Object> parameters) {
        super(parameters);
        this.expectedParametersCount = 1;
    }

    @Override
    public Object handle() {
        return parameters.getFirst() instanceof ListASTNode;
    }
}
