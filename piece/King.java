package livecoding.piece;

import livecoding.Board;
import livecoding.Color;
import livecoding.Coordinates;

import java.util.HashSet;
import java.util.Set;

public class King extends Piece {

    public King(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        Set<CoordinatesShift> result = new HashSet<>();
        for (int fileShift = -1; fileShift <= 1; fileShift++) {
            for (int rankShft = -1; rankShft <= 1; rankShft++) {
                if (fileShift == 0 && rankShft == 0)
                    continue;
                result.add(new CoordinatesShift(fileShift, rankShft));
            }

        }
        return result;
    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        boolean result = super.isSquareAvailableForMove(coordinates, board);
        if (result) {
            return !board.isSquareAttackedByColor(coordinates, color.opposite());// доступность конкретной клетки
        }
        return false;
    }
}
