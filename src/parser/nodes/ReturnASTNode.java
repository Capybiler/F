package parser.nodes;

import interpreter.exceptions.ReturnException;

import java.util.List;
import java.util.Map;

public class ReturnASTNode extends ASTNode {
    private ASTNode value;

    public ReturnASTNode(ASTNode value) {
        this.value = value;
    }

    public ASTNode getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Return(" + value + ")";
    }

    @Override
    public String toStringWithIndent(int indent) {
        return "\t".repeat(indent) + "Return(\n" + value.toStringWithIndent(indent + 1) + "\n" + "\t".repeat(indent) + ")";
    }

    @Override
    public boolean isSpecialForm() {
        return true;
    }

    @Override
    public String toJson() {
        return "{\"type\": \"Return\", \"value\": " + value.toJson() + "}";
    }

    @Override
    public void analyze(List<String> localContext, Map<String, Integer> functionParametersCount) {
        value.analyze(localContext, functionParametersCount);
    }

    @Override
    public ASTNode optimize() {
        value = value.optimize();
        return this;
    }

    @Override
    public Object interpret(Map<String, Object> context) {
        throw new ReturnException(value.interpret(context));
    }
}
