package chess;

public class MoveCommand implements Command{
    private ChessGame game;
    private ChessPosition start;
    private ChessPosition end;
    private ChessPiece.PieceType promotion;
    private ChessPiece movedPiece;
    private ChessPiece capturedPiece;

    public MoveCommand(ChessGame game, ChessMove move){
        this.game = game;
        this.start = move.getStartPosition();
        this.end = move.getEndPosition();
        this.promotion = move.getPromotionPiece();

    }

    @Override
    public void execute() {
        this.movedPiece = this.game.getBoard().getPiece(this.start);
        this.capturedPiece = this.game.getBoard().getPiece(this.end);
        this.movePiece(this.game, this.start,this.end);

    }

    @Override
    public void undo() {
        this.game.getBoard().removePiece(this.end);
        this.game.getBoard().addPiece(this.end, this.capturedPiece);
        this.game.getBoard().addPiece(this.start, this.movedPiece);
    }

    private void movePiece(ChessGame game, ChessPosition start, ChessPosition end){
        if (this.capturedPiece != null){
            game.getBoard().removePiece(end);
        }
        game.getBoard().removePiece(start);
        if (this.promotion == null) {
            game.getBoard().addPiece(end, this.movedPiece);
        }
        else{
            game.getBoard().addPiece(end, new ChessPiece(this.movedPiece.getTeamColor(), this.promotion));
        }
    }
}
