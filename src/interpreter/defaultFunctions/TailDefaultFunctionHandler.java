package interpreter.defaultFunctions;

import parser.nodes.ListASTNode;

import java.util.List;

public class TailDefaultFunctionHandler extends DefaultFunctionHandler {
    public TailDefaultFunctionHandler(List<Object> parameters) {
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
            throw new IllegalArgumentException("Cannot get tail of an empty list");
        }

        return new ListASTNode(list.getElements().subList(1, list.getElements().size()));
    }
}
