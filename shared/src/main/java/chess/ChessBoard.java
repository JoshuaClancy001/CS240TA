package chess;

import java.util.Arrays;
import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    private final ChessPiece [][] board = new ChessPiece[9][9];
    private ChessPosition blackKingPosition;
    private ChessPosition whiteKingPosition;


    public ChessPosition getBlackKingPosition(){
        return this.blackKingPosition;
    }

    public ChessPosition getWhiteKingPosition(){
        return this.whiteKingPosition;
    }

    public void setBlackKingPosition(ChessPosition blackKingPosition) {
        this.blackKingPosition = blackKingPosition;
    }

    public void setWhiteKingPosition(ChessPosition whiteKingPosition) {
        this.whiteKingPosition = whiteKingPosition;
    }

    public ChessBoard() {

    }

    public void setKings(){
        for(int i = 1; i < 9; i++){
            for (int j = 1; j < 9; j++){
                if (this.getPiece(new ChessPosition(i,j)) != null && this.getPiece(new ChessPosition(i,j)).getPieceType() == ChessPiece.PieceType.KING){
                    if (this.getPiece(new ChessPosition(i,j)).getTeamColor() == ChessGame.TeamColor.WHITE){
                        this.whiteKingPosition = new ChessPosition(i,j);
                    }
                    else{
                        this.blackKingPosition = new ChessPosition(i,j);
                    }
                }
            }
        }
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        this.board[position.getRow()][position.getColumn()] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return this.board[position.getRow()][position.getColumn()];
    }

    public void removePiece(ChessPosition position){
        this.board[position.getRow()][position.getColumn()] = null;
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        ChessPiece.PieceType[] backRow = {
                null,
                ChessPiece.PieceType.ROOK,
                ChessPiece.PieceType.KNIGHT,
                ChessPiece.PieceType.BISHOP,
                ChessPiece.PieceType.QUEEN,
                ChessPiece.PieceType.KING,
                ChessPiece.PieceType.BISHOP,
                ChessPiece.PieceType.KNIGHT,
                ChessPiece.PieceType.ROOK
        };

        for (int col = 1; col < 9; col++) {
            addPiece(new ChessPosition(1, col ), new ChessPiece(ChessGame.TeamColor.WHITE, backRow[col]));
            addPiece(new ChessPosition(2, col ), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
        }

        // Set up black pieces
        for (int col = 1; col < 9; col++) {
            addPiece(new ChessPosition(8, col ), new ChessPiece(ChessGame.TeamColor.BLACK, backRow[col]));
            addPiece(new ChessPosition(7, col ), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
        }

        // Set up white pieces
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessBoard that = (ChessBoard) o;
        return Objects.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }
}
