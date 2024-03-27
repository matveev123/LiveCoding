package livecoding;


import livecoding.board.Board;
import livecoding.board.Move;
import livecoding.piece.GameState;

import java.util.Arrays;
import java.util.List;

public class Game {

    /*
               1. рендер доски.
               2.читать ввод с консоли
               3.ход.
               4. переход хода pass
                */
    private final Board board;

    private BoardConsoleRenderer renderer = new BoardConsoleRenderer();

    private final List<GameStateChecker> checkers = Arrays.asList(
            new StalemateGameStateChecker(),
            new CheckmateGameStateChecker()
    );

    public Game(Board board) {
        this.board = board;
    }



    public void gameLoop() {
        Color colorToMove = Color.WHITE;

        GameState state = determineGameState(board, colorToMove);

        while (state == GameState.ONGOING) {
            renderer.render(board);

            if (colorToMove == Color.WHITE) {
                System.out.println("White to move");
            } else {
                System.out.println("Black to move");

            }

            Move move = InputCoordinates.inputMove(board, colorToMove, renderer);

            board.makeMove(move);

            // pass move
            colorToMove = colorToMove.opposite();// отражение

            state = determineGameState(board, colorToMove);
        }

        renderer.render(board);// остояние доски
        System.out.println("Game ended with state: " + state);
    }

    private GameState determineGameState(Board board, Color color) {
        for (GameStateChecker checker : checkers) {
            GameState state = checker.check(board, color);

            if (state != GameState.ONGOING) {
                return state;
            }
        }
        return GameState.ONGOING;

    }
}
