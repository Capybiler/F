package parser.nodes;

public abstract class ASTNode {
    public abstract String toString();

    public abstract String toStringWithIndent(int indent);

    public abstract boolean isSpecialForm();

    public abstract String toJson();
}
