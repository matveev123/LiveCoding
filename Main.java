package livecoding;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.setDefaultPiecesPositions();

        BoardConsoleRenderer renderer = new BoardConsoleRenderer();
        renderer.render(board);


    }
}
