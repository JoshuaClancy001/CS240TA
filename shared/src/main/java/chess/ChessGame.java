package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

   private ChessGame.TeamColor teamColor;
   private ChessBoard board;

    public ChessGame() {
        this.board = new ChessBoard();
        board.resetBoard();
        this.teamColor = TeamColor.WHITE;
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return this.teamColor;

    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.teamColor = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        ChessPiece startPiece = board.getPiece(startPosition);

        Collection<ChessMove> moves =  startPiece.pieceMoves(board, startPosition);
        Collection<ChessMove> validMoves = new ArrayList<>();


        for (ChessMove move: moves){
            MoveCommand moveCommand = new MoveCommand(this,move);
            moveCommand.execute();
            if (!isInCheck(startPiece.getTeamColor())){
                validMoves.add(move);
            }
            moveCommand.undo();
        }
        return validMoves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to perform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        this.board.setKings();
        if (this.board.getPiece(move.getStartPosition()) == null){
            throw new InvalidMoveException();
        }
        ChessPiece piece = board.getPiece(move.getStartPosition());
        Collection<ChessMove> possibleMoves = piece.pieceMoves(this.board,move.getStartPosition());
        if (!possibleMoves.contains(move)){
            throw new InvalidMoveException();
        }
        if (piece.getTeamColor() != this.getTeamTurn()){
            throw new InvalidMoveException();
        }
        MoveCommand moveCommand = new MoveCommand(this, move);
        moveCommand.execute();
        if (this.isInCheck(piece.getTeamColor())){
            moveCommand.undo();
            throw new InvalidMoveException();
        }
        TeamColor newTeamColor = (this.teamColor == TeamColor.WHITE) ? TeamColor.BLACK : TeamColor.WHITE;
        this.setTeamTurn(newTeamColor);
        if (piece.getPieceType() == ChessPiece.PieceType.KING){
            if (piece.getTeamColor() == TeamColor.WHITE){
                this.board.setWhiteKingPosition(move.getEndPosition());
            }
            else{
                this.board.setBlackKingPosition(move.getEndPosition());
            }
        }
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        this.board.setKings();
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 9; j++){
                if (this.board.getPiece(new ChessPosition(i,j)) != null && this.board.getPiece(new ChessPosition(i,j)).getTeamColor() != teamColor){
                    ChessPiece attacking = this.board.getPiece(new ChessPosition(i,j));
                    ChessPosition kingPosition = (teamColor.equals(TeamColor.WHITE)) ? this.board.getWhiteKingPosition() : this.board.getBlackKingPosition();
                    Collection<ChessMove> moves = attacking.pieceMoves(this.board, new ChessPosition(i,j));
                    for (ChessMove move: moves){
                        if (move.getEndPosition().equals(kingPosition)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (board.getPiece(new ChessPosition(i,j)) != null && board.getPiece(new ChessPosition(i,j)).getTeamColor() == teamColor){
                    Collection<ChessMove> moves = board.getPiece(new ChessPosition(i,j)).pieceMoves(board,new ChessPosition(i,j));
                    for (ChessMove move: moves){
                        MoveCommand moveCommand = new MoveCommand(this,move);
                        moveCommand.execute();
                        if (!isInCheck(teamColor)) {
                            moveCommand.undo();
                            return false;
                        }
                        else{
                            moveCommand.undo();
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        if (isInCheck(teamColor)) {
            return false;
        }
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                ChessPosition position = new ChessPosition(i, j);
                ChessPiece piece = board.getPiece(position);
                if (piece != null && piece.getTeamColor().equals(teamColor)) {
                    Collection<ChessMove> moves = piece.pieceMoves(board, position);
                    for (ChessMove move : moves) {
                        MoveCommand moveCommand = new MoveCommand(this, move);
                        moveCommand.execute();
                        boolean stillInCheck = isInCheck(teamColor);
                        moveCommand.undo();
                        if (!stillInCheck) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }


    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return this.board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessGame chessGame = (ChessGame) o;
        return teamColor == chessGame.teamColor && Objects.equals(board, chessGame.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamColor, board);
    }
}
