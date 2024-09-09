package lexer;


public enum TokenType {
    // Special forms
    QUOTE,
    SETQ,
    FUNC,
    LAMBDA,
    PROG,
    COND,
    WHILE,
    RETURN,
    BREAK,

    // Predefined functions
    PLUS,
    MINUS,
    TIMES,
    DIVIDE,
    HEAD,
    TAIL,
    CONS,
    EQUAL,
    NONEQUAL,
    LESS,
    LESSEQ,
    GREATER,
    GREATEREQ,
    ISINT,
    ISREAL,
    ISBOOL,
    ISNULL,
    ISATOM,
    ISLIST,
    AND,
    OR,
    XOR,
    NOT,
    EVAL,

    // Literals
    INTEGER,
    REAL,
    BOOLEAN,
    NULL,

    // Other
    IDENTIFIER,
    LEFT_PAREN,
    RIGHT_PAREN,
    EOF
}
