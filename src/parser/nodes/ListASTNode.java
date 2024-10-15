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

    @Override
    public String toStringWithIndent(int indent) {
        return "\t".repeat(indent) + "List(\n" + elements.stream().map(e -> e.toStringWithIndent(indent + 1)).reduce((a, b) -> a + ",\n" + b).orElse("") + "\n" + "\t".repeat(indent) + ")";
    }

    @Override
    public boolean isSpecialForm() {
        return false;
    }

    @Override
    public String toJson() {
        return "{\"type\": \"List\", \"elements\": [" + elements.stream().map(ASTNode::toJson).reduce((a, b) -> a + ", " + b).orElse("") + "]}";
    }
}
