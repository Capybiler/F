package parser.nodes;

import java.util.List;
import java.util.Map;

public class BreakASTNode extends ASTNode {
    @Override
    public String toString() {
        return "Break";
    }

    @Override
    public String toStringWithIndent(int indent) {
        return "\t".repeat(indent) + "Break";
    }

    @Override
    public boolean isSpecialForm() {
        return true;
    }

    @Override
    public String toJson() {
        return "{\"type\": \"Break\"}";
    }

    @Override
    public void analyze(List<String> localContext, Map<String, Integer> functionParametersCount) {

    }

    @Override
    public ASTNode optimize() {
        return this;
    }
}
