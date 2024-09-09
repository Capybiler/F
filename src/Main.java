import lexer.Lexer;
import lexer.Token;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String input = "(setq x +5)";
        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.lex();

        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}
