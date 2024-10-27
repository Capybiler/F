package parser.nodes;

import java.util.List;

public class ProgramASTNode extends ASTNode {
    private final List<ASTNode> elements;

    public ProgramASTNode(List<ASTNode> elements) {
        this.elements = elements;
    }

    public List<ASTNode> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return "Program(" + String.join(", ", elements.stream().map(Object::toString).toArray(String[]::new)) + ")";
    }

    @Override
    public String toStringWithIndent(int indent) {
        return "\t".repeat(indent) + "Program(\n" + elements.stream().map(e -> e.toStringWithIndent(indent + 1)).reduce((a, b) -> a + ",\n" + b).orElse("") + "\n" + "\t".repeat(indent) + ")";
    }

    @Override
    public boolean isSpecialForm() {
        return false;
    }

    @Override
    public String toJson() {
        return "{\"type\": \"Program\", \"elements\": [" + elements.stream().map(ASTNode::toJson).reduce((a, b) -> a + ", " + b).orElse("") + "]}";
    }
}
