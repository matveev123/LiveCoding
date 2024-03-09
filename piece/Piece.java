package livecoding.piece;

import livecoding.Color;
import livecoding.Coordinates;

public abstract class Piece {

    public Color color;
    public Coordinates coordinates;


    public Piece(Color color, Coordinates coordinates) {
        this.color = color;
        this.coordinates = coordinates;
    }
}
