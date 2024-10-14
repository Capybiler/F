package parser.nodes;

import java.util.List;

public class ProgASTNode extends ASTNode {
    private final List<AtomASTNode> localVariables;
    private final List<ASTNode> body;

    public ProgASTNode(List<AtomASTNode> localVariables, List<ASTNode> body) {
        this.localVariables = localVariables;
        this.body = body;
    }

    public List<AtomASTNode> getLocalVariables() {
        return localVariables;
    }

    public List<ASTNode> getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Prog(" + localVariables + ", " + body + ")";
    }
}
