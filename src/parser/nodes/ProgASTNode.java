package parser.nodes;

import interpreter.exceptions.ReturnException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgASTNode extends ASTNode {
    private final List<AtomASTNode> localVariables;
    private final List<ASTNode> elements;

    public ProgASTNode(List<AtomASTNode> localVariables, List<ASTNode> elements) {
        this.localVariables = localVariables;
        this.elements = elements;
    }

    public List<AtomASTNode> getLocalVariables() {
        return localVariables;
    }

    public List<ASTNode> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return "Prog(" + localVariables + ", " + elements + ")";
    }

    @Override
    public String toStringWithIndent(int indent) {
        return "\t".repeat(indent) + "Prog(\n" + "\t".repeat(indent + 1) + localVariables + ",\n" + "\t".repeat(indent + 1) + "[\n" + elements.stream().map(e -> e.toStringWithIndent(indent + 2)).reduce((a, b) -> a + ",\n" + b).orElse("") + "\n" + "\t".repeat(indent + 1) + "]\n" + "\t".repeat(indent) + ")";
    }

    @Override
    public boolean isSpecialForm() {
        return true;
    }

    @Override
    public String toJson() {
        return "{\"type\": \"Prog\", \"localVariables\": " + localVariables.stream().map(ASTNode::toJson).reduce((a, b) -> a + ", " + b).stream().toList() + ", \"elements\": " + elements.stream().map(ASTNode::toJson).reduce((a, b) -> a + ", " + b).stream().toList() + "}";
    }

    @Override
    public void analyze(List<String> localContext, Map<String, Integer> functionParametersCount) {
        List<String> bodyLocalContext = new ArrayList<>();

        bodyLocalContext.addAll(localContext);
        bodyLocalContext.addAll(localVariables.stream().map(AtomASTNode::getName).toList());

        Map<String, Integer> bodyFunctionParametersCount = new HashMap<>();
        bodyFunctionParametersCount.putAll(functionParametersCount);

        for (ASTNode element : elements) {
            element.analyze(bodyLocalContext, bodyFunctionParametersCount);
        }
    }

    @Override
    public ASTNode optimize() {
        Integer firstReturnOrBreakIndex = null;

        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i) instanceof ReturnASTNode || elements.get(i) instanceof BreakASTNode) {
                firstReturnOrBreakIndex = i;
                break;
            }
        }

        Integer elementsLengthAfterOptimization;

        if (firstReturnOrBreakIndex != null) {
            elementsLengthAfterOptimization = firstReturnOrBreakIndex + 1;
        } else {
            elementsLengthAfterOptimization = elements.size();
        }

        while (elements.size() > elementsLengthAfterOptimization) {
            elements.removeLast();
        }

        elements.replaceAll(ASTNode::optimize);

        return this;
    }

    @Override
    public Object interpret(Map<String, Object> context) {
        Map<String, Object> localContext = new HashMap<>();
        localContext.putAll(context);

        for (AtomASTNode localVariable : localVariables) {
            localContext.put(localVariable.getName(), null);
        }

        for (ASTNode element : elements) {
            try {
                element.interpret(localContext);
            } catch (ReturnException e) {
                return e.getValue();
            }
        }

        return null;
    }
}
