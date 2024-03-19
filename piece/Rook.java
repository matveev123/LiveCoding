package livecoding.piece;

import livecoding.Board;
import livecoding.Color;
import livecoding.Coordinates;

import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {
    public Rook(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        Set<CoordinatesShift> result = new HashSet<>();

        for (int i = -7; i < 7; i++) {
            if (i == 0)
                continue;
            result.add(new CoordinatesShift(i, 0));
        }
        for (int i = -7; i < 7; i++) {
            if (i == 0) {
                continue;
            }
            result.add(new CoordinatesShift(0, i));
        }
        return result;
    }

    /*@Override
    public Set<Coordinates> getAvailableMoveSquares(Board board) {
        boolean result = isSquareAvailableForMove(coordinates, board);
        if (result) {

            return
        } else {
        }
    }*/
}
