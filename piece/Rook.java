package livecoding.piece;

import livecoding.Color;
import livecoding.Coordinates;

import java.util.HashSet;
import java.util.Set;

public class Rook extends LongRagePiece implements IRook {
    public Rook(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        return getRookMooves();
    }


}
