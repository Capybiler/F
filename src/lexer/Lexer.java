package lexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Lexer {
    private final String input;
    private final int length;
    private int index;
    private int lineNum;
    private int columnNum;
    private int tokenBeginLineNum;
    private int tokenBeginColumnNum;
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
        keywords.put("null", TokenType.NULL);
    }

    // Return if the current token is a real number
    private boolean isCurrentTokenReal() {
        return isRealCurrentToken;
    }

    // Return if the current token is a number
    private boolean isCurrentTokenNumeric() {
        return isNumericCurrentToken;
    }

    // Return if the current token is an integer
    private boolean isCurrentTokenInteger() {
        // The current token is an integer if it is a number and not a real number
        return isNumericCurrentToken && !isRealCurrentToken;
    }

    // Set the current token as a number
    private void setNumericCurrentToken() {
        isNumericCurrentToken = true;
    }

    // Set the current token as a real number
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
    private void handleCurrentToken() {
        // If the current token string builder is empty, return
        if (currentTokenStringBuilder.isEmpty()) {
            return;
        }

        // Convert the current token string builder to a token string
        String tokenString = currentTokenStringBuilder.toString();

        // Calculate the end column number of the token
        int tokenEndColumnNum = tokenBeginColumnNum + tokenString.length() - 1;

        // Check if the token string is a keyword
        TokenType tokenType = keywords.get(tokenString);

        if (tokenType != null) {
            // If the token string is a keyword, add a token with the corresponding token type
            tokens.add(new Token(tokenType, new TokenSpan(tokenBeginLineNum, tokenBeginColumnNum, tokenEndColumnNum), tokenString));
            return;
        }

        if (isCurrentTokenInteger()) {
            // If the token string is an integer, add a token with the integer value
            tokens.add(
                    new Token(
                            TokenType.INTEGER,
                            new TokenSpan(tokenBeginLineNum, tokenBeginColumnNum, tokenEndColumnNum),
                            Integer.parseInt(tokenString)
                    )
            );
            return;
        }

        if (isCurrentTokenReal()) {
            // If the token string is a real number, add a token with the real number value
            tokens.add(
                    new Token(
                            TokenType.REAL,
                            new TokenSpan(tokenBeginLineNum, tokenBeginColumnNum, tokenEndColumnNum),
                            Double.parseDouble(tokenString)
                    )
            );
            return;
        }

        if (tokenString.equals("true") || tokenString.equals("false")) {
            // If the token string is a boolean, add a token with the boolean value
            tokens.add(
                    new Token(
                            TokenType.BOOLEAN,
                            new TokenSpan(tokenBeginLineNum, tokenBeginColumnNum, tokenEndColumnNum),
                            Boolean.parseBoolean(tokenString)
                    )
            );
            return;
        }

        // If the token string is an identifier, add a token with the identifier value
        tokens.add(
                new Token(
                        TokenType.IDENTIFIER,
                        new TokenSpan(tokenBeginLineNum, tokenBeginColumnNum, tokenEndColumnNum),
                        tokenString
                )
        );
    }

    // Reset the current token string builder
    private void resetCurrentToken() {
        currentTokenStringBuilder.setLength(0);
        tokenBeginLineNum = lineNum;
        tokenBeginColumnNum = columnNum;
        isNumericCurrentToken = false;
        isRealCurrentToken = false;
    }

    private void handleAndResetCurrentToken() {
        handleCurrentToken();
        resetCurrentToken();
    }

    // Return if the current token string builder is empty
    private boolean isCurrentTokenEmpty() {
        return currentTokenStringBuilder.isEmpty();
    }

    // Tokenize the input string
    public List<Token> lex() {
        while (true) {
            // Peek the current character
            char c = peek();

            // Handle the current character
            switch (c) {
                // Skip whitespace characters
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    // Handle the current token and reset the current token string builder
                    handleAndResetCurrentToken();

                    // If the current character is a newline character,
                    // set the flag to indicate the next character in a new line
                    if (c == '\n') {
                        nextIsNewLine = true;
                    }

                    // Move to the next character
                    advance();

                    break;
                case '(':
                    // Handle the current token and reset the current token string builder
                    handleAndResetCurrentToken();

                    // Add a token with the left parenthesis token type
                    tokens.add(new Token(TokenType.LEFT_PAREN, new TokenSpan(lineNum, columnNum, columnNum)));

                    // Move to the next character
                    advance();

                    break;
                case ')':
                    // Handle the current token and reset the current token string builder
                    handleAndResetCurrentToken();

                    // Add a token with the right parenthesis token type
                    tokens.add(new Token(TokenType.RIGHT_PAREN, new TokenSpan(lineNum, columnNum, columnNum)));

                    // Move to the next character
                    advance();

                    break;
                case '+':
                case '-':
                    // Plus or minus sign is only allowed at the beginning of a number
                    // If the current token is not empty, throw a lexical analysis error
                    if (!isCurrentTokenEmpty()) {
                        throw new LexicalAnalysisError(c, lineNum, columnNum);
                    }

                    // Save the column number as the beginning of the current token
                    tokenBeginColumnNum = columnNum;

                    // Append the current character to the current token string builder
                    currentTokenStringBuilder.append(c);

                    // Set the current token as a number
                    setNumericCurrentToken();

                    // Move to the next character
                    advance();

                    break;
                case '.':
                    // Dot is only allowed in a real number
                    // So before processing the dot, the current token must be an integer
                    // If the current token is not an integer, throw a lexical analysis error
                    if (!isCurrentTokenInteger()) {
                        throw new LexicalAnalysisError('.', lineNum, columnNum);
                    }

                    // Append the current character to the current token string builder
                    currentTokenStringBuilder.append(c);

                    // Set the current token as a real number
                    setRealCurrentToken();

                    // Move to the next character
                    advance();

                    break;
                case '\'':
                    // Quote is only allowed as a first and only character of a token
                    // If the current token is not empty, throw a lexical analysis error
                    if (!isCurrentTokenEmpty()) {
                        throw new LexicalAnalysisError('\'', lineNum, columnNum);
                    }

                    // Add a token with the quote token type
                    tokens.add(new Token(TokenType.QUOTE, new TokenSpan(lineNum, columnNum, columnNum + 1)));

                    // Move to the next character
                    advance();

                    break;
                case 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                     'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                     'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z':
                    // If the current token is empty, save the column number as the beginning of the current token
                    if (isCurrentTokenEmpty()) {
                        tokenBeginColumnNum = columnNum;
                    }

                    // Letter can not be part of a number
                    // If the current token is a number, throw a lexical analysis error
                    if (isCurrentTokenNumeric()) {
                        throw new LexicalAnalysisError(c, lineNum, columnNum);
                    }

                    // Append the current character to the current token string builder
                    currentTokenStringBuilder.append(c);

                    // Move to the next character
                    advance();

                    break;
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
                    // If the current token is empty,
                    // save the column number as the beginning of the current token
                    // and set the current token as a number
                    if (isCurrentTokenEmpty()) {
                        tokenBeginColumnNum = columnNum;
                        setNumericCurrentToken();
                    }

                    // Append the current character to the current token string builder
                    currentTokenStringBuilder.append(c);

                    // Set the current token as a number
                    advance();

                    break;
                case '\0':
                    // Handle the current token and reset the current token string builder
                    handleAndResetCurrentToken();
                    return tokens;
            }
        }
    }
}
