package livecoding;

import livecoding.board.Board;
import livecoding.piece.GameState;

public abstract class GameStateChecker {
    public abstract GameState check(Board board, Color color);
}
