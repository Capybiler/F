package parser.nodes;

import java.util.List;
import java.util.Map;

public class QuoteASTNode extends ASTNode {
    private final ASTNode quotedElement;

    public QuoteASTNode(ASTNode quotedElement) {
        this.quotedElement = quotedElement;
    }

    public ASTNode getQuotedElement() {
        return quotedElement;
    }

    @Override
    public String toString() {
        return "Quote(" + quotedElement + ")";
    }

    @Override
    public String toStringWithIndent(int indent) {
        return "\t".repeat(indent) + "Quote(\n" + quotedElement.toStringWithIndent(indent + 1) + "\n" + "\t".repeat(indent) + ")";
    }

    @Override
    public boolean isSpecialForm() {
        return true;
    }

    @Override
    public String toJson() {
        return "{\"type\": \"Quote\", \"quotedElement\": " + quotedElement.toJson() + "}";
    }

    @Override
    public void analyze(List<String> localContext, Map<String, Integer> functionParametersCount) {

    }

    @Override
    public void optimize() {

    }
}
