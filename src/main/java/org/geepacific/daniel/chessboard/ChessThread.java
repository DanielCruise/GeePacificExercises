package org.geepacific.daniel.chessboard;

public class ChessThread extends Thread {

    private ChessMan chessMan;

    ChessThread (ChessMan chessMan) {
        this.chessMan = chessMan;
    }

    public ChessMan getChessMan() {
        return chessMan;
    }

    public void setChessMan(ChessMan chessMan) {
        this.chessMan = chessMan;
    }

    @Override
    public void run() {
        for (String command: chessMan.getRoute()) {
            if ("L".equals(command) || "R".equals(command)) {
                chessMan.turn(command);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else if ("M".equals(command)) {
                chessMan.moveForward();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
