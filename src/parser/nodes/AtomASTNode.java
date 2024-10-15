package parser.nodes;

public class AtomASTNode extends ASTNode {
    private final String name;

    public AtomASTNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Atom(" + name + ")";
    }

    @Override
    public String toStringWithIndent(int indent) {
        return "\t".repeat(indent) + "Atom(" + name + ")";
    }

    @Override
    public boolean isSpecialForm() {
        return false;
    }

    @Override
    public String toJson() {
        return "{\"type\": \"Atom\", \"name\": \"" + name + "\"}";
    }
}
