package livecoding.piece;

import livecoding.Board;
import livecoding.Color;
import livecoding.Coordinates;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {

    public Color color;
    public Coordinates coordinates;


    public Piece(Color color, Coordinates coordinates) {
        this.color = color;
        this.coordinates = coordinates;
    }

    public Set<Coordinates> getAvailableMoveSquares(Board board) {
        Set<Coordinates> result = new HashSet<>();
        for (CoordinatesShift shift : getPieceMoves()) {
            if (coordinates.canShift(shift)) {
                Coordinates newCoordinates = coordinates.shift(shift);

                if (isSquareAvailableForMove(newCoordinates, board)) {
                    result.add(newCoordinates);
                }
            }
        }
        return result;
    }

    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        return board.isSquareIsEmpty(coordinates) || board.getPiece(coordinates).color != color;
    }

    protected abstract Set<CoordinatesShift> getPieceMoves();// почему protected?

    protected Set<CoordinatesShift> getPiecesAttacks() {
        return getPieceMoves();
    }

    public Set<Coordinates> getAttackedSquares(Board board) {// куда фигура может атковать
        Set<CoordinatesShift> piecesAttacks = getPiecesAttacks();//сдвиги
        Set<Coordinates> result = new HashSet();//онкретные клетки

        for (CoordinatesShift piecesAttack : piecesAttacks) {
            if (coordinates.canShift(piecesAttack)) {
                Coordinates shiftedCoordinates = coordinates.shift(piecesAttack);//тек координаты фигуры сдвинуты на сдвиг(pieceAttack)
                if(isSquareAvailableForAttack(shiftedCoordinates,board)){
                    result.add(shiftedCoordinates);
                }
            }
        }

        return result;
    }

    protected boolean isSquareAvailableForAttack(Coordinates coordinates, Board board) {
        return true;
    }
}
