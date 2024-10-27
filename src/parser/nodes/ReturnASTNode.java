package parser.nodes;

import java.util.List;

public class ReturnASTNode extends ASTNode {
    private final ASTNode value;

    public ReturnASTNode(ASTNode value) {
        this.value = value;
    }

    public ASTNode getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Return(" + value + ")";
    }

    @Override
    public String toStringWithIndent(int indent) {
        return "\t".repeat(indent) + "Return(\n" + value.toStringWithIndent(indent + 1) + "\n" + "\t".repeat(indent) + ")";
    }

    @Override
    public boolean isSpecialForm() {
        return true;
    }

    @Override
    public String toJson() {
        return "{\"type\": \"Return\", \"value\": " + value.toJson() + "}";
    }

    @Override
    public void analyze(List<AtomASTNode> localContext) {

    }

    @Override
    public void optimize() {

    }
}
