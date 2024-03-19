package livecoding;

import livecoding.piece.Piece;

import java.util.Set;

import static java.util.Collections.emptySet;

public class BoardConsoleRenderer {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE_PIECE_COLOR = "\u001B[97m";
    public static final String ANSI_BLACK_PIECE_COLOR = "\u001B[30m";

    public static final String ANSI_WHITE_SQUARE_BACKGROUND = "\u001B[47m";

    public static final String ANSI_BLACK_SQUARE_BACKGROUND = "\u001B[0;100m";

    public static final String ANSI_HIGHLIGHTED_SQUARE_BACKGROUND = "\u001B[45m";


    public void render(Board board, Piece pieceToMove) {
        Set<Coordinates> availableMoveSquares = emptySet();// зачем?

        if (pieceToMove != null) {
            availableMoveSquares = pieceToMove.getAvailableMoveSquares(board);
        }
        for (int rank = 8; rank >= 1; rank--) {
            String line = "";
            for (File file : File.values()) {
                Coordinates coordinates = new Coordinates(file, rank);
                boolean isHighLight = availableMoveSquares.contains(coordinates);

                if (board.isSquareIsEmpty(coordinates)) {
                    line += getSpriteForEmptySquare(coordinates, isHighLight);//blank
                } else {
                    line += getPieceSprite(board.getPiece(coordinates), isHighLight);
                }
            }
            line += ANSI_RESET;
            System.out.println(line);
        }
    }

    public String colorizedSprite(String sprite, Color pieceColor, boolean isSquareDark, boolean isHighLighted) {
        // format = background color + front color + text/;

        String result = sprite;

        if (pieceColor == Color.WHITE) {
            result = ANSI_WHITE_PIECE_COLOR + result;
        } else {
            result = ANSI_BLACK_PIECE_COLOR + result;
        }
        if (isHighLighted) {
            result = ANSI_HIGHLIGHTED_SQUARE_BACKGROUND + result;
        } else if (isSquareDark)
            result = ANSI_BLACK_SQUARE_BACKGROUND + result;
        else {
            result = ANSI_WHITE_SQUARE_BACKGROUND + result;
        }

        return result;
    }

    public void render(Board board) {
        render(board, null);
        ;
    }

    private String setPieceUnicodeSpriteForPiece(Piece piece) {
        switch (piece.getClass().getSimpleName()) {
            case "Pawn":
                return "♟︎";

            case "Knight":
                return "♞";

            case "Bishop":
                return "♝";

            case "Rook":
                return "♜";

            case "Queen":
                return "♛";

            case "King":
                return "♚";
        }
        return "";
    }

    public String getSpriteForEmptySquare(Coordinates coordinates, boolean isHighLight) {
        return colorizedSprite("   "+"\u2006\u2005", Color.WHITE, Board.isSquareDark(coordinates), isHighLight);// \u2009 \u2006
    }

    private String getPieceSprite(Piece piece, boolean isHighLight) {
        return colorizedSprite(" " + setPieceUnicodeSpriteForPiece(piece)+" ", piece.color, Board.isSquareDark(piece.coordinates), isHighLight);
    }
}