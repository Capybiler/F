package parser.nodes;

import java.util.List;

public abstract class ASTNode {
    public abstract String toString();

    public abstract String toStringWithIndent(int indent);

    public abstract boolean isSpecialForm();

    public abstract String toJson();

    public abstract void analyze(List<AtomASTNode> localContext);

    public abstract void optimize();
}
