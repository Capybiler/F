package parser.nodes;

public class BreakASTNode extends ASTNode {
    @Override
    public String toString() {
        return "Break";
    }

    @Override
    public String toStringWithIndent(int indent) {
        return "\t".repeat(indent) + "Break";
    }

    @Override
    public boolean isSpecialForm() {
        return true;
    }

    @Override
    public String toJson() {
        return "{\"type\": \"Break\"}";
    }
}
