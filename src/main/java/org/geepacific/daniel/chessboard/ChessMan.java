package org.geepacific.daniel.chessboard;


import java.util.List;

public class ChessMan {
    private int id;
    private int xAxis;
    private int yAxis;
    private String direction;
    private List<String> route;
    private String positionString;
    private ChessBoard chessBoard;

    public ChessMan(int id, int xAxis, int yAxis, String direction, List<String> route, String positionString, ChessBoard chessBoard) {
        this.id = id;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.direction = direction;
        this.route = route;
        this.positionString = positionString;
        this.chessBoard = chessBoard;
    }


    public String positionToString() {
        return "(" + xAxis + "," + yAxis + ")";
    }

    public void turn(String newTurn) {
        switch (newTurn) {
            case "L" -> { // turn left
                // Change the chessman's direction
                switch (direction) {
                    case "N" -> // Current direction is the North
                            direction = Const.Direction.WEST;
                    case "W" -> // Current direction is the West
                            direction = Const.Direction.SOUTH;
                    case "S" -> // Current direction is the South
                            direction = Const.Direction.EAST;
                    case "E" -> // Current direction is the East
                            direction = Const.Direction.NORTH;
                }
                System.out.println("Chess " + id + ": Turned left. Current direction: " + direction + ".");
            }
            case "R" -> { // turn right
                // Change the chessman's direction
                switch (direction) {
                    case "N" -> // Current direction is the North
                            direction = Const.Direction.EAST;
                    case "W" -> // Current direction is the West
                            direction = Const.Direction.NORTH;
                    case "S" -> // Current direction is the South
                            direction = Const.Direction.WEST;
                    case "E" -> // Current direction is the East
                            direction = Const.Direction.SOUTH;
                }
                System.out.println("Chess " + id + ": Turned right. Current direction: " + direction + ".");
            }
        }
    }

    public synchronized void moveForward() {
        int currentxAxis = xAxis;
        int currentyAxis = yAxis;
        switch (direction) {
            // Ensure that no chessman will go outta the chessboard.
            case "N":
                if (yAxis < 9) {
                    if (chessBoard.getMainChessBoard()[currentxAxis][currentyAxis + 1] == null) {
                        chessBoard.removeChessManFromTheOldPosition(this);
                        yAxis++;
                        chessBoard.putChessManToTheNewPosition(this);
                        System.out.println("Chess " + id + ": Moved on. Current position: (" + xAxis + "," + yAxis + ").");
                    } else {
                        System.out.println("Chess " + id + ": There is another chessman on the position (" + xAxis + "," + (yAxis + 1) + ") of the next move. This move is cancelled!");
                    }
                } else {
                    System.out.println("Chess " + id + ": Reached the top side of the chessboard. Can't move.");
                }
                break;
            case "E":
                if (xAxis < 9) {
                    if (chessBoard.getMainChessBoard()[currentxAxis + 1][currentyAxis] == null) {
                        chessBoard.removeChessManFromTheOldPosition(this);
                        xAxis++;
                        chessBoard.putChessManToTheNewPosition(this);
                        System.out.println("Chess " + id + ": Moved on. Current position: (" + xAxis + "," + yAxis + ").");
                    } else {
                        System.out.println("Chess " + id + ": There is another chessman on the position (" + (xAxis + 1) + "," + yAxis + ") of the next move. This move is cancelled!");
                    }
                } else {
                    System.out.println("Chess " + id + ": Reached the right side of the chessboard. Can't move.");
                }
                break;
            case "W":
                if (xAxis > 0) {
                    if (chessBoard.getMainChessBoard()[currentxAxis - 1][currentyAxis] == null) {
                        chessBoard.removeChessManFromTheOldPosition(this);
                        xAxis--;
                        chessBoard.putChessManToTheNewPosition(this);
                        System.out.println("Chess " + id + ": Moved on. Current position: (" + xAxis + "," + yAxis + ").");
                    } else {
                        System.out.println("Chess " + id + ": There is another chessman on the position (" + (xAxis - 1) + "," + yAxis + ") of the next move. This move is cancelled!");
                    }
                } else {
                    System.out.println("Chess " + id + ": Reached the left side of the chessboard. Can't move.");
                }
                break;
            case "S":
                if (yAxis > 0) {
                    if (chessBoard.getMainChessBoard()[currentxAxis][currentyAxis - 1] == null) {
                        chessBoard.removeChessManFromTheOldPosition(this);
                        yAxis--;
                        chessBoard.putChessManToTheNewPosition(this);
                        System.out.println("Chess " + id + ": Moved on. Current position: (" + xAxis + "," + yAxis + ").");
                    } else {
                        System.out.println("Chess " + id + ": There is another chessman on the position (" + xAxis + "," + (yAxis - 1) + ") of the next move. This move is cancelled!");
                    }
                } else {
                    System.out.println("Chess " + id + ": Reached the bottom side of the chessboard. Can't move.");
                }
                break;
        }
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getxAxis() {
        return xAxis;
    }

    public void setxAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    public int getyAxis() {
        return yAxis;
    }

    public void setyAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public List<String> getRoute() {
        return route;
    }

    public void setRoute(List<String> route) {
        this.route = route;
    }

    public String getPositionString() {
        return positionString;
    }

    public void setPositionString(String positionString) {
        this.positionString = positionString;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }
}