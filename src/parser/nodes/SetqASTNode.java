package parser.nodes;

import java.util.List;
import java.util.Map;

public class SetqASTNode extends ASTNode {
    private final AtomASTNode variable;
    private final ASTNode value;

    public SetqASTNode(AtomASTNode variable, ASTNode value) {
        this.variable = variable;
        this.value = value;
    }

    public AtomASTNode getVariable() {
        return variable;
    }

    public ASTNode getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Setq(" + variable + ", " + value + ")";
    }

    @Override
    public String toStringWithIndent(int indent) {
        return "\t".repeat(indent) + "Setq(\n" + "\t".repeat(indent + 1) + variable + ",\n" + value.toStringWithIndent(indent + 1) + "\n" + "\t".repeat(indent) + ")";
    }

    @Override
    public boolean isSpecialForm() {
        return true;
    }

    @Override
    public String toJson() {
        return "{\"type\": \"Setq\", \"variable\": " + variable.toJson() + ", \"value\": " + value.toJson() + "}";
    }

    @Override
    public void analyze(List<String> localContext, Map<String, Integer> functionParametersCount) {
        localContext.add(variable.getName());
        value.analyze(localContext, functionParametersCount);

        if (value instanceof LambdaASTNode) {
            functionParametersCount.put(variable.getName(), ((LambdaASTNode) value).getParameters().size());
        } else {
            functionParametersCount.put(variable.getName(), null);
        }
    }

    @Override
    public void optimize() {

    }
}
