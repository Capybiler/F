package parser.nodes;

import java.util.List;

public class ListASTNode extends ASTNode {
    private final List<ASTNode> elements;

    public ListASTNode(List<ASTNode> elements) {
        this.elements = elements;
    }

    public List<ASTNode> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return "List(" + String.join(", ", elements.stream().map(Object::toString).toArray(String[]::new)) + ")";
    }
}
