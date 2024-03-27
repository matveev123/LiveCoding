package livecoding;

import livecoding.board.Board;
import livecoding.board.BoardFactory;
import livecoding.board.Move;
import livecoding.piece.GameState;
import livecoding.piece.King;
import livecoding.piece.Piece;

import java.util.List;
import java.util.Set;

public class CheckmateGameStateChecker extends GameStateChecker {
    @Override
    public GameState check(Board board, Color color) {
        // check if king in check - ШАХ КОРОЛЮ!
        // check that there  is no move to prevent this check

        Piece king = board.getPiecesByColor(color).stream().filter(piece -> piece instanceof King).findFirst().get();


        if (!board.isSquareAttackedByColor(king.coordinates, color.opposite())) {
            return GameState.ONGOING;
        }

        List<Piece> pieces = board.getPiecesByColor(color);// все наши нашего цвета фигруы
        for (Piece piece : pieces) {
            Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);// се их ходы

            for (Coordinates coordinates : availableMoveSquares) {
                Board clone = new BoardFactory().copy(board);
                clone.makeMove(new Move(piece.coordinates, coordinates));// делаем ход


                Piece cloneKing = clone.getPiecesByColor(color).stream().filter(p -> p instanceof King).findFirst().get();

                if (!clone.isSquareAttackedByColor(cloneKing.coordinates, color.opposite())) {// сли есть ход от мата
                    return GameState.ONGOING;
                }
            }
        }

        if (color == Color.WHITE) {//  МАТ!!!
            return GameState.CHECKMATE_TO_WHITE_KING;
        } else {
            return GameState.CHECKMATE_TO_BLACK_KING;
        }

    }
}
