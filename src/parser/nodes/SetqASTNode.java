package parser.nodes;

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
}
