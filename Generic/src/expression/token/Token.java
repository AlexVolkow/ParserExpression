package expression.token;

/**
 * Created by Alexandr Volkov on 21.03.2017.
 */
public class Token {
    private TokenType type;
    private String value;
    private int idx;

    public Token(TokenType type, String value, int idx) {
        this.type = type;
        this.value = value;
        this.idx = idx;
    }

    public TokenType getType() {
        return type;
    }

    public int getIdx() {
        return idx;
    }

    public String getValue() {
        return value;
    }
}
