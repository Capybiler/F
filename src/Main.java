import lexer.Lexer;
import lexer.Token;
import parser.Parser;
import parser.nodes.ASTNode;
import utils.FileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1 && args.length != 2) {
            System.out.println("Usage: java Main <input-file> --with-indent");
            System.exit(1);
        }

        String input = "";
        List<Token> tokens = null;
        ASTNode tree = null;

        try {
            String inputFilePath = args[0];
            FileReader fileReader = new FileReader();
            input = fileReader.readFile(inputFilePath);
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        try {
            Lexer lexer = new Lexer(input);
            tokens = lexer.lex();
        } catch (Exception e) {
            System.out.println("Error lexing input: " + e.getMessage());
        }

        assert tokens != null;

        try {
            Parser parser = new Parser(tokens);
            tree = parser.parse();
        } catch (Exception e) {
            System.out.println("Error parsing input: " + e.getMessage());
        }

        assert tree != null;

        try {
            if (args.length == 2 && args[1].equals("--with-indent")) {
                System.out.println(tree.toStringWithIndent(0));
            } else {
                System.out.println(tree);
            }
        } catch (Exception e) {
            System.out.println("Error printing AST: " + e.getMessage());
        }
    }
}
