import lexer.Lexer;
import lexer.Token;
import parser.Parser;
import parser.nodes.ProgramASTNode;
import utils.FileReader;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 1 || args.length > 3) {
            System.out.println("Usage: java Main <input-file> [--with-indent] [--with-json]");
            System.exit(1);
        }

        boolean withIndent = false;
        boolean withJson = false;
        boolean withStandardOutput = false;
        boolean rawExceptions = false;

        for (String arg : args) {
            if (arg.equals("--with-indent")) {
                withIndent = true;
            } else if (arg.equals("--with-json")) {
                withJson = true;
            } else if (arg.equals("--with-standard-output")) {
                withStandardOutput = true;
            } else if (arg.equals("--raw-exceptions")) {
                rawExceptions = true;
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
            if (rawExceptions) {
                throw e;
            }
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        try {
            Lexer lexer = new Lexer(input);
            tokens = lexer.lex();
        } catch (Exception e) {
            if (rawExceptions) {
                throw e;
            }
            System.out.println("Error lexing input: " + e.getMessage());
            return;
        }

        assert tokens != null;

        try {
            Parser parser = new Parser(tokens);
            tree = parser.parse();
        } catch (Exception e) {
            if (rawExceptions) {
                throw e;
            }
            System.out.println("Error parsing input: " + e.getMessage());
            return;
        }

        assert tree != null;

        try {
            tree.analyze();
        } catch (Exception e) {
            if (rawExceptions) {
                throw e;
            }
            System.out.println("Error analyzing AST: " + e.getMessage());
            return;
        }

        try {
            tree.optimize();
        } catch (Exception e) {
            if (rawExceptions) {
                throw e;
            }
            System.out.println("Error optimizing AST: " + e.getMessage());
            return;
        }

        try {
            if (withStandardOutput) {
                System.out.println(tree);
            }

            if (withIndent) {
                System.out.println(tree.toStringWithIndent(0));
            }

            if (withJson) {
                System.out.println(tree.toJson());
            }

        } catch (Exception e) {
            if (rawExceptions) {
                throw e;
            }
            System.out.println("Error printing AST: " + e.getMessage());
        }

        try {
            tree.interpret();
        } catch (Exception e) {
            if (rawExceptions) {
                throw e;
            }
            System.out.println("Error interpreting AST: " + e.getMessage());
        }
    }
}
