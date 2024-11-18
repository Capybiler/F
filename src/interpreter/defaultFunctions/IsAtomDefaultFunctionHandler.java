package interpreter.defaultFunctions;

import parser.nodes.AtomASTNode;

import java.util.List;

public class IsAtomDefaultFunctionHandler extends DefaultFunctionHandler {
    public IsAtomDefaultFunctionHandler(List<Object> parameters) {
        super(parameters);
        this.expectedParametersCount = 1;
    }

    @Override
    public Object handle() {
        return parameters.getFirst() instanceof AtomASTNode;
    }
}
