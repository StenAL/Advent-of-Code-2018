import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdventOfCode9toX {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello, world!");
        task24();
    }

    public static void task17() throws IOException {
        Stream<String> dataStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/9.txt"));
        List<String> dataList  = dataStream.collect(Collectors.toList());
        String[] data = dataList.get(0).split(" ");
        int playerCount = Integer.parseInt(data[0]);
        int marbles = Integer.parseInt(data[1]) + 1;
        ArrayList<Integer> scores = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            scores.add(0);
        }
        ArrayList<Integer> playField = new ArrayList<>();
        playField.add(0);
        playField.add(1);
        int currentMarble = 1;
        int turn = 2;
        while (turn <= marbles) {
            //System.out.println(playField);
            if (turn % 23 != 0) {
                int newPosition = (playField.indexOf(currentMarble) + 2);
                if (newPosition > playField.size()) {
                    newPosition = newPosition % playField.size();
                }
                playField.add(newPosition, turn);
                currentMarble = turn;
            }
            else {
                int scoreGain = turn;
                int player = turn % playerCount;
                int newPosition = (playField.indexOf(currentMarble) - 7);
                if (newPosition < 0) {
                    newPosition = playField.size() + newPosition;
                }
                scoreGain += playField.get(newPosition);
                playField.remove(newPosition);
                currentMarble = playField.get(newPosition);
                scores.set(player, scores.get(player) + scoreGain);
            }
            if (turn % 10000 == 0) {
                System.out.println(turn + " done");
            }
            turn++;
        }
        System.out.println(scores);
        System.out.println(Collections.max(scores));
    }

    public static void task18() throws IOException {
        // overflow is a bitch, use LONG LONG LONG LONG
        Stream<String> dataStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/9.txt"));
        List<String> dataList  = dataStream.collect(Collectors.toList());
        String[] data = dataList.get(0).split(" ");
        int playerCount = Integer.parseInt(data[0]);
        int marbles = Integer.parseInt(data[1]) + 1;
        ArrayList<Long> scores = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            long zero = 0;
            scores.add(zero);
        }
        List<Integer> playField = new ArrayList<>();
        playField.add(0);
        playField.add(1);
        int currentIndex = 1;
        int turn = 2;
        while (turn <= marbles) {
            //System.out.println(playField);
            if (turn % 23 != 0) {
                currentIndex = (currentIndex + 2);
                if (currentIndex > playField.size()) {
                    currentIndex = currentIndex % playField.size();
                }
                playField.add(currentIndex, turn);
            }
            else {
                int player = turn % playerCount;
                currentIndex = (currentIndex - 7);
                if (currentIndex < 0) {
                    currentIndex = playField.size() + currentIndex;
                }
                int marble = playField.remove(currentIndex);
                scores.set(player, scores.get(player) + marble);
            }
            if (turn % 10000 == 0) {
                System.out.println(turn + " done");
            }
            turn++;
            }
            System.out.println(scores);
            System.out.println(Collections.max(scores));
    }

    public static void task19() throws IOException {
        Stream<String> dataStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/10.txt"));
        List<String> dataList = dataStream.collect(Collectors.toList());
        ArrayList<ArrayList<Integer>> coordinates = new ArrayList<>();
        ArrayList<ArrayList<Integer>> velocities = new ArrayList<>();
        int i = 0;
        for (String data: dataList) {
            coordinates.add(new ArrayList<>());
            velocities.add(new ArrayList<>());
            String[] dataArray = data.split("<");
            String[] pos = dataArray[1].split(",");
            int xPos = Integer.parseInt(pos[0].trim());
            int yPos = Integer.parseInt(pos[1].trim().substring(0, pos[1].trim().length()-11));
            coordinates.get(i).add(xPos);
            coordinates.get(i).add(yPos);
            String[] vels = dataArray[2].split(",");
            int xVel = Integer.parseInt(vels[0].trim());
            int yVel = Integer.parseInt(vels[1].trim().substring(0, vels[1].trim().length()-1));
            velocities.get(i).add(xVel);
            velocities.get(i).add(yVel);
            i++;
        }
        //System.out.println(coordinates);
        boolean done = false;
        ArrayList<ArrayList<Integer>> ansCords = new ArrayList<>();
        int maxX = 0, maxY = 0, minX = 0, minY = 0;
        int u = 0;
        int time = 0;
        while (!done) {
            HashMap<Integer, Integer> xCount = new HashMap<>();
            HashMap<Integer, Integer> yCount = new HashMap<>();
            boolean doneRow = false, doneColumn = false;
            for (int j = 0; j < coordinates.size(); j++) {
                ArrayList<Integer> coordinate = coordinates.get(j);
                ArrayList<Integer> velocity = velocities.get(j);
                int x = coordinate.get(0);
                int y = coordinate.get(1);
                coordinate.set(0, x + velocity.get(0));
                coordinate.set(1, y + velocity.get(1));
                x = coordinate.get(0);
                y = coordinate.get(1);
                if (!xCount.containsKey(x)) {
                    xCount.put(x, 1);
                }
                else {
                    xCount.put(x, xCount.get(x) + 1);
                }
                if (!yCount.containsKey(y)) {
                    yCount.put(y, 1);
                }
                else {
                    yCount.put(y, yCount.get(y) + 1);
                }
            }

            if (Collections.max(xCount.values()) >= 8) {
                doneRow = true;
            }
            if (Collections.max(yCount.values()) > 3) {
                doneColumn = true;
            }
            time++;
            if (doneRow && doneColumn) {
                maxX = Collections.max(xCount.keySet());
                maxY = Collections.max(yCount.keySet());
                minX = Collections.min(xCount.keySet());
                minY = Collections.min(yCount.keySet());
                ansCords = coordinates;
                u++;
                if (u > 21) {
                    done = true;
                }
            }
        }
        System.out.println(time);
        System.out.println("a" + ansCords);
        System.out.println(maxX + " " + maxY + " " + minX + " " + minY);
        ArrayList<ArrayList<String>> grid = new ArrayList<>();
        for (int y = minY; y <= maxY; y++) {
            grid.add(new ArrayList<>());
            for (int x = minX; x <= maxX; x++) {
                ArrayList<Integer> coords = new ArrayList<>();
                coords.add(x);
                coords.add(y);
                if (ansCords.contains(coords)) {
                    grid.get(y - minY).add("#");
                }
                else {
                    grid.get(y - minY).add(".");
                }
            }
        }
        PrintWriter writer = new PrintWriter("/Users/stenl/Desktop/jaava/AdventOfCode/10_1.txt", "UTF-8");
        for (ArrayList<String> row: grid) {
            System.out.println(row);
            writer.println(row);
        }
    }

    public static void task21() throws IOException {
        Stream<String> dataStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/11.txt"));
        List<String> dataList = dataStream.collect(Collectors.toList());
        int serialNumber = Integer.parseInt(dataList.get(0));
        //serialNumber = 42;
        int size = 300;
        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
        for (int y = 0; y < size; y++) {
            ArrayList<Integer> row = new ArrayList<>();
            grid.add(row);
            for (int x = 0; x < size; x++) {
                int value = 0;
                int rackID = x + 1 + 10;
                value += rackID;
                value *= (y+1);
                value += serialNumber;
                value *= rackID;
                String valueString = "" + value;
                value = Character.getNumericValue(valueString.charAt(valueString.length()-3));
                value -= 5;
                row.add(value);
            }
        }
        String ans = "";
        int max = 0;
        for (int y = 0; y < size - 2; y++) {
            for (int x = 0; x < size - 2; x++) {
                int sum = 0;
                sum += grid.get(y).get(x);
                sum += grid.get(y).get(x+1);
                sum += grid.get(y).get(x+2);
                sum += grid.get(y+1).get(x);
                sum += grid.get(y+1).get(x+1);
                sum += grid.get(y+1).get(x+2);
                sum += grid.get(y+2).get(x);
                sum += grid.get(y+2).get(x+1);
                sum += grid.get(y+2).get(x+2);
                if (sum > max) {
                    max = sum;
                    //System.out.println(max);
                    ans = "" + (x+1) + "," + (y+1);
                }
            }
        }
        System.out.println(ans);
    }

    public static void task22() throws IOException {
        Stream<String> dataStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/11.txt"));
        List<String> dataList = dataStream.collect(Collectors.toList());
        int serialNumber = Integer.parseInt(dataList.get(0));
        //serialNumber = 42;
        int gridSize = 300;
        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
        for (int y = 0; y < gridSize; y++) {
            ArrayList<Integer> row = new ArrayList<>();
            grid.add(row);
            for (int x = 0; x < gridSize; x++) {
                int value = 0;
                int rackID = x + 1 + 10;
                value += rackID;
                value *= (y + 1);
                value += serialNumber;
                value *= rackID;
                String valueString = "" + value;
                value = Character.getNumericValue(valueString.charAt(valueString.length() - 3));
                value -= 5;
                row.add(value);
            }
        }
        String ans = "";
        int max = 0;
        for (int size = 1; size <= 300; size++) {
            System.out.println(size + " done");
            for (int y = 0; y < gridSize - (size-1); y++) {
                for (int x = 0; x < gridSize - (size-1); x++) {
                    int sum = 0;
                    for (int yGrid = y; yGrid < y + size; yGrid++){
                        for (int xGrid = x; xGrid < x + size; xGrid++)
                            sum += grid.get(yGrid).get(xGrid);
                    }
                    if (sum > max) {
                        max = sum;
                        //System.out.println(max);
                        ans = "" + (x + 1) + "," + (y + 1) + "," + size;
                    }
                }
            }
        }
        System.out.println(ans);
    }

    public static void task23() throws IOException {
        Stream<String> dataStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/test.txt"));
        List<String> dataList = dataStream.collect(Collectors.toList());
        String initalStates = dataList.get(0).substring(15);
        while (initalStates.length() < 50) {
            initalStates = " " + initalStates + " ";
        }
        System.out.println(initalStates);
        initalStates = initalStates.replace(".", "0");
        initalStates = initalStates.replace("#", "1");
        HashMap<String, String> rules = new HashMap<>();
        for (int i = 2; i < dataList.size(); i++) {
            String rule = dataList.get(i);
            rule = rule.replace(".", "0");
            rule = rule.replace("#", "1");
            String precursor = rule.substring(0, 5);
            String result = "" + rule.charAt(9);
            rules.put(precursor, result);
            System.out.println(precursor + "  " + result);
        }
        String prevState = initalStates;
        int cycles = 20;
        int min = 0;
        int sum = 0;
        for (int cycle = 0; cycle < cycles; cycle++) {
            String newState = "";
            sum = 0;
            for (int i = -3; i < prevState.length() + 2; i++) {
                prevState = prevState.trim();
                String plant = "";
                String surround = "";
                if (i >= 0 && i < prevState.length()) {
                    plant = "" + prevState.charAt(i);
                    if (i == 0) {
                        surround += "00";
                    } else if (i == 1) {
                        surround += "0" + prevState.charAt(0);
                    } else {
                        surround += "" + prevState.charAt(i - 2) + prevState.charAt(i - 1);
                    }
                    surround += plant;
                } else if (i < 0) {
                    plant = "0";
                    surround = "00" + plant;
                    if (i == -3) {
                        surround += "00";
                    } else {
                        if (i == -2) {
                            surround += "0" + prevState.charAt(0);
                        } else {
                            surround += "" + prevState.charAt(0) + prevState.charAt(1);
                        }
                    }
                }
                if (surround.length() < 5) {
                    if (i < prevState.length() - 2) {
                        surround += "" + prevState.charAt(i + 1) + prevState.charAt(i + 2);
                    } else if (i == prevState.length() - 2) {
                        surround += "" + prevState.charAt(i + 1) + "0";
                    } else if (i == prevState.length() - 1) {
                        surround += "00";
                    } else if (i == prevState.length()) {
                        surround += "" + prevState.charAt(i - 2) + prevState.charAt(i - 1) + "000";
                    } else if (i == prevState.length() + 1) {
                        surround += "" + prevState.charAt(i - 2) + "0000";
                    }
                }
                //System.out.println(surround + " " + i);
                if (rules.keySet().contains(surround)) {
                    String newPlant = rules.get(surround);

                    if (newPlant.equals("0") && i < 0) {

                    }
                    else {
                        newState += newPlant;
                    }
                    if (newPlant.equals("1")) {
                        if (i < 0) {
                            min += i;
                        }
                        sum += i + min;
                    }
                }
                else {
                    if (i >= 0 && i < prevState.length())
                    newState += "0";
                }
            }
            while (newState.length() < 50) {
                newState = " " + newState + " ";
            }
            String printable = newState.replace("0", ".");
            printable = printable.replace("1", "#");
            System.out.println((cycle + 1) + " " + printable + sum + " " + min);
            prevState = newState;
        }
        System.out.println(sum);
    }

    public static void task24() throws IOException {
        Stream<String> dataStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/12.txt"));
        List<String> dataList = dataStream.collect(Collectors.toList());
        String initalStates = dataList.get(0).substring(15);
        while (initalStates.length() < 50) {
            initalStates = " " + initalStates + " ";
        }
        System.out.println(initalStates);
        initalStates = initalStates.replace(".", "0");
        initalStates = initalStates.replace("#", "1");
        HashMap<String, String> rules = new HashMap<>();
        for (int i = 2; i < dataList.size(); i++) {
            String rule = dataList.get(i);
            rule = rule.replace(".", "0");
            rule = rule.replace("#", "1");
            String precursor = rule.substring(0, 5);
            String result = "" + rule.charAt(9);
            rules.put(precursor, result);
        }
        String prevState = initalStates;
        int cycles = 300;
        int min = 0;
        int sum = 0;
        int oldSum = 0;
        for (int cycle = 0; cycle < cycles; cycle++) {
            String newState = "";
            sum = 0;
            for (int i = -3; i < prevState.length() + 2; i++) {
                prevState = prevState.trim();
                String plant = "";
                String surround = "";
                if (i >= 0 && i < prevState.length()) {
                    plant = "" + prevState.charAt(i);
                    if (i == 0) {
                        surround += "00";
                    } else if (i == 1) {
                        surround += "0" + prevState.charAt(0);
                    } else {
                        surround += "" + prevState.charAt(i - 2) + prevState.charAt(i - 1);
                    }
                    surround += plant;
                } else if (i < 0) {
                    plant = "0";
                    surround = "00" + plant;
                    if (i == -3) {
                        surround += "00";
                    } else {
                        if (i == -2) {
                            surround += "0" + prevState.charAt(0);
                        } else {
                            surround += "" + prevState.charAt(0) + prevState.charAt(1);
                        }
                    }
                }
                if (surround.length() < 5) {
                    if (i < prevState.length() - 2) {
                        surround += "" + prevState.charAt(i + 1) + prevState.charAt(i + 2);
                    } else if (i == prevState.length() - 2) {
                        surround += "" + prevState.charAt(i + 1) + "0";
                    } else if (i == prevState.length() - 1) {
                        surround += "00";
                    } else if (i == prevState.length()) {
                        surround += "" + prevState.charAt(i - 2) + prevState.charAt(i - 1) + "000";
                    } else if (i == prevState.length() + 1) {
                        surround += "" + prevState.charAt(i - 2) + "0000";
                    }
                }
                //System.out.println(surround + " " + i);
                if (rules.keySet().contains(surround)) {
                    String newPlant = rules.get(surround);

                    if (newPlant.equals("0") && i < 0) {

                    }
                    else {
                        newState += newPlant;
                    }
                    if (newPlant.equals("1")) {
                        if (i < 0) {
                            min += i;
                        }
                        sum += i + min;
                    }
                }
                else {
                    if (i >= 0 && i < prevState.length())
                        newState += "0";
                }
            }
            prevState = newState;
            //System.out.println(sum + " " + (sum-oldSum) + " " + (cycle+1));
            oldSum = sum;
        }
        // sum at 300 cycles is 19255. Every cycle sum increases by 62
        // sum at 50000000000 is (50000000000-300)*62 + 19255
        System.out.println(sum);
        long ans = (50000000000L-300)*62 + 19255;
        System.out.println(ans);
    }
}
