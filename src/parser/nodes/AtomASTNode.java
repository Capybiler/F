package parser.nodes;

import java.util.List;
import java.util.Map;

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

    @Override
    public void analyze(List<String> localContext, Map<String, Integer> functionParametersCount) {
        if (!localContext.contains(name)) {
            throw new RuntimeException("Variable " + name + " is not defined");
        }
    }

    @Override
    public void optimize() {

    }
}
