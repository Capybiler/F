package parser.nodes;

public class WhileASTNode extends ASTNode {
    private final ASTNode condition;
    private final ASTNode body;

    public WhileASTNode(ASTNode condition, ASTNode body) {
        this.condition = condition;
        this.body = body;
    }

    public ASTNode getCondition() {
        return condition;
    }

    public ASTNode getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "While(" + condition + ", " + body + ")";
    }
}

