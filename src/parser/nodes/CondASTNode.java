package parser.nodes;

import java.util.List;

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

    @Override
    public String toStringWithIndent(int indent) {
        if (falseBranch == null) {
            return "\t".repeat(indent) + "Cond(\n" + condition.toStringWithIndent(indent + 1) + ",\n" + trueBranch.toStringWithIndent(indent + 1) + "\n" + "\t".repeat(indent) + ")";
        }
        return "\t".repeat(indent) + "Cond(\n" + condition.toStringWithIndent(indent + 1) + ",\n" + trueBranch.toStringWithIndent(indent + 1) + ",\n" + falseBranch.toStringWithIndent(indent + 1) + "\n" + "\t".repeat(indent) + ")";
    }

    @Override
    public boolean isSpecialForm() {
        return true;
    }

    @Override
    public String toJson() {
        if (falseBranch == null) {
            return "{\"type\": \"Cond\", \"condition\": " + condition.toJson() + ", \"trueBranch\": " + trueBranch.toJson() + ", \"falseBranch\": null}";
        }
        return "{\"type\": \"Cond\", \"condition\": " + condition.toJson() + ", \"trueBranch\": " + trueBranch.toJson() + ", \"falseBranch\": " + falseBranch.toJson() + "}";
    }

    @Override
    public void analyze(List<AtomASTNode> localContext) {
        condition.analyze(localContext);
        trueBranch.analyze(localContext);
        if (falseBranch != null) {
            falseBranch.analyze(localContext);
        }
    }

    @Override
    public void optimize() {

    }
}
