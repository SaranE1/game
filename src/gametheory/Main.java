package gametheory;

import gametheory.strategiesImpl.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // List of all strategies
        List<Strategy> allStrategies = Arrays.asList(
                new AlwaysCooperate(),
                new AlwaysDefect(),
                new GeneticMemory(),
                new GeneticOneMove(),
                new GeneticStrategy(0.5), // Example with 0.5 parameter for GeneticStrategy
                new Grudger(),
                new Simpleton(),
                new TitForTat()
        );

        // Display all strategies
        System.out.println("Available strategies:");
        for (int i = 0; i < allStrategies.size(); i++) {
            System.out.println((i + 1) + ". " + allStrategies.get(i).getStrategyName());
        }

        Scanner scanner = new Scanner(System.in);

        // Get user input to assign 3 strategies to Player 1
        Set<Strategy> player1Strategies = selectStrategies(scanner, allStrategies, "Player 1");

        // Get user input to assign 3 strategies to Player 2, ensuring no overlap with Player 1's choices
        Set<Strategy> player2Strategies = selectStrategies(scanner, allStrategies, "Player 2", player1Strategies);

        // Display strategies for Player 1 and Player 2
        displaySelectedStrategies(player1Strategies, "Player 1");
        displaySelectedStrategies(player2Strategies, "Player 2");

        // Ask user for the number of rounds they want to play
        System.out.print("\nEnter the number of rounds you want to play: ");
        int rounds = scanner.nextInt();

        // Game loop for the selected number of rounds
        for (int currentRound = 1; currentRound <= rounds; currentRound++) {
            System.out.println("\nRound " + currentRound);

            // Player 1 strategy selection
            Strategy player1Strategy = selectStrategy(scanner, player1Strategies, "Player 1");

            // Player 2 strategy selection
            Strategy player2Strategy = selectStrategy(scanner, player2Strategies, "Player 2");

            // Create the game with the selected strategies
            Game game = new Game(player1Strategy, player2Strategy);

            // Execute the game for this round
            List<Integer> results = game.executeGame(1); // Execute for 1 round

            // Print the results: Player 1's score and Player 2's score
            System.out.println("Game Results: ");
            System.out.println("Player 1's score = " + results.get(0));
            System.out.println("Player 2's score = " + results.get(1));
        }
    }

    // Helper method to select strategies for a player (ensuring no duplicates)
    private static Set<Strategy> selectStrategies(Scanner scanner, List<Strategy> allStrategies, String playerName) {
        return selectStrategies(scanner, allStrategies, playerName, new HashSet<>());
    }

    // Helper method to select strategies for a player (ensuring no duplicates)
    private static Set<Strategy> selectStrategies(Scanner scanner, List<Strategy> allStrategies, String playerName, Set<Strategy> otherPlayerStrategies) {
        Set<Strategy> selectedStrategies = new HashSet<>();
        System.out.println("\nAssign 3 strategies to " + playerName + " from the above list:");
        while (selectedStrategies.size() < 3) {
            int choice = getValidStrategyChoice(scanner, allStrategies);
            Strategy selectedStrategy = allStrategies.get(choice - 1);
            if (otherPlayerStrategies.contains(selectedStrategy)) {
                System.out.println("This strategy has already been assigned to the other player. Please select another.");
            } else {
                selectedStrategies.add(selectedStrategy);
            }
        }
        return selectedStrategies;
    }

    // Helper method to display selected strategies
    private static void displaySelectedStrategies(Set<Strategy> strategies, String playerName) {
        System.out.println("\n" + playerName + "'s assigned strategies:");
        strategies.forEach(strategy -> System.out.println(strategy.getStrategyName()));
    }

    // Helper method to select a strategy for a player
    private static Strategy selectStrategy(Scanner scanner, Set<Strategy> strategies, String playerName) {
        List<Strategy> strategyList = new ArrayList<>(strategies);
        System.out.println("\nAvailable strategies for " + playerName + ":");
        for (int i = 0; i < strategyList.size(); i++) {
            System.out.println((i + 1) + ". " + strategyList.get(i).getStrategyName());
        }

        int choice = getValidStrategyChoice(scanner, strategyList);
        return strategyList.get(choice - 1);
    }

    // Helper method to get a valid strategy choice from the user
    private static int getValidStrategyChoice(Scanner scanner, List<Strategy> strategies) {
        int choice;
        while (true) {
            System.out.print("Enter the number for the strategy: ");
            choice = scanner.nextInt();
            if (choice < 1 || choice > strategies.size()) {
                System.out.println("Invalid input. Please select a valid strategy number.");
            } else {
                break;
            }
        }
        return choice;
    }
}
