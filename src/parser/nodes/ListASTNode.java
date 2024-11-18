package parser.nodes;

import interpreter.defaultFunctions.DefaultFunctionHandler;
import interpreter.defaultFunctions.DefaultFunctionMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListASTNode extends ASTNode {
    private final List<ASTNode> elements;

    public ListASTNode(List<ASTNode> elements) {
        this.elements = elements;
    }

    public List<ASTNode> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return "List(" + String.join(", ", elements.stream().map(Object::toString).toArray(String[]::new)) + ")";
    }

    @Override
    public String toStringWithIndent(int indent) {
        return "\t".repeat(indent) + "List(\n" + elements.stream().map(e -> e.toStringWithIndent(indent + 1)).reduce((a, b) -> a + ",\n" + b).orElse("") + "\n" + "\t".repeat(indent) + ")";
    }

    @Override
    public boolean isSpecialForm() {
        return false;
    }

    @Override
    public String toJson() {
        return "{\"type\": \"List\", \"elements\": [" + elements.stream().map(ASTNode::toJson).reduce((a, b) -> a + ", " + b).orElse("") + "]}";
    }

    @Override
    public void analyze(List<String> localContext, Map<String, Integer> functionParametersCount) {
        if (elements.isEmpty()) {
            return;
        }

        for (ASTNode element : elements) {
            element.analyze(localContext, functionParametersCount);
        }

        if (!(elements.getFirst() instanceof AtomASTNode)) {
            return;
        }

        AtomASTNode firstElement = (AtomASTNode) elements.getFirst();

        if (functionParametersCount.getOrDefault(firstElement.getName(), null) == null) {
            return;
        }

        if (functionParametersCount.get(firstElement.getName()) != elements.size() - 1) {
            throw new RuntimeException("Function " + firstElement.getName() + " expects " + functionParametersCount.get(firstElement.getName()) + " parameters, but got " + (elements.size() - 1) + " parameters");
        }
    }

    @Override
    public ASTNode optimize() {
        elements.replaceAll(ASTNode::optimize);

        return this;
    }

    @Override
    public Object interpret(Map<String, Object> context) {
        if (elements.getFirst() instanceof AtomASTNode) {
            List<Object> interpretedParameters = elements.subList(1, elements.size()).stream().map(e -> e.interpret(context)).toList();

            DefaultFunctionHandler defaultFunctionHandler = DefaultFunctionMapper.getHandler(((AtomASTNode) elements.getFirst()).getName(), interpretedParameters);

            if (defaultFunctionHandler != null) {
                return defaultFunctionHandler.handle();
            }
        }

        ASTNode firstElement = elements.getFirst();

        Object interpretedFirstElement = firstElement.interpret(context);

        if (!(interpretedFirstElement instanceof LambdaASTNode)) {
            throw new RuntimeException("First element of a list must be a function");
        }

        LambdaASTNode lambda = (LambdaASTNode) interpretedFirstElement;

        if (lambda.getParameters().size() != elements.size() - 1) {
            String functionName;

            if (elements.getFirst() instanceof AtomASTNode) {
                functionName = ((AtomASTNode) elements.getFirst()).getName();
            } else {
                functionName = "lambda";
            }

            throw new RuntimeException("Function " + functionName + " expects " + lambda.getParameters().size() + " parameters, but got " + (elements.size() - 1) + " parameters");
        }

        Map<String, Object> newContext = new HashMap<>();
        newContext.putAll(context);

        for (int i = 0; i < lambda.getParameters().size(); i++) {
            newContext.put(lambda.getParameters().get(i).getName(), elements.get(i + 1).interpret(context));
        }

        return lambda.getBody().interpret(newContext);
    }
}
