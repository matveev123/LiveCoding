package livecoding.piece;

import livecoding.Color;
import livecoding.Coordinates;

import java.util.Set;

public class Pawn extends Piece{
    public Pawn(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        return null;
    }
}
