package org.geepacific.daniel.chessboard;

import java.util.List;

public class ChessBoard {

    private ChessMan[][] mainChessBoard = new ChessMan[10][10];

    public void putChessMansOnTheChessBoard(List<ChessMan> chessManList) {
        for (ChessMan chessMan: chessManList) {
            mainChessBoard[chessMan.getxAxis()][chessMan.getyAxis()] = chessMan;
        }
    }

    public void removeChessManFromTheOldPosition(ChessMan chessMan) {
        mainChessBoard[chessMan.getxAxis()][chessMan.getyAxis()] = null;
    }
    public void putChessManToTheNewPosition(ChessMan chessMan) {
        mainChessBoard[chessMan.getxAxis()][chessMan.getyAxis()] = chessMan;
    }

    public ChessMan[][] getMainChessBoard() {
        return mainChessBoard;
    }

    public void setMainChessBoard(ChessMan[][] mainChessBoard) {
        this.mainChessBoard = mainChessBoard;
    }
}
