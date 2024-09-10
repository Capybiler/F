import lexer.Lexer;
import lexer.Token;
import utils.FileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <input-file>");
            System.exit(1);
        }

        String inputFilePath = args[0];

        FileReader fileReader = new FileReader();
        String  input = "";
        try {
            input = fileReader.readFile(inputFilePath);
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        try {
            Lexer lexer = new Lexer(input);
            List<Token> tokens = lexer.lex();

            for (Token token : tokens) {
                System.out.println(token);
            }
        } catch (Exception e) {
            System.out.println("Error lexing input: " + e.getMessage());
        }
    }
}
