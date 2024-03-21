package livecoding;

public enum Color {
    WHITE, BLACK;

    public Color opposite() {
        return (this == Color.WHITE ? BLACK : WHITE);
    }
}
