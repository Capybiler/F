package parser.nodes;

import java.util.List;

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
    public void analyze(List<AtomASTNode> localContext) {
        localContext.add(variable);
        value.analyze(localContext);
    }

    @Override
    public void optimize() {

    }
}
