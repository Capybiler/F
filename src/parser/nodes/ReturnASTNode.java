package parser.nodes;

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
}
