package interpreter.defaultFunctions;

import parser.nodes.ListASTNode;

import java.util.List;

public class HeadDefaultFunctionHandler extends DefaultFunctionHandler {
    public HeadDefaultFunctionHandler(List<Object> parameters) {
        super(parameters);
        this.expectedParametersCount = 1;
    }

    @Override
    public Object handle() {
        if (!(parameters.getFirst() instanceof ListASTNode)) {
            throw new IllegalArgumentException("Expected list as a parameter, but got " + parameters.getFirst().getClass().getSimpleName());
        }

        ListASTNode list = (ListASTNode) parameters.getFirst();

        if (list.getElements().isEmpty()) {
            throw new IllegalArgumentException("Cannot get head of an empty list");
        }

        return list.getElements().getFirst();
    }
}
