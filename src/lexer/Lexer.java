package lexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Lexer {
    private final String input;
    private final long length;
    private long index;
    private long lineNum;
    private long columnNum;
    private long tokenBeginLineNum;
    private long tokenBeginColumnNum;
    private boolean nextIsNewLine;
    private boolean isNumericCurrentToken;
    private boolean isRealCurrentToken;
    private final List<Token> tokens;
    private final StringBuilder currentTokenStringBuilder;
    private final Map<String, TokenType> keywords;

    public Lexer(String input) {
        // Input string to be tokenized
        this.input = input;

        // Length of the input string
        this.length = input.length();

        // Index of the current character being processed
        this.index = 0;

        // Line number of the current character being processed
        this.lineNum = 1;

        // Column number of the current character being processed
        this.columnNum = 1;

        // Line number of the beginning of the current token
        this.tokenBeginLineNum = 1;

        // Column number of the beginning of the current token
        this.tokenBeginColumnNum = 1;

        // Flag to indicate if the next character in a new line
        this.nextIsNewLine = false;

        // List of tokens - the output of the lexer
        this.tokens = new ArrayList<>();

        // StringBuilder to store the current token being processed
        this.currentTokenStringBuilder = new StringBuilder();

        // Flag to indicate if the current token is a number
        this.isNumericCurrentToken = false;

        // Flag to indicate if the current token is a real number
        this.isRealCurrentToken = false;

        // Map of keywords to their corresponding token types
        this.keywords = new HashMap<>();
        keywords.put("quote", TokenType.QUOTE);
        keywords.put("setq", TokenType.SETQ);
        keywords.put("func", TokenType.FUNC);
        keywords.put("lambda", TokenType.LAMBDA);
        keywords.put("prog", TokenType.PROG);
        keywords.put("cond", TokenType.COND);
        keywords.put("while", TokenType.WHILE);
        keywords.put("return", TokenType.RETURN);
        keywords.put("break", TokenType.BREAK);
        keywords.put("plus", TokenType.PLUS);
        keywords.put("minus", TokenType.MINUS);
        keywords.put("times", TokenType.TIMES);
        keywords.put("divide", TokenType.DIVIDE);
        keywords.put("head", TokenType.HEAD);
        keywords.put("tail", TokenType.TAIL);
        keywords.put("cons", TokenType.CONS);
        keywords.put("equal", TokenType.EQUAL);
        keywords.put("nonequal", TokenType.NONEQUAL);
        keywords.put("less", TokenType.LESS);
        keywords.put("lesseq", TokenType.LESSEQ);
        keywords.put("greater", TokenType.GREATER);
        keywords.put("greatereq", TokenType.GREATEREQ);
        keywords.put("isint", TokenType.ISINT);
        keywords.put("isreal", TokenType.ISREAL);
        keywords.put("isbool", TokenType.ISBOOL);
        keywords.put("isnull", TokenType.ISNULL);
        keywords.put("isatom", TokenType.ISATOM);
        keywords.put("islist", TokenType.ISLIST);
        keywords.put("and", TokenType.AND);
        keywords.put("or", TokenType.OR);
        keywords.put("xor", TokenType.XOR);
        keywords.put("not", TokenType.NOT);
        keywords.put("eval", TokenType.EVAL);
    }

    private boolean isCurrentTokenReal() {
        return isRealCurrentToken;
    }

    private boolean isCurrentTokenNumeric() {
        return isNumericCurrentToken;
    }

    private boolean isCurrentTokenInteger() {
        return isNumericCurrentToken && !isRealCurrentToken;
    }

    private void setNumericCurrentToken() {
        isNumericCurrentToken = true;
    }

    private void setRealCurrentToken() {
        isRealCurrentToken = true;
    }

    // Return the character at the current index
    private char peek() {
        // If reached the end of the input string, return null character
        if (index >= length) {
            return '\0';
        }
        // Otherwise, return the character at the current index
        return input.charAt((int) index);
    }

    // Move to the next character in the input string
    private void advance() {
        // If reached the end of the input string, return
        if (index >= length) {
            return;
        }

        // If the current character is a newline character, increment the line number and reset the column number
        if (nextIsNewLine) {
            lineNum++;
            columnNum = 1;
            nextIsNewLine = false;
        } else {
            // Otherwise, increment the column number
            columnNum++;
        }

        // Increment the index to move to the next character
        index++;
    }

    // Handle the token in the current token string builder
    private void handlePreviousToken() {
        // If the current token string builder is empty, return
        if (currentTokenStringBuilder.isEmpty()) {
            return;
        }

        // Convert the current token string builder to a token string
        String tokenString = currentTokenStringBuilder.toString();

        // Calculate the end column number of the token
        long tokenEndColumnNum = tokenBeginColumnNum + tokenString.length() - 1;

        // Check if the token string is a keyword
        TokenType tokenType = keywords.get(tokenString);

        if (tokenType != null) {
            // If the token string is a keyword, add a token with the corresponding token type
            tokens.add(new Token(tokenType, new TokenSpan(tokenBeginLineNum, tokenBeginColumnNum, tokenEndColumnNum)));
        } else {
            try {
                tokens.add(
                    new Token(
                        TokenType.INTEGER,
                        new TokenSpan(tokenBeginLineNum, tokenBeginColumnNum, tokenEndColumnNum),
                        Long.parseLong(tokenString)
                    )
                );
                return;
            } catch (NumberFormatException _) {}

            try {
                tokens.add(
                    new Token(
                        TokenType.REAL,
                        new TokenSpan(tokenBeginLineNum, tokenBeginColumnNum, tokenEndColumnNum),
                        Double.parseDouble(tokenString)
                    )
                );
                return;
            } catch (NumberFormatException _) {}

            if (tokenString.equals("true") || tokenString.equals("false")) {
                tokens.add(
                    new Token(
                        TokenType.BOOLEAN,
                        new TokenSpan(tokenBeginLineNum, tokenBeginColumnNum, tokenEndColumnNum),
                        Boolean.parseBoolean(tokenString)
                    )
                );
                return;
            }

            if (tokenString.equals("null")) {
                tokens.add(
                    new Token(
                        TokenType.NULL,
                        new TokenSpan(tokenBeginLineNum, tokenBeginColumnNum, tokenEndColumnNum)
                    )
                );
                return;
            }

            tokens.add(
                new Token(
                    TokenType.IDENTIFIER,
                    new TokenSpan(tokenBeginLineNum, tokenBeginColumnNum, tokenEndColumnNum),
                    tokenString
                )
            );
        }
    }

    private void resetCurrentToken() {
        currentTokenStringBuilder.setLength(0);
        tokenBeginLineNum = lineNum;
        tokenBeginColumnNum = columnNum;
        isNumericCurrentToken = false;
        isRealCurrentToken = false;
    }

    private boolean isCurrentTokenEmpty() {
        return currentTokenStringBuilder.isEmpty();
    }

    private char prevCurrentToken() {
        return currentTokenStringBuilder.charAt(currentTokenStringBuilder.length() - 1);
    }

    public List<Token> lex() {
        while (true) {
            char c = peek();

            switch (c) {
                // Skip whitespace characters
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    handlePreviousToken();
                    resetCurrentToken();

                    if (c == '\n') {
                        nextIsNewLine = true;
                    }
                    advance();

                    break;
                case '(':
                    handlePreviousToken();
                    resetCurrentToken();

                    tokens.add(new Token(TokenType.LEFT_PAREN, new TokenSpan(lineNum, columnNum, columnNum)));
                    advance();

                    break;
                case ')':
                    handlePreviousToken();
                    resetCurrentToken();

                    tokens.add(new Token(TokenType.RIGHT_PAREN, new TokenSpan(lineNum, columnNum, columnNum)));
                    advance();

                    break;
                case '+':
                case '-':
                    if (!isCurrentTokenEmpty()) {
                        throw new LexicalAnalysisError('+', lineNum, columnNum);
                    }

                    tokenBeginColumnNum = columnNum;
                    currentTokenStringBuilder.append(c);

                    setNumericCurrentToken();

                    advance();

                    break;
                case '.':
                    if (!isCurrentTokenInteger()) {
                        throw new LexicalAnalysisError('.', lineNum, columnNum);
                    }

                    currentTokenStringBuilder.append(c);

                    setRealCurrentToken();

                    advance();

                    break;
                case '\'':
                    if (!isCurrentTokenEmpty()) {
                        throw new LexicalAnalysisError('\'', lineNum, columnNum);
                    }

                    tokens.add(new Token(TokenType.QUOTE, new TokenSpan(lineNum, columnNum, columnNum + 1)));
                    advance();
                    break;
                case 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                     'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                     'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z':
                    if (isCurrentTokenEmpty()) {
                        tokenBeginColumnNum = columnNum;
                    }

                    if (isCurrentTokenNumeric()) {
                        throw new LexicalAnalysisError(c, lineNum, columnNum);
                    }

                    currentTokenStringBuilder.append(c);
                    advance();
                    break;
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
                    if (isCurrentTokenEmpty()) {
                        tokenBeginColumnNum = columnNum;
                    }

                    currentTokenStringBuilder.append(c);
                    advance();
                    break;
                case '\0':
                    handlePreviousToken();
                    return tokens;
            }
        }
    }
}
