package parser.nodes;

public class LiteralASTNode extends ASTNode {
    private final Object value;

    public LiteralASTNode(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Literal(" + value + ")";
    }
}
