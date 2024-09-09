package lexer;


public class TokenSpan {
    private final long lineNum, columnBegin, columnEnd;

    public TokenSpan(long lineNum, long columnBegin, long columnEnd) {
        this.lineNum = lineNum;
        this.columnBegin = columnBegin;
        this.columnEnd = columnEnd;
    }

    public long getLineNum() {
        return lineNum;
    }

    public long getColumnBegin() {
        return columnBegin;
    }

    public long getColumnEnd() {
        return columnEnd;
    }

    @Override
    public String toString() {
        return "line " + lineNum + ", columns " + columnBegin + "-" + columnEnd;
    }
}
