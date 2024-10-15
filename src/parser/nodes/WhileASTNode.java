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

    @Override
    public String toStringWithIndent(int indent) {
        return "\t".repeat(indent) + "While(\n" + condition.toStringWithIndent(indent + 1) + ",\n" + body.toStringWithIndent(indent + 1) + "\n" + "\t".repeat(indent) + ")";
    }

    @Override
    public boolean isSpecialForm() {
        return true;
    }

    @Override
    public String toJson() {
        return "{\"type\": \"While\", \"condition\": " + condition.toJson() + ", \"body\": " + body.toJson() + "}";
    }
}
