package lexer;

public class LexicalAnalysisError extends RuntimeException {
    public LexicalAnalysisError(char c, long line, long column) {
        super("Lexical analysis error: unexpected character '" + c + "' at line " + line + ", column " + column);
    }
}
