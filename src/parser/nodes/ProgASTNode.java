package parser.nodes;

import java.util.List;

public class ProgASTNode extends ASTNode {
    private final List<AtomASTNode> localVariables;
    private final ASTNode body;

    public ProgASTNode(List<AtomASTNode> localVariables, ASTNode body) {
        this.localVariables = localVariables;
        this.body = body;
    }

    public List<AtomASTNode> getLocalVariables() {
        return localVariables;
    }

    public ASTNode getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Prog(" + localVariables + ", " + body + ")";
    }

    @Override
    public String toStringWithIndent(int indent) {
        return "\t".repeat(indent) + "Prog(\n" + "\t".repeat(indent + 1) + localVariables + ",\n" + body.toStringWithIndent(indent + 1) + "\n" + "\t".repeat(indent) + ")";
    }

    @Override
    public boolean isSpecialForm() {
        return true;
    }

    @Override
    public String toJson() {
        return "{\"type\": \"Prog\", \"localVariables\": " + localVariables.stream().map(ASTNode::toJson).reduce((a, b) -> a + ", " + b).stream().toList() + ", \"body\": " + body.toJson() + "}";
    }
}
