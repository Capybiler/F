package interpreter.defaultFunctions;

import parser.nodes.ASTNode;
import parser.nodes.ListASTNode;
import parser.nodes.LiteralASTNode;

import java.util.List;

public class ConsDefaultFunctionHandler extends DefaultFunctionHandler {
    public ConsDefaultFunctionHandler(List<Object> parameters) {
        super(parameters);
        this.expectedParametersCount = 2;
    }

    @Override
    public Object handle() {
        if (!(parameters.get(1) instanceof ListASTNode)) {
            throw new IllegalArgumentException("Expected list as a parameter, but got " + parameters.get(1).getClass().getSimpleName());
        }

        ASTNode node;

        if (parameters.getFirst() instanceof ASTNode) {
            node = (ASTNode) parameters.getFirst();
        } else {
            node = new LiteralASTNode(parameters.getFirst());
        }

        ListASTNode list = (ListASTNode) parameters.get(1);

        list.getElements().addFirst(node);

        return list;
    }
}
