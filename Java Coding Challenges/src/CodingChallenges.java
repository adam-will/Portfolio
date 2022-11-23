import java.util.*;

public class CodingChallenges {

    public static void main(String[] args) {

    }


    /*
========================================================================================================================
Battleship field validator
URL: https://www.codewars.com/kata/52bb6539a4cf1b12d90005b7/train/java

"Write a method that takes a field for well-known board game "Battleship" as an argument and returns true if it has a
 valid disposition of ships, false otherwise."
*/

    public static boolean fieldValidator(int[][] field) {

        // 1. Set up the variables
        HashMap<String, Integer> ships = new HashMap<>();
        ships.put("Battleship", 1);
        ships.put("Cruiser", 2);
        ships.put("Destroyer", 3);
        ships.put("Submarine", 4);
        String currentShip;
        int shipSize = 0;
        ArrayList<int[]> locations = new ArrayList<>();
        int longitude;
        int latitude;

        // 2. Extract individual locations
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 1) locations.add(new int[]{i, j});
            }
        }

        // 3. Check if the deck locations are valid
        if (!CodingChallenges.checkDeckIsValid(locations)) return false;

        // 4. Extract and count individual ships
        for (int i = 0; i < locations.size(); i++) {
            latitude = locations.get(i)[0];
            longitude = locations.get(i)[1];

            if (latitude >= 0) {
                shipSize = 1;
                locations.get(i)[0] = -1;
                locations.get(i)[1] = -1;
                int counter = longitude + 1;

                for (int j = i + 1; j < locations.size(); j++) {
                    if (locations.get(j)[1] == counter && locations.get(j)[0] == latitude) {
                        shipSize++;
                        locations.get(j)[0] = -1;
                        locations.get(j)[1] = -1;
                        counter++;
                    }
                }
                if (shipSize == 1) {
                    counter = latitude + 1;
                    for (int j = i + 1; j < locations.size(); j++) {
                        if (locations.get(j)[1] == longitude && locations.get(j)[0] == counter) {
                            shipSize++;
                            locations.get(j)[0] = -1;
                            locations.get(j)[1] = -1;
                            counter++;
                        }
                    }
                }

                if (shipSize > 4) return false;
                currentShip = CodingChallenges.identifyShips(shipSize);
                ships.replace(currentShip, ships.get(currentShip) - 1);
            }
        }

        // 5. Format and return the results
        return ships.values().stream().noneMatch(element -> element != 0);
    }

    private static boolean checkDeckIsValid(ArrayList<int[]> locations) {
        int latitude;
        int longitude;

        // Fail if:
        // a. Incorrect number of locations
        if (locations.size() != 20) return false;

        // b. Incorrect placement of locations
        for (int i = 0; i < locations.size(); i++) {
            latitude = locations.get(i)[0];
            longitude = locations.get(i)[1];
            // b.1 Overlapping or touching ships - 3 neighbouring locations not in one line
            if (CodingChallenges.findLocationIndex(longitude + 1, latitude, locations) >= 0 && CodingChallenges.findLocationIndex(longitude,
                    latitude + 1, locations) >= 0)
                return false;
            if (CodingChallenges.findLocationIndex(longitude, latitude + 1, locations) >= 0 && CodingChallenges.findLocationIndex(longitude - 1,
                    latitude, locations) >= 0)
                return false;
            if (CodingChallenges.findLocationIndex(longitude - 1, latitude, locations) >= 0 && CodingChallenges.findLocationIndex(longitude,
                    latitude - 1, locations) >= 0)
                return false;
            if (CodingChallenges.findLocationIndex(longitude, latitude - 1, locations) >= 0 && CodingChallenges.findLocationIndex(longitude + 1,
                    latitude, locations) >= 0)
                return false;
            // b.2 Any two locations diagonal to each other
            if (CodingChallenges.findLocationIndex(longitude + 1, latitude + 1, locations) >= 0
                    || CodingChallenges.findLocationIndex(longitude + 1, latitude - 1, locations) >= 0
                    || CodingChallenges.findLocationIndex(longitude - 1, latitude + 1, locations) >= 0
                    || CodingChallenges.findLocationIndex(longitude - 1, latitude - 1, locations) >= 0)
                return false;
        }
        return true;
    }

    private static int findLocationIndex(int longitude, int latitude, ArrayList<int[]> locations) {
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i)[1] == longitude && locations.get(i)[0] == latitude) return i;
        }
        return -1;
    }

    private static String identifyShips(int size) {
        switch (size) {
            case 1:
                return "Submarine";
            case 2:
                return "Destroyer";
            case 3:
                return "Cruiser";
            case 4:
                return "Battleship";
            default:
                return "";
        }
    }

    /*
     * =================================================================================================================
     * The Lift
     * URL: https://www.codewars.com/kata/58905bfa1decb981da00009e/train/java
     *
     * Imitates the behaviour of a lift. The full list of rules and requirements can be found under the link above.
     */

    public static int[] theLift(final int[][] queues, final int capacity) {

        // 1. Set up the variables
        List<Integer> floorsVisited = new LinkedList<>();
        Direction currentDirection = Direction.UP;
        int currentFloor = 0;
        List<Integer> liftOccupants = new LinkedList<>();
        queues[currentFloor] = CodingChallenges.letPeopleIn(currentFloor, liftOccupants, queues[currentFloor],
                capacity, currentDirection);

        // 2. Process the requests
        // Add ground floor to the list of visited floors
        if (queues[0].length == 0) floorsVisited.add(currentFloor);
        // Move people to their destinations and record the visited floors
        while (CodingChallenges.checkRequests(queues) || liftOccupants.size() > 0) {
            if (CodingChallenges.checkIfStopRequired(currentFloor, queues, liftOccupants, currentDirection, capacity)) {
                floorsVisited.add(currentFloor);
                CodingChallenges.letPeopleOut(currentFloor, liftOccupants);
                queues[currentFloor] = CodingChallenges.letPeopleIn(currentFloor, liftOccupants, queues[currentFloor]
                        , capacity, currentDirection);
                if (CodingChallenges.setDirection(queues, liftOccupants, currentFloor, currentDirection, capacity) != currentDirection) {
                    currentDirection = CodingChallenges.setDirection(queues, liftOccupants, currentFloor,
                            currentDirection, capacity);
                    queues[currentFloor] = CodingChallenges.letPeopleIn(currentFloor, liftOccupants,
                            queues[currentFloor], capacity, currentDirection);
                }
            }
            if (currentDirection == Direction.UP) {
                currentFloor++;
            } else {
                currentFloor--;
            }
        }
        // Go back to the ground floor
        if (floorsVisited.get(floorsVisited.size() - 1) != 0) {
            floorsVisited.add(0);
        }

        // 3. Format and return the results
        return floorsVisited.stream().mapToInt(Integer::intValue).toArray();
    }

    private static Direction setDirection(int[][] queues, List<Integer> liftOccupants, int currentFloor,
                                          Direction currentDirection, int capacity) {
        // While moving upwards
        if (currentDirection == Direction.UP) {
            // Keep current direction if any of the lift occupants requested any of the floors above the current floor
            if (liftOccupants.stream().max(Integer::compare).orElse(currentFloor) > currentFloor) {
                return currentDirection;
                // Keep current direction if there are people waiting on any of the floors above this floor and the lift
                // has capacity
            } else if (CodingChallenges.checkRequests(Arrays.copyOfRange(queues, currentFloor + 1, queues.length))
                    && liftOccupants.size() < capacity) {
                return currentDirection;
            } else {
                return Direction.DOWN;
            }
            // While moving downwards
        } else {
            // Keep current direction if any of the lift occupants requested any of the floors below the current floor
            if (liftOccupants.stream().min(Integer::compare).orElse(currentFloor) < currentFloor) {
                return currentDirection;
                // Keep current direction if there are people waiting on any of the floors below this floor and the lift
                // has capacity
            } else if (CodingChallenges.checkRequests(Arrays.copyOfRange(queues, 0, currentFloor))
                    && liftOccupants.size() < capacity) {
                return currentDirection;
                // Change direction to upwards if there are people on the current floor waiting to go upwards or
                // there are people waiting on any of the floors above the current floor and the lift has capacity
            } else if (CodingChallenges.checkRequests(Arrays.copyOfRange(queues, currentFloor, queues.length))
                    && liftOccupants.size() < capacity) {
                return Direction.UP;
            } else {
                return currentDirection;
            }
        }
    }

    private static boolean checkIfStopRequired(int currentFloor, int[][] queues, List<Integer> liftOccupants,
                                               Direction currentDirection, int capacity) {
        // Stop if any of the current lift occupants leaves on this floor
        if (liftOccupants.contains(currentFloor)) {
            return true;
        } else {
            if (currentDirection == Direction.UP) {
                // Stop if the current floor's queue contains people wishing to travel in the current direction
                if (Arrays.stream(queues[currentFloor]).filter(e -> e > currentFloor).findAny().isPresent()) {
                    return true;
                }
                // Stop if there are no more people waiting on the further floors and there are no occupants in the lift
                else if ((!CodingChallenges.checkRequests(Arrays.copyOfRange(queues, currentFloor + 1, queues.length)) && (liftOccupants.size() == 0))) {
                    return true;
                } else return false;
            } else {
                // Stop if current floor's queue contains people wishing to travel in the current direction
                if (Arrays.stream(queues[currentFloor]).filter(e -> e < currentFloor).findAny().isPresent()) {
                    return true;
                }
                // Stop if there are no more people waiting on the further floors and there are no occupants in the lift
                else if ((!CodingChallenges.checkRequests(Arrays.copyOfRange(queues, 0, currentFloor)) && (liftOccupants.size() == 0))) {
                    return true;
                } else return false;
            }
        }
    }

    private static void letPeopleOut(int currentFloor, List<Integer> liftOccupants) {
        liftOccupants.removeIf(e -> e == currentFloor);
    }

    private static int[] letPeopleIn(int currentFloor, List<Integer> liftOccupants, int[] floorQueue, int capacity,
                                     Direction currentDirection) {
        int personCounter = 0;
        List<Integer> remainingFloorQueue = new LinkedList<>();
        while (personCounter < floorQueue.length) {
            if ((currentDirection == Direction.UP && floorQueue[personCounter] > currentFloor && liftOccupants.size() < capacity)
                    || (currentDirection == Direction.DOWN && floorQueue[personCounter] < currentFloor) && liftOccupants.size() < capacity) {
                liftOccupants.add(floorQueue[personCounter]);
            } else {
                remainingFloorQueue.add(floorQueue[personCounter]);
            }
            personCounter++;
        }
        return remainingFloorQueue.stream().mapToInt(Integer::intValue).toArray();
    }

    private static boolean checkRequests(int[][] queues) {
        for (int i = 0; i < queues.length; i++) {
            if (queues[i].length > 0) {
                return true;
            }
        }
        return false;
    }


    /*
     * =================================================================================================================
     * Calculator
     * URL: https://www.codewars.com/kata/5235c913397cbf2508000048/train/java
     *
     * "A simple calculator that given a string of operators (), +, -, *, / and
     * numbers separated by spaces returns the value of that expression."
     */

    public static Double evaluate(String expression) {

        // 1. Set up the variables
        List<String> elements = new LinkedList<>(Arrays.asList(expression.split(" ")));

        // 2. Process operations stored within brackets
        while (elements.contains("(")) {
            int lastOpeningBracket = elements.lastIndexOf("(");
            int correspondingClosingBracket = elements
                    .subList(lastOpeningBracket, elements.size()).indexOf(")") + lastOpeningBracket;
            CodingChallenges.evaluate(elements.subList(lastOpeningBracket,
                    correspondingClosingBracket + 1));
        }

        // 3. Process the remaining operations
        CodingChallenges.evaluate(elements);

        // 4. Format and return the results
        return Double.parseDouble(elements.get(0));
    }

    /*
    Orchestrates calculations in terms of Order of Operations
     */
    private static void evaluate(List<String> aList) {
        for (int i = 0; i < aList.size(); i++) {
            if (aList.get(i).equals("*") || aList.get(i).equals("/")) {
                CodingChallenges.calculate(aList, i);
                i--;
            }
        }
        for (int i = 0; i < aList.size(); i++) {
            if (aList.get(i).equals("+") || aList.get(i).equals("-")) {
                CodingChallenges.calculate(aList, i);
                i--;
            }
        }
    }

    /*
    Performs the actual calculations
     */
    private static void calculate(List<String> aList, int operandIndex) {
        double result = 0;
        switch (aList.get(operandIndex)) {
            case "*":
                result = Double.parseDouble(aList.get(operandIndex - 1))
                        * Double.parseDouble(aList.get(operandIndex + 1));
                break;
            case "/":
                result = Double.parseDouble(aList.get(operandIndex - 1))
                        / Double.parseDouble(aList.get(operandIndex + 1));
                break;
            case "+":
                result = Double.parseDouble(aList.get(operandIndex - 1))
                        + Double.parseDouble(aList.get(operandIndex + 1));
                break;
            case "-":
                result = Double.parseDouble(aList.get(operandIndex - 1))
                        - Double.parseDouble(aList.get(operandIndex + 1));
                break;
        }

        CodingChallenges.reduce(aList, result, operandIndex - 1, operandIndex + 1);
    }

    /*
    Replaces the selected mathematical expression with its result
     */
    private static void reduce(List<String> aList, Double aResult, int aStartIndex, int anEndIndex) {
        // Remove the sub-expression
        for (int i = aStartIndex; i <= anEndIndex; i++) {
            aList.remove(aStartIndex);
        }
        // Add the result
        aList.add(aStartIndex, String.valueOf(aResult));

        // Remove brackets where present
        if ("(".equals(aList.get(0))) {
            aList.remove(0);
        }
        if (")".equals(aList.get(aList.size() - 1))) {
            aList.remove(aList.size() - 1);
        }
    }
}

enum Direction {
    DOWN,
    UP
}