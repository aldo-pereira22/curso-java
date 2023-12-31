package chess.piece;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position){
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }

    private boolean testRookCastling(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return  p != null && p instanceof Rook &&
                    p.getColor() == getColor() && p.getMoveCount() == 0;
    }
    @Override
    public boolean[][] possibleMoves() {
        boolean mat[][] = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0,0);

//    ABOVE
        p.setValues(position.getRow() - 1, position.getColumn());
        if(getBoard().postionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
//    below
        p.setValues(position.getRow() + 1, position.getColumn());
        if(getBoard().postionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //    Left
        p.setValues(position.getRow(), position.getColumn() - 1);
        if(getBoard().postionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //    RIGHT
        p.setValues(position.getRow(), position.getColumn() + 1);
        if(getBoard().postionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //    NW - NOROESTE
        p.setValues(position.getRow() -1, position.getColumn() - 1);
        if(getBoard().postionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //    NE - NORDESTE
        p.setValues(position.getRow() -1, position.getColumn() + 1);
        if(getBoard().postionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //    SW - SUDOESTE
        p.setValues(position.getRow() + 1, position.getColumn() -1);
        if(getBoard().postionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //    SE - SUDESTE
        p.setValues(position.getRow() +1, position.getColumn()  + 1);
        if(getBoard().postionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
//         #Specialmove castling
        if(getMoveCount() == 0 && !chessMatch.getCheck()){
//            #Special move castling kingside rook
            Position post1 = new Position(position.getRow(), position.getColumn() + 3);
            if(testRookCastling(post1)){
                Position p1 = new Position(position.getRow(), position.getColumn() + 1);
                Position p2 = new Position(position.getRow(), position.getColumn() + 2);

                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null){
                    mat[position.getRow()][post1.getColumn() + 2] = true;
                }

            }

            //            #Special move castling queengside rook
            Position post2 = new Position(position.getRow(), position.getColumn() - 4);
            if(testRookCastling(post2)){
                Position p1 = new Position(position.getRow(), position.getColumn() - 1);
                Position p2 = new Position(position.getRow(), position.getColumn() - 2);
                Position p3 = new Position(position.getRow(), position.getColumn() - 3);

                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null){
                    mat[position.getRow()][post1.getColumn() - 2] = true;
                }

            }
        return mat;
    }

}
