package livecoding.piece;

import livecoding.Color;
import livecoding.Coordinates;

import java.util.Set;

public class Queen extends LongRagePiece implements IRook, IBishop {
    public Queen(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        Set<CoordinatesShift> moves = getBishopMoves();
        moves.addAll(getRookMooves());
        return moves;

    }
}
