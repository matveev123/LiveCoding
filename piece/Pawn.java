package livecoding.piece;

import livecoding.Board;
import livecoding.Color;
import livecoding.Coordinates;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
    public Pawn(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        Set<CoordinatesShift> result = new HashSet<>();

        if (Color.WHITE == color) {
            if (coordinates.rank == 2) {
                result.add(new CoordinatesShift(0, 2));
            }
            result.add(new CoordinatesShift(0, 1));
            result.add(new CoordinatesShift(-1, 1));
            result.add(new CoordinatesShift(1, 1));
        } else {
            result.add(new CoordinatesShift(-1, -1));
            result.add(new CoordinatesShift(1, -1));
            if (coordinates.rank == 7) {
                result.add(new CoordinatesShift(0, -2));
            }
            if (Color.BLACK == color) {
                result.add(new CoordinatesShift(0, -1));
            }
        }
        return result;
    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        if (this.coordinates.file == coordinates.file) {//this.coordiantes - выбрыная нами фигура,а -coordinates- целевая фигура
            return board.isSquareIsEmpty(coordinates);
        } else {
            if (board.isSquareIsEmpty(coordinates))
                return false;
            else {// взятие
                return board.getPiece(coordinates).color != color;
            }
        }

    }
}
