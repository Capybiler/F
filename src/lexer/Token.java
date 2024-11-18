package lexer;


public class Token {
    private final TokenType type;
    private final TokenSpan span;

    private int intValue;
    private double realValue;
    private boolean boolValue;
    private String identifier;

    public Token(TokenType type, TokenSpan span) {
        this.type = type;
        this.span = span;
    }

    public Token(TokenType type, TokenSpan span, int intValue) {
        this.type = type;
        this.span = span;
        this.intValue = intValue;
    }

    public Token(TokenType type, TokenSpan span, double realValue) {
        this.type = type;
        this.span = span;
        this.realValue = realValue;
    }

    public Token(TokenType type, TokenSpan span, boolean boolValue) {
        this.type = type;
        this.span = span;
        this.boolValue = boolValue;
    }

    public Token(TokenType type, TokenSpan span, String identifier) {
        this.type = type;
        this.span = span;
        this.identifier = identifier;
    }

    public TokenType getType() {
        return type;
    }

    public TokenSpan getSpan() {
        return span;
    }

    public int getIntValue() {
        return intValue;
    }

    public double getRealValue() {
        return realValue;
    }

    public boolean getBoolValue() {
        return boolValue;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String toString() {
        return switch (type) {
            case INTEGER -> "INTEGER(value=" + intValue + ", span=" + span + ")";
            case REAL -> "REAL(value=" + realValue + ", span=" + span + ")";
            case BOOLEAN -> "BOOLEAN(value=" + boolValue + ", span=" + span + ")";
            case NULL -> "NULL(span=" + span + ")";
            case IDENTIFIER -> "IDENTIFIER(value=" + identifier + ", span=" + span + ")";
            default -> type + "(span=" + span + ")";
        };
    }

    public boolean isLiteral() {
        return type == TokenType.INTEGER || type == TokenType.REAL || type == TokenType.BOOLEAN || type == TokenType.NULL;
    }

    public boolean isPredefinedFunction() {
        return type == TokenType.PLUS || type == TokenType.MINUS || type == TokenType.TIMES || type == TokenType.DIVIDE || type == TokenType.HEAD || type == TokenType.TAIL || type == TokenType.CONS || type == TokenType.EQUAL || type == TokenType.NONEQUAL || type == TokenType.LESS || type == TokenType.LESSEQ || type == TokenType.GREATER || type == TokenType.GREATEREQ || type == TokenType.ISINT || type == TokenType.ISREAL || type == TokenType.ISBOOL || type == TokenType.ISNULL || type == TokenType.ISATOM || type == TokenType.ISLIST || type == TokenType.AND || type == TokenType.OR || type == TokenType.XOR || type == TokenType.NOT || type == TokenType.EVAL;
    }

    public boolean isIdentifier() {
        return type == TokenType.IDENTIFIER || isPredefinedFunction();
    }
}
