package livecoding;

import livecoding.piece.Piece;

import java.util.Set;



public class Game {

    /*
               1. рендер доски.
               2.читать ввод с консоли
               3.ход.
               4. переход хода pass
                */
    private final Board board;


    private BoardConsoleRenderer renderer = new BoardConsoleRenderer();

    public Game(Board board) {
        this.board = board;
    }

    public void gameLoop() {
        boolean isWhiteToMove = true;

        while (true) {
            renderer.render(board);

            if (isWhiteToMove) {
                System.out.println("White to move");
            } else {
                System.out.println("Black to move");

            }

            Coordinates sourceCoordinates = InputCoordinates.inputPieceCoordinatesForColor(isWhiteToMove ? Color.WHITE : Color.BLACK, board);

            Piece piece = board.getPiece(sourceCoordinates);

            Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);

            renderer.render(board,piece);

            Coordinates targetCoordinates = InputCoordinates.inputAvailableSquare(availableMoveSquares);

            board.movePiece(sourceCoordinates, targetCoordinates);


            // pass move
            isWhiteToMove = !isWhiteToMove;
        }
    }
}
