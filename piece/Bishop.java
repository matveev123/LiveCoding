package livecoding.piece;

import livecoding.Color;
import livecoding.Coordinates;

import java.util.HashSet;
import java.util.Set;

public class Bishop extends LongRagePiece implements IBishop {

    public Bishop(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        return getBishopMoves();//!
    }


}
