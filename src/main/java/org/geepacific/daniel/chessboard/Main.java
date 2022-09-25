package org.geepacific.daniel.chessboard;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {
    private static final List<String> turn = List.of(new String[]{"L", "R"});
    private static final List<String> direction = List.of(new String[]{"N", "E", "W", "S"});
    private static final List<String> numberList = List.of(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});

    public List<ChessMan> initChessMans() throws IOException {
        String filePath = Const.filePath + "ChessFile.txt";
        FileInputStream chessFile = new FileInputStream(filePath);
        List<String> chessManData = new ArrayList<>();
        // Read data from the specified file
        Scanner sc = new Scanner(chessFile);
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            chessManData.add(data);
        }
        sc.close();
        // VALIDATION PROCESSES
        if (chessManData.size() > 0) {
            List<String> invalidChessManData = new ArrayList<>();
            for (String data : chessManData) {
                // Firstly, remove all white spaces.
                data = data.replaceAll("\\s", "");
                // If the data is empty, it's invalid.
                if ("".equals(data)) {
                    invalidChessManData.add(data);
                    continue;
                }
                // If the data doesn't start with I, no initialization required, it's invalid.
                if (!data.startsWith("I")) {
                    invalidChessManData.add(data);
                    continue;
                }
                // The data must contain 2 parts only, they are initializing part and routing part.
                if (StringUtils.countMatches(data, ";") != 2) {
                    invalidChessManData.add(data);
                    continue;
                }
                // The initializing part must contain 4 characters.
                String[] dataSplited = data.split(";");
                if (dataSplited[0].length() != 4) {
                    invalidChessManData.add(data);
                    continue;
                }
                // Check whether the initializing part is valid
                String[] dataSplitedFirstPart = dataSplited[0].split("");
                if (!numberList.contains(dataSplitedFirstPart[1]) || !numberList.contains(dataSplitedFirstPart[2])
                        || !direction.contains(dataSplitedFirstPart[3])) {
                    invalidChessManData.add(data);
                }
            }
            // Validation finished, remove all invalid chessman data if exists.
            if (invalidChessManData.size() > 0) chessManData.removeAll(invalidChessManData);
        }
        // CHESSMAN CREATING PROCESSES
        List<ChessMan> chessManList = new ArrayList<>();
        int id = 1;
        for (String data: chessManData) {
            String[] dataSplited = data.split(";");
            // Process the initializing part
            String[] initializingCommands = dataSplited[0].split("");
            int xAxis = Integer.parseInt(initializingCommands[1]);
            int yAxis = Integer.parseInt(initializingCommands[2]);
            String direction = initializingCommands[3];
            // Remove all invalid characters from the routing part
            List<String> routing = new ArrayList<>(List.of(dataSplited[1].split("")));
            List<String> invalidCommandRoute = new ArrayList<>();
            for (String commandRoute: routing) {
                if (!"M".equals(commandRoute) && !turn.contains(commandRoute)) {
                    invalidCommandRoute.add(commandRoute);
                }
            }
            routing.removeAll(invalidCommandRoute);
            // Initialize chessmans, haven't put them on the chessboard yet, so the property chessBoard is null in the constructor.
            chessManList.add(new ChessMan(id, xAxis, yAxis, direction, routing, "(" + xAxis + "," + yAxis + ")", null));
            id++;
        }
        // Remove duplications in all chessmans' first position to avoid more than 1 chessman are initialized on the same position.
        List<String> chessManPosition = new ArrayList<>();
        for (ChessMan chessMan: chessManList) {
            chessManPosition.add(chessMan.getPositionString());
        }
        HashMap<String,Integer> duplicatedPositionsMap = new HashMap<>();
        for (String chessManPositionElement: chessManPosition) {
            if (Collections.frequency(chessManPosition, chessManPositionElement) > 1) {
                duplicatedPositionsMap.put(chessManPositionElement, chessManPosition.indexOf(chessManPositionElement) + 1);
            }
        }
        List<Integer> chessmanKeepIds = new ArrayList<>(duplicatedPositionsMap.values());
        Set<String> duplicatedPositions = duplicatedPositionsMap.keySet();

        Set<ChessMan> duplicatedChessmanInInitializing = new HashSet<>();
        for (ChessMan chessMan: chessManList) {
            if (chessmanKeepIds.contains(chessMan.getId())) duplicatedChessmanInInitializing.add(chessMan);
        }
        // If duplications detected, do remove them.
        if (duplicatedChessmanInInitializing.size() > 0) {
            // Remove all chessmans that have the same initialized position.
            chessManList.removeIf(chessMan -> duplicatedPositions.contains(chessMan.getPositionString()));
            // Then add again 1 chessman has that one.
            chessManList.addAll(duplicatedChessmanInInitializing);
            // Re-initialize chessmans' id
            id = 1;
            for (ChessMan chessMan : chessManList) {
                chessMan.setId(id);
                id++;
            }
        }
        return chessManList;
    }



    public static void main(String[] args) throws IOException {
        Main main = new Main();
        List<ChessMan> chessManList = main.initChessMans();
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.putChessMansOnTheChessBoard(chessManList);
        // Wake all chessmans up to let them know which chessboard they are standing on.
        for (ChessMan chessMan: chessManList) {
            chessMan.setChessBoard(chessBoard);
        }
        // Put each chessman to its own thread.
        List<ChessThread> chessThreadList = new ArrayList<>();
        for (ChessMan chessMan: chessManList) {
            chessThreadList.add(new ChessThread(chessMan));
        }
        for (ChessThread chessThread: chessThreadList) {
            chessThread.start();
        }
    }
}
