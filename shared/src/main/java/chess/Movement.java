package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Movement {


   private HashMap<String, Pair<Integer, Integer>> directions = new HashMap<>();
   private HashMap<String, Pair<Integer, Integer>> knightDirections = new HashMap<>();


    public boolean isOffBoard(ChessBoard board, ChessPosition newPos){
        return (newPos.getRow() < 1 || newPos.getRow() > 8 || newPos.getColumn() < 1 || newPos.getColumn() > 8);
    }

    public void move(ChessBoard board, Collection<ChessMove> moves, int range,ChessPosition ogPos, int rowInc, int colInc ){
        for (int i = 1; i < range; i++){
            ChessPosition newPos = new ChessPosition(ogPos.getRow() + i * rowInc, ogPos.getColumn() + i * colInc);
            if (this.isOffBoard(board, newPos)){
                return;
            }
            else if (board.getPiece(newPos) != null){
                if (board.getPiece(ogPos).getTeamColor() == board.getPiece(newPos).getTeamColor()){
                    // it is your piece here so don't add this move
                    return;
                }
                else{
                    // it is not your piece so add this as a potential and then break
                    moves.add(new ChessMove(ogPos,newPos,null));
                    return;
                }
            }
            else{
                moves.add(new ChessMove(ogPos,newPos,null));
            }

        }
    }

    public Movement(){
        this.directions.put("right", new Pair<>(0,1));
        this.directions.put("left", new Pair<>(0, -1));
        this.directions.put("down", new Pair<>(-1, 0));
        this.directions.put("up", new Pair<>(1,0));
        this.directions.put("upright", new Pair<>(1,1));
        this.directions.put("upleft", new Pair<>(1,-1));
        this.directions.put("downright", new Pair<>(-1,1));
        this.directions.put("downleft", new Pair<>(-1,-1));
        this.knightDirections.put("up2right1", new Pair<>(2, 1));
        this.knightDirections.put("up2left1", new Pair<>(2, -1));
        this.knightDirections.put("up1right2", new Pair<>(1,2));
        this.knightDirections.put("up1left2", new Pair<>(1, -2));
        this.knightDirections.put("down2right1", new Pair<>(-2, 1));
        this.knightDirections.put("down2left1", new Pair<>(-2, -1));
        this.knightDirections.put("down1right2", new Pair<>(-1, 2));
        this.knightDirections.put("down1left2", new Pair<>(-1, -2));
    }

    public Collection<ChessMove> BishopMoves(ChessBoard board, ChessPosition ogPosition, int range){
        Collection<ChessMove> newMoves = new ArrayList<>();

        this.move(board, newMoves, range,  ogPosition,this.directions.get("upright").first, this.directions.get("upright").second );
        // diagonal down left
        this.move(board, newMoves, range,  ogPosition, this.directions.get("downleft").first, this.directions.get("downleft").second );
        // diagonal down right
        this.move(board, newMoves, range,  ogPosition, this.directions.get("downright").first, this.directions.get("downright").second );
        // diagonal up left
        this.move(board, newMoves, range,  ogPosition, this.directions.get("upleft").first, this.directions.get("upleft").second );

        return newMoves;
    }

    public Collection<ChessMove> RookMoves(ChessBoard board, ChessPosition ogPosition, int range){
        Collection<ChessMove> newMoves = new ArrayList<>();
        // 1 to the right
        this.move(board, newMoves, range, ogPosition, this.directions.get("right").first, this.directions.get("right").second );
        // 1 to the left
        this.move(board, newMoves, range,ogPosition,this.directions.get("left").first, this.directions.get("left").second  );
        // 1 up
        this.move(board, newMoves, range,   ogPosition, this.directions.get("up").first, this.directions.get("up").second );
        // 1 down
        this.move(board, newMoves, range,  ogPosition, this.directions.get("down").first, this.directions.get("down").second );
        return newMoves;
    }

    public Collection<ChessMove> KnightMoves(ChessBoard board, ChessPosition ogPosition){
        Collection<ChessMove> newMoves = new ArrayList<>();
        this.move(board, newMoves, 2, ogPosition, this.knightDirections.get("up2right1").first, this.knightDirections.get("up2right1").second );
        this.move(board, newMoves, 2, ogPosition, this.knightDirections.get("up2left1").first, this.knightDirections.get("up2left1").second );
        this.move(board, newMoves, 2, ogPosition, this.knightDirections.get("up1right2").first, this.knightDirections.get("up1right2").second );
        this.move(board, newMoves, 2, ogPosition, this.knightDirections.get("up1left2").first, this.knightDirections.get("up1left2").second );
        this.move(board, newMoves, 2, ogPosition, this.knightDirections.get("down2right1").first, this.knightDirections.get("down2right1").second );
        this.move(board, newMoves, 2, ogPosition, this.knightDirections.get("down2left1").first, this.knightDirections.get("down2left1").second );
        this.move(board, newMoves, 2, ogPosition, this.knightDirections.get("down1right2").first, this.knightDirections.get("down1right2").second );
        this.move(board, newMoves, 2, ogPosition, this.knightDirections.get("down1left2").first, this.knightDirections.get("down1left2").second );
        return newMoves;
    }

    public Collection<ChessMove> KingQueenMoves(ChessBoard board, ChessPosition ogPosition, String piece){
        Collection<ChessMove> newMoves = new ArrayList<>();
        int range;
        if (piece.equals("Queen")){
            range = 9;
        }
        else{
            range = 2;
        }
        newMoves.addAll(this.RookMoves(board, ogPosition, range));
        newMoves.addAll(this.BishopMoves(board,ogPosition, range));
        return newMoves;
    }

    public ArrayList<ChessMove> pawnMoves(ChessBoard board, ChessPosition ogPosition) {
        ArrayList<ChessMove> newMoves = new ArrayList<>();
        ChessPiece piece = board.getPiece(ogPosition);
        int direction = (piece.getTeamColor() == ChessGame.TeamColor.WHITE) ? 1 : -1;
        int startRow = (piece.getTeamColor() == ChessGame.TeamColor.WHITE) ? 2 : 7;
        int endRow = (piece.getTeamColor() == ChessGame.TeamColor.WHITE) ? 8 : 1;

        // Forward one square
        ChessPosition forwardOne = new ChessPosition(ogPosition.getRow() + direction, ogPosition.getColumn());
        // if it's off the board and there isn't another piece there add the move
        if (!isOffBoard(board, forwardOne) && board.getPiece(forwardOne) == null) {
            addPawnMoveWithPromotionCheck(newMoves, ogPosition, forwardOne, endRow);

            // if it's the starting row and that spot doesn't already have a piece
            if (ogPosition.getRow() == startRow) {
                ChessPosition forwardTwo = new ChessPosition(ogPosition.getRow() + 2 * direction, ogPosition.getColumn());
                if (!isOffBoard(board, forwardTwo) && board.getPiece(forwardTwo) == null) {
                    newMoves.add(new ChessMove(ogPosition, forwardTwo, null));
                }
            }
        }

        // Captures on the diagonals
        // Check both up and right and up and left
        int[] diagCols = { ogPosition.getColumn() - 1, ogPosition.getColumn() + 1 };
        for (int col : diagCols) {
            ChessPosition diagonal = new ChessPosition(ogPosition.getRow() + direction, col);
            if (!isOffBoard(board, diagonal)) {
                ChessPiece target = board.getPiece(diagonal);
                // if there's something there and its not your own piece add the move
                if (target != null && target.getTeamColor() != piece.getTeamColor()) {
                    addPawnMoveWithPromotionCheck(newMoves, ogPosition, diagonal, endRow);
                }
            }
        }

        return newMoves;
    }

    // Adds move and handles promotion
    private void addPawnMoveWithPromotionCheck(ArrayList<ChessMove> moves, ChessPosition from, ChessPosition to, int endRow) {
        if (to.getRow() == endRow) {
            moves.add(new ChessMove(from, to, ChessPiece.PieceType.QUEEN));
            moves.add(new ChessMove(from, to, ChessPiece.PieceType.ROOK));
            moves.add(new ChessMove(from, to, ChessPiece.PieceType.BISHOP));
            moves.add(new ChessMove(from, to, ChessPiece.PieceType.KNIGHT));
        } else {
            moves.add(new ChessMove(from, to, null));
        }
    }
}
