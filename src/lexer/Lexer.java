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
    private final List<Token> tokens;
    private final StringBuilder currentTokenStringBuilder;
    private final Map<String, TokenType> keywords;

    public Lexer(String input) {
        this.input = input;
        this.length = input.length();

        this.index = 0;
        this.lineNum = 1;
        this.columnNum = 1;
        this.tokenBeginLineNum = 1;
        this.tokenBeginColumnNum = 1;
        this.nextIsNewLine = false;

        this.tokens = new ArrayList<>();
        this.currentTokenStringBuilder = new StringBuilder();

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

    private char peek() {
        if (index >= length) {
            return '\0';
        }
        return input.charAt((int) index);
    }

    private void advance() {
        if (index >= length) {
            return;
        }

        if (nextIsNewLine) {
            lineNum++;
            columnNum = 1;
            nextIsNewLine = false;
        }

        columnNum++;
        index++;
    }

    private void handlePreviousToken() {
        if (currentTokenStringBuilder.isEmpty()) {
            return;
        }

        String tokenString = currentTokenStringBuilder.toString();
        long tokenEndColumnNum = tokenBeginColumnNum + tokenString.length() - 1;
        TokenType tokenType = keywords.get(tokenString);


        if (tokenType != null) {
            tokens.add(new Token(tokenType, new TokenSpan(tokenBeginLineNum, tokenBeginColumnNum, tokenEndColumnNum)));
        } else {
            try {
                long intValue = Long.parseLong(tokenString);
                tokens.add(new Token(TokenType.INTEGER, new TokenSpan(tokenBeginLineNum, tokenBeginColumnNum, tokenEndColumnNum), intValue));
            } catch (NumberFormatException e1) {
                try {
                    double realValue = Double.parseDouble(tokenString);
                    tokens.add(new Token(TokenType.REAL, new TokenSpan(tokenBeginLineNum, tokenBeginColumnNum, tokenEndColumnNum), realValue));
                } catch (NumberFormatException e2) {
                    if (tokenString.equals("true") || tokenString.equals("false")) {
                        tokens.add(new Token(TokenType.BOOLEAN, new TokenSpan(tokenBeginLineNum, tokenBeginColumnNum, tokenEndColumnNum), tokenString.equals("true")));
                    } else if (tokenString.equals("null")) {
                        tokens.add(new Token(TokenType.NULL, new TokenSpan(tokenBeginLineNum, tokenBeginColumnNum, tokenEndColumnNum)));
                    } else {
                        tokens.add(new Token(TokenType.IDENTIFIER, new TokenSpan(tokenBeginLineNum, tokenBeginColumnNum, tokenEndColumnNum), tokenString));
                    }
                }
            }
        }
    }

    private void resetCurrentToken() {
        currentTokenStringBuilder.setLength(0);
        tokenBeginLineNum = lineNum;
        tokenBeginColumnNum = columnNum;
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
                        throw new RuntimeException("Unexpected character '+' at line " + lineNum + ", column " + columnNum);
                    }

                    tokenBeginColumnNum = columnNum;
                    currentTokenStringBuilder.append(c);
                    advance();

                    break;
                case '.':
                    if (isCurrentTokenEmpty()) {
                        throw new RuntimeException("Unexpected character '.' at line " + lineNum + ", column " + columnNum);
                    }

                    if (!Character.isDigit(prevCurrentToken())) {
                        currentTokenStringBuilder.append(c);
                        advance();
                    }
                    break;

                case '\'':
                    tokens.add(new Token(TokenType.QUOTE, new TokenSpan(lineNum, columnNum, columnNum + 1)));
                    advance();
                    break;
                case 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                     'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                     'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7',
                     '8', '9':
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
