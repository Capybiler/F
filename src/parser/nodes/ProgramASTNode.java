package parser.nodes;

import java.util.ArrayList;
import java.util.List;

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
        List<AtomASTNode> initialPredefinedFunctions = new ArrayList<>();

        initialPredefinedFunctions.add(new AtomASTNode("plus"));
        initialPredefinedFunctions.add(new AtomASTNode("minus"));
        initialPredefinedFunctions.add(new AtomASTNode("times"));
        initialPredefinedFunctions.add(new AtomASTNode("divide"));

        initialPredefinedFunctions.add(new AtomASTNode("head"));
        initialPredefinedFunctions.add(new AtomASTNode("tail"));
        initialPredefinedFunctions.add(new AtomASTNode("cons"));

        initialPredefinedFunctions.add(new AtomASTNode("equal"));
        initialPredefinedFunctions.add(new AtomASTNode("nonequal"));
        initialPredefinedFunctions.add(new AtomASTNode("less"));
        initialPredefinedFunctions.add(new AtomASTNode("lesseq"));
        initialPredefinedFunctions.add(new AtomASTNode("greater"));
        initialPredefinedFunctions.add(new AtomASTNode("greatereq"));

        initialPredefinedFunctions.add(new AtomASTNode("isint"));
        initialPredefinedFunctions.add(new AtomASTNode("isreal"));
        initialPredefinedFunctions.add(new AtomASTNode("isbool"));
        initialPredefinedFunctions.add(new AtomASTNode("isnull"));
        initialPredefinedFunctions.add(new AtomASTNode("isatom"));
        initialPredefinedFunctions.add(new AtomASTNode("islist"));

        initialPredefinedFunctions.add(new AtomASTNode("and"));
        initialPredefinedFunctions.add(new AtomASTNode("or"));
        initialPredefinedFunctions.add(new AtomASTNode("xor"));
        initialPredefinedFunctions.add(new AtomASTNode("not"));

        initialPredefinedFunctions.add(new AtomASTNode("eval"));

        analyze(initialPredefinedFunctions);
    }

    @Override
    public void analyze(List<AtomASTNode> localContext) {
        for (ASTNode element : elements) {
            element.analyze(localContext);
        }
    }

    @Override
    public void optimize() {

    }
}
