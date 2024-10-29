import lexer.Lexer;
import lexer.Token;
import parser.Parser;
import parser.nodes.ASTNode;
import parser.nodes.ProgramASTNode;
import utils.FileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1 || args.length > 3) {
            System.out.println("Usage: java Main <input-file> [--with-indent] [--with-json]");
            System.exit(1);
        }

        boolean withIndent = false;
        boolean withJson = false;
        for (String arg : args) {
            if (arg.equals("--with-indent")) {
                withIndent = true;
            } else if (arg.equals("--with-json")) {
                withJson = true;
            }
        }

        String input = "";
        List<Token> tokens = null;
        ProgramASTNode tree = null;

        try {
            String inputFilePath = args[0];
            FileReader fileReader = new FileReader();
            input = fileReader.readFile(inputFilePath);
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        try {
            Lexer lexer = new Lexer(input);
            tokens = lexer.lex();
        } catch (Exception e) {
            System.out.println("Error lexing input: " + e.getMessage());
            return;
        }

        assert tokens != null;

        try {
            Parser parser = new Parser(tokens);
            tree = parser.parse();
        } catch (Exception e) {
            System.out.println("Error parsing input: " + e.getMessage());
            return;
        }

        assert tree != null;

        try {
            tree.analyze();
        } catch (Exception e) {
            System.out.println("Error analyzing AST: " + e.getMessage());
            return;
        }

        try {
            tree.optimize();
        } catch (Exception e) {
            System.out.println("Error optimizing AST: " + e.getMessage());
            return;
        }

        try {
            System.out.println(tree);

            if (withIndent) {
                System.out.println(tree.toStringWithIndent(0));
            }

            if (withJson) {
                System.out.println(tree.toJson());
            }

        } catch (Exception e) {
            System.out.println("Error printing AST: " + e.getMessage());
        }
    }
}
