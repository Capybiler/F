package parser.nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramASTNode extends ASTNode {
    private final List<ASTNode> elements;

    public ProgramASTNode(List<ASTNode> elements) {
        this.elements = elements;
    }

    public List<ASTNode> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return "Program(" + String.join(", ", elements.stream().map(Object::toString).toArray(String[]::new)) + ")";
    }

    @Override
    public String toStringWithIndent(int indent) {
        return "\t".repeat(indent) + "Program(\n" + elements.stream().map(e -> e.toStringWithIndent(indent + 1)).reduce((a, b) -> a + ",\n" + b).orElse("") + "\n" + "\t".repeat(indent) + ")";
    }

    @Override
    public boolean isSpecialForm() {
        return false;
    }

    @Override
    public String toJson() {
        return "{\"type\": \"Program\", \"elements\": [" + elements.stream().map(ASTNode::toJson).reduce((a, b) -> a + ", " + b).orElse("") + "]}";
    }

    public void analyze() {
        List<String> initialPredefinedFunctions = new ArrayList<>();

        initialPredefinedFunctions.add("plus");
        initialPredefinedFunctions.add("minus");
        initialPredefinedFunctions.add("times");
        initialPredefinedFunctions.add("divide");

        initialPredefinedFunctions.add("head");
        initialPredefinedFunctions.add("tail");
        initialPredefinedFunctions.add("cons");

        initialPredefinedFunctions.add("equal");
        initialPredefinedFunctions.add("nonequal");
        initialPredefinedFunctions.add("less");
        initialPredefinedFunctions.add("lesseq");
        initialPredefinedFunctions.add("greater");
        initialPredefinedFunctions.add("greatereq");

        initialPredefinedFunctions.add("isint");
        initialPredefinedFunctions.add("isreal");
        initialPredefinedFunctions.add("isbool");
        initialPredefinedFunctions.add("isnull");
        initialPredefinedFunctions.add("isatom");
        initialPredefinedFunctions.add("islist");

        initialPredefinedFunctions.add("and");
        initialPredefinedFunctions.add("or");
        initialPredefinedFunctions.add("xor");
        initialPredefinedFunctions.add("not");

        initialPredefinedFunctions.add("eval");

        HashMap<String, Integer> functionParametersCount = new HashMap<>();

        functionParametersCount.put("plus", 2);
        functionParametersCount.put("minus", 2);
        functionParametersCount.put("times", 2);
        functionParametersCount.put("divide", 2);

        functionParametersCount.put("head", 1);
        functionParametersCount.put("tail", 1);
        functionParametersCount.put("cons", 2);

        functionParametersCount.put("equal", 2);
        functionParametersCount.put("nonequal", 2);
        functionParametersCount.put("less", 2);
        functionParametersCount.put("lesseq", 2);
        functionParametersCount.put("greater", 2);
        functionParametersCount.put("greatereq", 2);

        functionParametersCount.put("isint", 1);
        functionParametersCount.put("isreal", 1);
        functionParametersCount.put("isbool", 1);
        functionParametersCount.put("isnull", 1);
        functionParametersCount.put("isatom", 1);
        functionParametersCount.put("islist", 1);

        functionParametersCount.put("and", 2);
        functionParametersCount.put("or", 2);
        functionParametersCount.put("xor", 2);
        functionParametersCount.put("not", 1);

        functionParametersCount.put("eval", 1);

        analyze(initialPredefinedFunctions, functionParametersCount);
    }

    @Override
    public void analyze(List<String> localContext, Map<String, Integer> functionParametersCount) {
        for (ASTNode element : elements) {
            element.analyze(localContext, functionParametersCount);
        }
    }

    @Override
    public ASTNode optimize() {
        elements.replaceAll(ASTNode::optimize);
        return this;
    }
}
