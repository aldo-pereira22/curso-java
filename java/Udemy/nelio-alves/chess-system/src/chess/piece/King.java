package chess.piece;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position){
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
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

        return mat;
    }

}
