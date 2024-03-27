package livecoding.piece;

import livecoding.board.Board;
import livecoding.Color;
import livecoding.Coordinates;
import livecoding.board.BoardUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pawn extends Piece {
    public Pawn(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {// ходит толкьо веперёд
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
    protected Set<CoordinatesShift> getPiecesAttacks() {// атакует только по диагонали
        Set<CoordinatesShift> result = new HashSet<>();

        if (color == color.WHITE) {
            result.add(new CoordinatesShift(-1, 1));
            result.add(new CoordinatesShift(1, 1));
        } else {
            result.add(new CoordinatesShift(-1, -1));
            result.add(new CoordinatesShift(1, -1));
        }
        return result;
    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        if (this.coordinates.file == coordinates.file) {//this.coordiantes - выбрыная нами фигура,а -coordinates- целевая фигура
            int rankShift = Math.abs(this.coordinates.rank - coordinates.rank);

            if (rankShift == 2) {
                List<Coordinates> between = BoardUtils.getVerticalCoordinatesBetween(this.coordinates, coordinates);
                return (board.isSquareEmpty(between.get(0))) && board.isSquareEmpty(coordinates);
            } else
                return board.isSquareEmpty(coordinates);
        } else {
            if (board.isSquareEmpty(coordinates))
                return false;
            else {// взятие
                return board.getPiece(coordinates).color != color;
            }
        }
    }
}
