import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdventOfCode1to8 {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello, world!");
        task16();
    }

    private static int task1() throws IOException
    {
        return Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/1.txt"))
                .map(Integer::parseInt)
                .reduce(0, (total, count) -> total + count);
    }

    private static int task2() throws IOException
    {
        boolean found = false;
        int sum = 0;
        int count = 0;
        ArrayList<Integer> sums = new ArrayList<>();
        List<Integer>  frequencyList = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/1st-1.txt"))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
        while (!found){
            for (Integer frequency : frequencyList) {
                sum += frequency;
                if (sums.contains(sum)) {
                    return sum;
                }
                sums.add(sum);
            }
            count += 1;
            System.out.println("Iterated " + count + "times");
        }
        System.out.println(sums);
        return -1;
    }

    private static void task3() throws IOException
    {
        int twos = 0;
        int threes = 0;
        Stream<String> IDStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/2.txt"));
        List<String> IDList  = IDStream.collect(Collectors.toList());
        for(String ID: IDList) {
            boolean done2 = false;
            boolean done3 = false;
            ArrayList<Character> counted = new ArrayList<Character>();
            for (char charater: ID.toCharArray())
            {
                int count = 0;
                if (!counted.contains(charater)) {
                    for (char comaparator : ID.toCharArray()) {
                        if (charater == comaparator) {
                            count += 1;
                        }
                    }
                    counted.add(charater);
                }
                if (count == 2 && !done2) {
                    twos += 1;
                    done2 = true;
                }
                else if (count == 3 && !done3) {
                    threes += 1;
                    done3 = true;
                }
                System.out.println(ID + " contains " + count + " " + charater);
            }
        }
        System.out.println(twos + " " + threes + " " + twos*threes);

    }

    private  static void task4() throws IOException
    {
        Stream<String> IDStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/2.txt"));
        List<String> IDList  = IDStream.collect(Collectors.toList());
        for(String ID: IDList) {
            for(String compareString: IDList) {
                int differences = 0;
                for(int i = 0; i < ID.length(); i ++) {
                    if (ID.charAt(i) != compareString.charAt(i)) {
                        differences += 1;
                    }
                }
                if (differences == 1) {
                    String endString = "";
                    for(int i = 0; i < ID.length(); i ++) {
                        if (ID.charAt(i) == compareString.charAt(i)) {
                            endString += ID.substring(i, i+1);
                        }
                    }
                    System.out.println(endString);

                }
            }
        }
    }

    private static void task5() throws IOException
    {
        int overlap = 0;
        List<String> existing = new ArrayList<>();
        List<String> counted = new ArrayList<>();
        Stream<String> squareStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/3.txt"));
        List<String> squareList  = squareStream.collect(Collectors.toList());
        for (String bigSquare: squareList) {
            System.out.println("done " + bigSquare);
            String[] data = bigSquare.split(" ");
            String[] beginnings = data[2].split(",");
            int beginRow = Integer.parseInt(beginnings[0]);
            int beginColumn = Integer.parseInt(beginnings[1].substring(0, beginnings[1].length()-1));
            String[] sizes = data[3].split("x");
            int width = Integer.parseInt(sizes[0]);
            int height = Integer.parseInt(sizes[1]);
            for (int row = beginRow; row < beginRow + width; row++) {
                for (int column = beginColumn; column < beginColumn + height; column++) {
                    String square = (row + " " + column);
                    if (existing.contains(square)) {
                        if (!counted.contains(square)) {
                            overlap += 1;
                            counted.add(square);
                        }
                    }
                    else {
                        existing.add(square);
                    }
                }
            }
        }
        System.out.println(overlap);
    }

    private  static void task6() throws IOException
    {
        List<String> existing = new ArrayList<>();
        List<String> counted = new ArrayList<>();
        Stream<String> squareStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/3.txt"));
        List<String> squareList  = squareStream.collect(Collectors.toList());
        for (String bigSquare: squareList) {
            String[] data = bigSquare.split(" ");
            String[] beginnings = data[2].split(",");
            int beginRow = Integer.parseInt(beginnings[0]);
            int beginColumn = Integer.parseInt(beginnings[1].substring(0, beginnings[1].length()-1));
            String[] sizes = data[3].split("x");
            int width = Integer.parseInt(sizes[0]);
            int height = Integer.parseInt(sizes[1]);
            for (int row = beginRow; row < beginRow + width; row++) {
                for (int column = beginColumn; column < beginColumn + height; column++) {
                    String square = (row + " " + column);
                    if (existing.contains(square)) {
                        if (!counted.contains(square)) {
                            counted.add(square);
                        }
                    }
                    else {
                        existing.add(square);
                    }
                }
            }
        }
        for (String bigSquare: squareList) {
            boolean overlaps = false;
            String[] data = bigSquare.split(" ");
            String[] beginnings = data[2].split(",");
            int beginRow = Integer.parseInt(beginnings[0]);
            int beginColumn = Integer.parseInt(beginnings[1].substring(0, beginnings[1].length() - 1));
            String[] sizes = data[3].split("x");
            int width = Integer.parseInt(sizes[0]);
            int height = Integer.parseInt(sizes[1]);
            for (int row = beginRow; row < beginRow + width; row++) {
                for (int column = beginColumn; column < beginColumn + height; column++) {
                    String square = (row + " " + column);
                    if (counted.contains(square)) {
                        overlaps = true;
                    }
                }
            }
            if (!overlaps) {
                System.out.println("!!!!!" + bigSquare);
            }
            else {
                System.out.println(bigSquare + " overlaps");
            }
        }
    }

    private static void task7() throws IOException
    {
        Stream<String> logStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/4.txt"));
        List<String> logList  = logStream.sorted().collect(Collectors.toList());
        HashMap<String, Integer> sleepTimes = new HashMap<>();
        int beginning = 0, ending = 0;
        String id = "";
        HashMap<Integer, Integer> sleptMinutes = new HashMap<>();
        // 2039 has slept the most, for 469 minutes
        for (String logLine: logList) {
            if (logLine.contains("#")) {
                String idString = logLine.split(" ")[3];
                id = idString.substring(1);
                if (!sleepTimes.containsKey(id)) {
                    sleepTimes.put(id, 0);
                }
            }
            else {
                if (logLine.contains("falls")) {
                    beginning = Integer.parseInt(logLine.split(":")[1].substring(0,2));
                }
                else {
                    ending = Integer.parseInt(logLine.split(":")[1].substring(0,2));
                    int sleepTime = ending - beginning;
                    sleepTimes.put(id, sleepTimes.get(id) + sleepTime);
                    //System.out.println(id + " has slept for " + sleepTimes.get(id));
                    if (id.equals("2039")) {
                        for (int i = beginning; i < ending; i++) {
                            if (!sleptMinutes.keySet().contains(i)) {
                                sleptMinutes.put(i, 1);
                            }
                            else {
                                sleptMinutes.put(i, sleptMinutes.get(i) + 1);
                            }
                        }
                        System.out.println(id + " has slept " + beginning + " " + ending);
                    }
                }
            }
        }
        System.out.println(sleptMinutes);
        System.out.println(Collections.max(sleptMinutes.values()));
        // Minute 49 = most slept
    }

    private static void task8() throws IOException {
        Stream<String> logStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/4.txt"));
        List<String> logList = logStream.sorted().collect(Collectors.toList());
        HashMap<String, HashMap<Integer, Integer>> sleepTimes = new HashMap<>();
        int beginning = 0, ending = 0;
        String id = "";
        int max = 0;
        for (String logLine : logList) {
            if (logLine.contains("#")) {
                String idString = logLine.split(" ")[3];
                id = idString.substring(1);
                if (!sleepTimes.containsKey(id)) {
                    sleepTimes.put(id, new HashMap<>());
                }
            }
            else {
                if (logLine.contains("falls")) {
                    beginning = Integer.parseInt(logLine.split(":")[1].substring(0, 2));
                }
                else {
                    ending = Integer.parseInt(logLine.split(":")[1].substring(0, 2));
                    for (int i = beginning; i < ending; i++) {

                        if (!sleepTimes.get(id).keySet().contains(i)) {
                            sleepTimes.get(id).put(i, 1);
                        }
                        else {
                            //System.out.println(id + " " + i + " " + sleepTimes.get(id).get(i));
                            sleepTimes.get(id).put(i, sleepTimes.get(id).get(i) + 1);
                            if (sleepTimes.get(id).get(i) > max) {
                                max = sleepTimes.get(id).get(i);
                                // last one printed is answer
                                System.out.println("max " + max + " " + id + " minute " + i);
                            }
                        }
                    }
                    //System.out.println(id + " has slept " + beginning + " " + ending);
                }
            }
        }
    }

    private static void task9() throws IOException
    {
        Stream<String> polymerStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/5.txt"));
        List<String> polymerList = polymerStream.collect(Collectors.toList());
        String polymer = polymerList.get(0);
        System.out.println(polymer);
        boolean removed = true;
        while (removed) {
            removed = false;
            for (int i = 1; i < polymer.length(); i ++) {
                char c2 = polymer.charAt(i);
                boolean c2Upper = Character.isUpperCase(c2);
                char c1 = polymer.charAt(i-1);
                boolean c1Upper = Character.isUpperCase(c1);
                if (c2Upper != c1Upper && Character.toUpperCase(c2) == Character.toUpperCase(c1)) {
                    polymer = polymer.substring(0, i-1) + polymer.substring(i+1);
                    removed = true;
                }
            }
        }
        System.out.println(polymer);
        System.out.println(polymer.length());
    }

    private static void task10() throws IOException
    {
        Stream<String> polymerStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/5_2.txt"));
        List<String> polymerList = polymerStream.collect(Collectors.toList());
        String polymerStart = polymerList.get(0);
        HashMap<Character, Integer> removedMap = new HashMap<>();
        for (int j = 1; j < polymerStart.length(); j++) {
            char c = polymerStart.charAt(j-1);
            if (!removedMap.containsKey(c)) {
                String polymer = polymerStart.replace("" + c, "");
                polymer = polymer.replace("" + Character.toUpperCase(c), "");
                polymer = polymer.replace("" + Character.toLowerCase(c), "");
                boolean removed = true;
                while (removed) {
                    removed = false;
                    for (int i = 1; i < polymer.length(); i ++) {
                        char c2 = polymer.charAt(i);
                        boolean c2Upper = Character.isUpperCase(c2);
                        char c1 = polymer.charAt(i-1);
                        boolean c1Upper = Character.isUpperCase(c1);
                        if (c2Upper != c1Upper && Character.toUpperCase(c2) == Character.toUpperCase(c1)) {
                            polymer = polymer.substring(0, i-1) + polymer.substring(i+1);
                            removed = true;
                        }
                    }
                }
                removedMap.put(c, polymer.length());
            }
        }
        int min = 10000000;
        for (char character: removedMap.keySet()) {
            System.out.println(character + " " + removedMap.get(character));
            if (removedMap.get(character) < min) {
                min = removedMap.get(character);
                System.out.println("MIN " + character + " " + min);
            }
        }
    }

    private static void task11() throws IOException {
        Stream<String> pointStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/6.txt"));
        List<String> pointList = pointStream.collect(Collectors.toList());
        int xMax = 0, yMax = 0;
        for (String point : pointList) {
            String[] coordinates = point.split(", ");
            int xCoordinate = Integer.parseInt(coordinates[0]);
            int yCoordinate = Integer.parseInt(coordinates[1]);
            if (xCoordinate > xMax) {
                xMax = xCoordinate;
            }
            if (yCoordinate > yMax) {
                yMax = yCoordinate;
            }
        }
        System.out.println(xMax + " " + yMax);
        List<String> infinitePoints = new ArrayList<>();
        Map<String, Integer> closestCount = new HashMap<>();
        for (int x = 0; x <= xMax + 1; x++) {
            for (int y = 0; y <= yMax + 1; y++) {
                boolean edgePoint;
                boolean shared = false;
                int dMin = 1000000000;
                String closestPoint = "";
                if (x == 0 || y == 0 || x == xMax + 1 || y == yMax + 1) {
                    edgePoint = true;
                }
                else {
                    edgePoint = false;
                }
                for (String point : pointList) { // cycle through every point to find the one it's closest to
                    String[] coordinates = point.split(", ");
                    int xCoordinate = Integer.parseInt(coordinates[0]);
                    int yCoordinate = Integer.parseInt(coordinates[1]);
                    int d = Math.abs(xCoordinate - x) + Math.abs(yCoordinate - y);
                    if (d <= dMin) {
                        if (d == dMin) {
                            shared = true;
                        }
                        else {
                            dMin = d;
                            closestPoint = point;
                            shared = false;
                        }
                    }
                }
                if (edgePoint) {
                    infinitePoints.add(closestPoint);
                }
                else if (!infinitePoints.contains(closestPoint) && !shared){
                    if (!closestCount.keySet().contains(closestPoint)) {
                        closestCount.put(closestPoint, 1);
                    }
                    else {
                        closestCount.put(closestPoint, closestCount.get(closestPoint) + 1);
                    }
                }
            }
        }
        int i = 0;
        int ans = 0;
        for (String a: closestCount.keySet()) {
            System.out.println(i + "point " + a + " has " + closestCount.get(a));
            i++;
            if (closestCount.get(a) > ans) {
                ans = closestCount.get(a);
                System.out.println("ANS " + ans);
            }
        }
    }
    private static void task12() throws IOException {
        Stream<String> pointStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/6.txt"));
        List<String> pointList = pointStream.collect(Collectors.toList());
        int xMax = 0, yMax = 0;
        for (String point : pointList) {
            String[] coordinates = point.split(", ");
            int xCoordinate = Integer.parseInt(coordinates[0]);
            int yCoordinate = Integer.parseInt(coordinates[1]);
            if (xCoordinate > xMax) {
                xMax = xCoordinate;
            }
            if (yCoordinate > yMax) {
                yMax = yCoordinate;
            }
        }
        System.out.println(xMax + " " + yMax);
        int count = 0;
        for (int x = 0; x <= xMax; x++) {
            for (int y = 0; y <= yMax; y++) {
                int totalDistance = 0;
                for (String point : pointList) { // cycle through every point to find the one it's closest to
                    String[] coordinates = point.split(", ");
                    int xCoordinate = Integer.parseInt(coordinates[0]);
                    int yCoordinate = Integer.parseInt(coordinates[1]);
                    int d = Math.abs(xCoordinate - x) + Math.abs(yCoordinate - y);
                    totalDistance += d;
                }
                if (totalDistance < 10000) {
                    count += 1;
                }
            }
        }
        System.out.println(count);
    }

    private static void task13() throws IOException {
        Stream<String> taskStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/7.txt"));
        List<String> taskList = taskStream.collect(Collectors.toList());
        ArrayList<String> tasks = new ArrayList<>();
        ArrayList<String> doable = new ArrayList<>();
        HashMap<String, ArrayList<String>> conditions = new HashMap<>();
        for (String task: taskList) {
            HashMap<String, ArrayList<String>> conditionMap = new HashMap<>();
            String[] taskArray = task.split(" ");
            String condition = taskArray[1];
            if (!tasks.contains(condition)) {
                tasks.add(condition);
            }
            String result = taskArray[7];
            if (!tasks.contains(result)) {
                tasks.add(result);
            }
            if (!conditions.keySet().contains(result)) {
                conditions.put(result, new ArrayList<>());
            }
            conditions.get(result).add(condition);

        }
        for (String task: tasks) {
            if (!conditions.containsKey(task)) {
                doable.add(task);
            }
        }
        System.out.println(conditions);
        System.out.println(doable);
        String ans = "";
        while (doable.size() != 0){
            String task = doable.get(0);
            ans += task;
            doable.remove(task);
            ArrayList<String> empty = new ArrayList<>();
            for (String condional: conditions.keySet()) {
                ArrayList<String> thisCondition = conditions.get(condional);
                if (thisCondition.contains(task)) {
                    thisCondition.remove(task);
                }
                if (thisCondition.size() == 0 && !doable.contains(condional)) {
                    doable.add(condional);
                    empty.add(condional);
                }
            }
            for (String noConditions: empty) {
                conditions.remove(noConditions);
            }
            Collections.sort(doable);
            System.out.println(ans);
        }
    }
    private static void task14() throws IOException {
        Stream<String> taskStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/7.txt"));
        List<String> taskList = taskStream.collect(Collectors.toList());
        ArrayList<String> tasks = new ArrayList<>();
        ArrayList<String> doable = new ArrayList<>();
        ArrayList<String> done = new ArrayList<>();
        int workersCount = 5;
        HashMap<Integer, Integer> workers = new HashMap<>();
        HashMap<String, ArrayList<String>> conditions = new HashMap<>();
        for (String task : taskList) {
            HashMap<String, ArrayList<String>> conditionMap = new HashMap<>();
            String[] taskArray = task.split(" ");
            String condition = taskArray[1];
            if (!tasks.contains(condition)) {
                tasks.add(condition);
            }
            String result = taskArray[7];
            if (!tasks.contains(result)) {
                tasks.add(result);
            }
            if (!conditions.keySet().contains(result)) {
                conditions.put(result, new ArrayList<>());
            }
            conditions.get(result).add(condition);

        }
        for (String task : tasks) {
            if (!conditions.containsKey(task)) {
                doable.add(task);
            }
        }
        int time = 0;

        //for (int i = 0; i < 20; i++) {
        while (done.size() != tasks.size()) {
            ArrayList<Integer> finishedWorkers = new ArrayList<>();
            int taskTime = -1;

            //System.out.println(workers + " begin");
            for (int j = workers.size(); j < workersCount; j++) {
                //System.out.println(j + " " + workers.size());
                String task = null;
                if (doable.size() != 0 && workers.size() < workersCount) {
                    task = doable.get(0);
                    taskTime = task.charAt(0) - 'A' + 1 + 60; // 'A' = 65
                    doable.remove(task);
                }
                if (task != null) {
                        workers.put(taskTime, 1);
                }
            }
            //System.out.println(workers);
            for (int worker: workers.keySet()) {
                if (workers.get(worker) == worker) {
                    finishedWorkers.add(worker);
                    char ch = (char) (worker - 61 + 65);
                    //System.out.println(ch);
                    String doneTask = Character.toString(ch);
                    done.add(doneTask);


                    ArrayList<String> empty = new ArrayList<>();
                    for (String condional: conditions.keySet()) {
                        ArrayList<String> thisCondition = conditions.get(condional);
                        if (thisCondition.contains(doneTask)) {
                            thisCondition.remove(doneTask);
                        }
                        if (thisCondition.size() == 0 && !doable.contains(condional)) {
                            doable.add(condional);
                            empty.add(condional);
                        }
                    }
                    for (String noConditions: empty) {
                        conditions.remove(noConditions);
                    }

                }
                else {
                    workers.put(worker, workers.get(worker) + 1);
                }
            }
            for (int finishedWorker: finishedWorkers) {
                workers.remove(finishedWorker);
            }
            time++;
            System.out.println(time + " " + done);
        }
    }

    private static void task15() throws IOException {
        Stream<String> treeStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/8.txt"));
        List<String> taskList = treeStream.collect(Collectors.toList());
        String[] dataString = taskList.get(0).split(" ");
        ArrayList<Integer> data = new ArrayList();
        for (int i = 0; i < dataString.length; i++) {
            data.add(Integer.parseInt(dataString[i]));
        }
        int ans = task15_helper(0, data, 0);
        System.out.println("ans " + ans);
    }

    private static int task15_helper(int sum, ArrayList<Integer> data, int indexStart) {
        int nodes = data.get(indexStart);
        int metaDataNumber = data.get(indexStart + 1);
        if (nodes != 0) {
            for (int i = 0; i < nodes; i++) {
                sum = task15_helper(sum, data, indexStart + 2);
            }
            System.out.println(sum);
            for (int i = 1; i <= metaDataNumber; i++) {
                sum += data.get(indexStart + 2);
                data.remove(indexStart + 2);
            }
            data.remove(indexStart);
            data.remove(indexStart);
        }
        else {
            for (int i = 1; i <= metaDataNumber; i++) {
                int entry = data.get(indexStart + 2);
                sum += entry;
                data.remove(indexStart);
            }
            data.remove(indexStart);
            data.remove(indexStart);
        }
        return sum;
    }

    private static void task16() throws IOException {
        Stream<String> treeStream = Files.lines(Paths.get("/Users/stenl/Desktop/jaava/AdventOfCode/8.txt"));
        List<String> taskList = treeStream.collect(Collectors.toList());
        String[] dataString = taskList.get(0).split(" ");
        ArrayList<Integer> data = new ArrayList();
        for (int i = 0; i < dataString.length; i++) {
            data.add(Integer.parseInt(dataString[i]));
        }
        int ans = task16_helper(0, data, 0, 0);
        System.out.println("ans " + ans);
    }

    private static int task16_helper(int sum, ArrayList<Integer> data, int indexStart, int accrued) {
        //System.out.println(data);
        int nodes = data.get(indexStart);
        int metaDataNumber = data.get(indexStart + 1);
        //System.out.println(nodes +" " + metaDataNumber);
        if (nodes != 0) {
            ArrayList<Integer> childSums = new ArrayList<>();
            ArrayList<Integer> metaData = new ArrayList<>();
            for (int i = 0; i < nodes; i++) {
                sum = task16_helper(sum, data, indexStart + 2, accrued);
                childSums.add(sum);
            }
            for (int i = 1; i <= metaDataNumber; i++) {
                metaData.add(data.get(indexStart + 2));
                data.remove(indexStart + 2);
            }
            //System.out.println(nodes +" " + metaDataNumber + " metadata " + metaData);
            for (int metaDataElement: metaData) {
                if (childSums.size() >= metaDataElement) {
                    sum = childSums.get(metaDataElement - 1);
                    accrued += sum;
                }
            }
            data.remove(indexStart);
            data.remove(indexStart);
            return accrued;
        }
        else {
            sum = 0;
            for (int i = 1; i <= metaDataNumber; i++) {
                int entry = data.get(indexStart + 2);
                sum += entry;
                data.remove(indexStart);
            }
            data.remove(indexStart);
            data.remove(indexStart);
            return sum;
        }
    }


}
