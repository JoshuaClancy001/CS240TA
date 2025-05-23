package chess;

import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessGame.TeamColor pieceColor;
    private ChessPiece.PieceType pieceType;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceType = type;
        this.pieceColor = pieceColor;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return this.pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return this.pieceType;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPiece piece = board.getPiece(myPosition);
        Movement rules = new Movement();

        if (piece.getPieceType() == PieceType.KING){
            return rules.KingQueenMoves(board, myPosition, "King");
        }
        else if (piece.getPieceType() == PieceType.QUEEN){
            return rules.KingQueenMoves(board, myPosition, "Queen");
        }
        else if (piece.getPieceType() == PieceType.BISHOP){
            return rules.BishopMoves(board, myPosition, 9);
        }
        else if (piece.getPieceType() == PieceType.ROOK){
            return rules.RookMoves(board, myPosition, 9);
        }
        else if (piece.getPieceType() == PieceType.KNIGHT){
            return rules.KnightMoves(board,myPosition);
        }
        else if(piece.getPieceType() == PieceType.PAWN){
            return rules.pawnMoves(board,myPosition);
        }


        return new ArrayList<>();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && pieceType == that.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, pieceType);
    }
}
