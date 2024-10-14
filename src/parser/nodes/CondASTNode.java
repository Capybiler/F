package parser.nodes;

public class CondASTNode extends ASTNode {
    private final ASTNode condition;
    private final ASTNode trueBranch;
    private final ASTNode falseBranch;

    public CondASTNode(ASTNode condition, ASTNode trueBranch, ASTNode falseBranch) {
        this.condition = condition;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }

    public ASTNode getCondition() {
        return condition;
    }

    public ASTNode getTrueBranch() {
        return trueBranch;
    }

    public ASTNode getFalseBranch() {
        return falseBranch;
    }

    @Override
    public String toString() {
        return "Cond(" + condition + ", " + trueBranch + ", " + falseBranch + ")";
    }
}
