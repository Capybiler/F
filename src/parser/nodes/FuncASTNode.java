package parser.nodes;

import java.util.List;

public class FuncASTNode extends ASTNode {
    private final AtomASTNode name;
    private final List<AtomASTNode> parameters;
    private final ASTNode body;

    public FuncASTNode(AtomASTNode name, List<AtomASTNode> parameters, ASTNode body) {
        this.name = name;
        this.parameters = parameters;
        this.body = body;
    }

    public AtomASTNode getName() {
        return name;
    }

    public List<AtomASTNode> getParameters() {
        return parameters;
    }

    public ASTNode getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Func(" + name + ", " + parameters + ", " + body + ")";
    }
}
