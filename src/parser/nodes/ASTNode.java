package parser.nodes;

import java.util.List;
import java.util.Map;

public abstract class ASTNode {
    public abstract String toString();

    public abstract String toStringWithIndent(int indent);

    public abstract boolean isSpecialForm();

    public abstract String toJson();

    public abstract void analyze(List<String> localContext, Map<String, Integer> functionParametersCount);

    public abstract ASTNode optimize();

    public abstract Object interpret(Map<String, Object> context);
}
