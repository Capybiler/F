package parser.nodes;

import java.util.List;
import java.util.Map;

public class CondASTNode extends ASTNode {
    private ASTNode condition;
    private ASTNode trueBranch;
    private ASTNode falseBranch;

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
    public void analyze(List<String> localContext, Map<String, Integer> functionParametersCount) {
        condition.analyze(localContext, functionParametersCount);
        trueBranch.analyze(localContext, functionParametersCount);
        if (falseBranch != null) {
            falseBranch.analyze(localContext, functionParametersCount);
        }
    }

    @Override
    public ASTNode optimize() {
        condition = condition.optimize();
        trueBranch = trueBranch.optimize();
        if (falseBranch != null) {
            falseBranch = falseBranch.optimize();
        }

        if (condition instanceof LiteralASTNode) {
            if ((boolean) ((LiteralASTNode) condition).getValue()) {
                return trueBranch;
            } else {
                if (falseBranch != null) {
                    return falseBranch;
                }
                return new LiteralASTNode(null);
            }
        }

        return this;
    }

    @Override
    public Object interpret(Map<String, Object> context) {
        if (falseBranch == null) {
            return (boolean) condition.interpret(context) ? trueBranch.interpret(context) : null;
        }
        return (boolean) condition.interpret(context) ? trueBranch.interpret(context) : falseBranch.interpret(context);
    }
}
