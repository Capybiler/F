package parser;

import lexer.Token;
import lexer.TokenType;
import parser.nodes.*;

import java.util.ArrayList;
import java.util.List;


public class Parser {
    private final List<Token> tokens;
    private int currentPosition = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public ASTNode parse() {
        return parseExpression();
    }

    private boolean hasNext() {
        return currentPosition < tokens.size();
    }

    private Token currentToken() {
        if (currentPosition >= tokens.size()) {
            throw new RuntimeException("Unexpected end of input");
        }
        return tokens.get(currentPosition);
    }

    private Token advance() {
        return tokens.get(currentPosition++);
    }

    private boolean match(TokenType type) {
        return hasNext() && currentToken().getType() == type;
    }

    private ASTNode parseExpression() {
        if (match(TokenType.LEFT_PAREN)) {
            return parseList();
        } else if (currentToken().isLiteral()) {
            return parseLiteral();
        } else if (currentToken().isIdentifier()) {
            return parseAtom();
        } else if (match(TokenType.QUOTE)) {
            return parseQuote();
        } else if (match(TokenType.SETQ)) {
            return parseSetq();
        } else if (match(TokenType.FUNC)) {
            return parseFunc();
        } else if (match(TokenType.PROG)) {
            return parseProg();
        } else if (match(TokenType.COND)) {
            return parseCond();
        } else if (match(TokenType.WHILE)) {
            return parseWhile();
        } else if (match(TokenType.RETURN)) {
            return parseReturn();
        } else if (match(TokenType.BREAK)) {
            return parseBreak();
        } else {
            throw new RuntimeException("Unexpected token: " + currentToken());
        }
    }

    private ASTNode parseList() {

        advance();

        List<ASTNode> elements = new ArrayList<>();

        while (!match(TokenType.RIGHT_PAREN)) {
            if (!hasNext()) {
                throw new RuntimeException("Expected closing parenthesis");
            }
            elements.add(parseExpression());
        }


        advance();

        if (elements.size() == 1 && elements.getFirst().isSpecialForm()) {
            return elements.getFirst();
        }

        return new ListASTNode(elements);
    }

    private ASTNode parseLiteral() {
        Token literal = advance();

        switch (literal.getType()) {
            case INTEGER:
                return new LiteralASTNode(literal.getIntValue());
            case REAL:
                return new LiteralASTNode(literal.getRealValue());
            case BOOLEAN:
                return new LiteralASTNode(literal.getBoolValue());
            case NULL:
                return new LiteralASTNode(null);
            default:
                throw new RuntimeException("Unexpected literal type: " + literal);
        }
    }

    private ASTNode parseAtom() {
        Token atom = advance();
        return new AtomASTNode(atom.getIdentifier());
    }

    private ASTNode parseQuote() {

        advance();
        ASTNode quotedElement = parseExpression();
        return new QuoteASTNode(quotedElement);
    }

    private ASTNode parseSetq() {

        advance();

        if (!match(TokenType.IDENTIFIER)) {
            throw new RuntimeException("Expected identifier after setq");
        }

        AtomASTNode variable = (AtomASTNode) parseAtom();
        ASTNode value = parseExpression();

        return new SetqASTNode(variable, value);
    }

    private ASTNode parseFunc() {

        advance();

        if (!match(TokenType.IDENTIFIER)) {
            throw new RuntimeException("Expected function name after func");
        }

        AtomASTNode functionName = (AtomASTNode) parseAtom();


        if (!match(TokenType.LEFT_PAREN)) {
            throw new RuntimeException("Expected '(' after function name");
        }
        advance();

        List<AtomASTNode> parameters = new ArrayList<>();
        while (!match(TokenType.RIGHT_PAREN)) {
            parameters.add((AtomASTNode) parseAtom());
        }
        advance();


        ASTNode body = parseExpression();

        return new FuncASTNode(functionName, parameters, body);
    }

    private ASTNode parseCond() {

        advance();

        ASTNode condition = parseExpression();
        ASTNode trueBranch = parseExpression();
        ASTNode falseBranch = parseExpression();

        return new CondASTNode(condition, trueBranch, falseBranch);
    }

    private ASTNode parseProg() {

        advance();

        List<AtomASTNode> localVars = new ArrayList<>();
        if (match(TokenType.LEFT_PAREN)) {
            advance();

            while (!match(TokenType.RIGHT_PAREN)) {
                localVars.add((AtomASTNode) parseAtom());
            }

            advance();
        }

        ASTNode body = parseExpression();

        return new ProgASTNode(localVars, body);
    }

    private ASTNode parseWhile() {

        advance();

        ASTNode condition = parseExpression();
        ASTNode body = parseExpression();

        return new WhileASTNode(condition, body);
    }

    private ASTNode parseReturn() {

        advance();
        ASTNode value = parseExpression();
        return new ReturnASTNode(value);
    }

    private ASTNode parseBreak() {
        advance();
        return new BreakASTNode();
    }
}
