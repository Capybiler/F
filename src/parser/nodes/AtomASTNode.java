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
}
