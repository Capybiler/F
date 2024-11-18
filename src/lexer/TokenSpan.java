package lexer;


public class TokenSpan {
    private final int lineNum, columnBegin, columnEnd;

    public TokenSpan(int lineNum, int columnBegin, int columnEnd) {
        this.lineNum = lineNum;
        this.columnBegin = columnBegin;
        this.columnEnd = columnEnd;
    }

    public int getLineNum() {
        return lineNum;
    }

    public int getColumnBegin() {
        return columnBegin;
    }

    public int getColumnEnd() {
        return columnEnd;
    }

    @Override
    public String toString() {
        return "line " + lineNum + ", columns " + columnBegin + "-" + columnEnd;
    }
}
