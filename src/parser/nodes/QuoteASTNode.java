package parser.nodes;

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
}
