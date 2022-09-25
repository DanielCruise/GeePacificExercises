import org.geepacific.daniel.chessboard.ChessBoard;
import org.geepacific.daniel.chessboard.ChessMan;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ChessBoardTest {
    @Test
    public void checkMoveUp() {
        ChessBoard chessBoard = new ChessBoard();
        ChessMan chessMan = new ChessMan(1, 5, 5, "N", List.of(new String[]{"M"}), "(5,5)", chessBoard);
        chessBoard.putChessMansOnTheChessBoard(List.of(new ChessMan[]{chessMan}));
        chessMan.moveForward();
        Assert.assertEquals(chessMan.positionToString(), "(5,6)");
    }

    @Test
    public void checkMoveDown() {
        ChessBoard chessBoard = new ChessBoard();
        ChessMan chessMan = new ChessMan(1, 5, 5, "S", List.of(new String[]{"M"}), "(5,5)", chessBoard);
        chessBoard.putChessMansOnTheChessBoard(List.of(new ChessMan[]{chessMan}));
        chessMan.moveForward();
        Assert.assertEquals(chessMan.positionToString(), "(5,4)");
    }

    @Test
    public void checkMoveToTheLeft() {
        ChessBoard chessBoard = new ChessBoard();
        ChessMan chessMan = new ChessMan(1, 5, 5, "W", List.of(new String[]{"M"}), "(5,5)", chessBoard);
        chessBoard.putChessMansOnTheChessBoard(List.of(new ChessMan[]{chessMan}));
        chessMan.moveForward();
        Assert.assertEquals(chessMan.positionToString(), "(4,5)");
    }

    @Test
    public void checkMovetoTheRight() {
        ChessBoard chessBoard = new ChessBoard();
        ChessMan chessMan = new ChessMan(1, 5, 5, "E", List.of(new String[]{"M"}), "(5,5)", chessBoard);
        chessBoard.putChessMansOnTheChessBoard(List.of(new ChessMan[]{chessMan}));
        chessMan.moveForward();
        Assert.assertEquals(chessMan.positionToString(), "(6,5)");
    }

    @Test
    public void checkMoveWhenAtTheLeftSide() {
        ChessBoard chessBoard = new ChessBoard();
        ChessMan chessMan = new ChessMan(1, 0, 5, "W", List.of(new String[]{"M"}), "(0,5)", chessBoard);
        chessBoard.putChessMansOnTheChessBoard(List.of(new ChessMan[]{chessMan}));

        chessMan.moveForward();
        Assert.assertEquals(chessMan.positionToString(), "(0,5)");
    }

    @Test
    public void checkMoveWhenAtTheRightSide() {
        ChessBoard chessBoard = new ChessBoard();
        ChessMan chessMan = new ChessMan(1, 9, 5, "E", List.of(new String[]{"M"}), "(9,5)", chessBoard);
        chessBoard.putChessMansOnTheChessBoard(List.of(new ChessMan[]{chessMan}));
        chessMan.moveForward();
        Assert.assertEquals(chessMan.positionToString(), "(9,5)");
    }

    @Test
    public void checkMoveWhenAtTheTopSide() {
        ChessBoard chessBoard = new ChessBoard();
        ChessMan chessMan = new ChessMan(1, 5, 9, "N", List.of(new String[]{"M"}), "(5,9)", chessBoard);
        chessBoard.putChessMansOnTheChessBoard(List.of(new ChessMan[]{chessMan}));
        chessMan.moveForward();
        Assert.assertEquals(chessMan.positionToString(), "(5,9)");
    }

    @Test
    public void checkMoveWhenAtTheBottomSide() {
        ChessBoard chessBoard = new ChessBoard();
        ChessMan chessMan = new ChessMan(1, 5, 0, "S", List.of(new String[]{"M"}), "(5,0)", chessBoard);
        chessBoard.putChessMansOnTheChessBoard(List.of(new ChessMan[]{chessMan}));
        chessMan.moveForward();
        Assert.assertEquals(chessMan.positionToString(), "(5,0)");
    }

    @Test
    public void checkMoveWhenAChessManIsAtTheNextMove() {
        ChessBoard chessBoard = new ChessBoard();
        ChessMan chessMan1 = new ChessMan(1, 5, 5, "S", List.of(new String[]{"M"}), "(5,5)", chessBoard);
        ChessMan chessMan2 = new ChessMan(2, 5, 4, "S", List.of(new String[]{"M"}), "(5,5)", chessBoard);
        List<ChessMan> chessManList = List.of(new ChessMan[]{chessMan1, chessMan2});
        chessBoard.putChessMansOnTheChessBoard(chessManList);
        chessMan1.moveForward();
        Assert.assertEquals(chessMan1.positionToString(), "(5,5)");
    }

}
