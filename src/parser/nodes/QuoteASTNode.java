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
}
