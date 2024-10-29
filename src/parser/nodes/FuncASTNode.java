package parser.nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FuncASTNode extends ASTNode {
    private final AtomASTNode name;
    private final List<AtomASTNode> parameters;
    private ASTNode body;

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

    @Override
    public String toStringWithIndent(int indent) {
        return "\t".repeat(indent) + "Func(\n" + name.toStringWithIndent(indent + 1) + ",\n" + "\t".repeat(indent + 1) + parameters + ",\n" + body.toStringWithIndent(indent + 1) + "\n" + "\t".repeat(indent) + ")";
    }

    @Override
    public boolean isSpecialForm() {
        return true;
    }

    @Override
    public String toJson() {
        return "{\"type\": \"Func\", \"name\": " + name.toJson() + ", \"parameters\": " + parameters.stream().map(ASTNode::toJson).reduce((a, b) -> a + ", " + b).stream().toList() + ", \"body\": " + body.toJson() + "}";
    }

    @Override
    public void analyze(List<String> localContext, Map<String, Integer> functionParametersCount) {
        functionParametersCount.put(name.getName(), parameters.size());

        List<String> bodyLocalContext = new ArrayList<>();

        bodyLocalContext.addAll(localContext);
        bodyLocalContext.add(name.getName());
        bodyLocalContext.addAll(parameters.stream().map(AtomASTNode::getName).toList());

        Map<String, Integer> bodyFunctionParametersCount = new HashMap<>();
        bodyFunctionParametersCount.putAll(functionParametersCount);

        body.analyze(bodyLocalContext, bodyFunctionParametersCount);
    }

    @Override
    public ASTNode optimize() {
        body = body.optimize();
        return this;
    }
}
