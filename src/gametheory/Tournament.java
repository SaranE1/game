package gametheory;

import gametheory.strategiesImpl.*;

import java.util.*;

public class Tournament {

    private HashMap<Strategy, Integer> points;

    Tournament(List<Strategy> strategies) {
        this.points = new HashMap<>();
        // Initialize points for each strategy
        for (Strategy s : strategies) {
            this.points.put(s, 0);
        }
    }

    // Method to execute the tournament for a given number of rounds
    public HashMap<Strategy, Integer> executeTournamentRounds(int numRounds) {
        for (int i = 0; i < numRounds; i++) {
            addNewPoints(tournamentRound(10));  // Assuming 10 rounds per match, can be adjusted
        }
        return this.points;
    }

    // Method for a single round of the tournament
    private HashMap<Strategy, Integer> tournamentRound(int n) {
        HashMap<Strategy, Integer> tournamentPoints = new HashMap<>();
        Object[] strategies = this.points.keySet().toArray();

        // Iterate over all pairs of strategies
        for (int i = 0; i < strategies.length; i++) {
            for (int j = i + 1; j < strategies.length; j++) {
                // Create the game with the pair of strategies
                Game g = new Game((Strategy) strategies[i], (Strategy) strategies[j]);
                List<Integer> gameOutcome = g.executeGame(n);

                // Update the tournament points based on the game outcome
                tournamentPoints.put((Strategy) strategies[i], tournamentPoints.getOrDefault(strategies[i], 0) + gameOutcome.get(0));
                tournamentPoints.put((Strategy) strategies[j], tournamentPoints.getOrDefault(strategies[j], 0) + gameOutcome.get(1));
            }
        }
        return tournamentPoints;
    }

    // Add new points from a round to the overall points
    private void addNewPoints(HashMap<Strategy, Integer> newPoints) {
        for (Strategy s : newPoints.keySet()) {
            this.points.put(s, this.points.getOrDefault(s, 0) + newPoints.get(s));
        }
    }

    // Sort strategies based on their total points (descending order)
    public ArrayList<Map.Entry<Strategy, Integer>> sortEntries() {
        ArrayList<Map.Entry<Strategy, Integer>> sortedEntries = new ArrayList<>(this.points.entrySet());
        sortedEntries.sort((e1, e2) -> e2.getValue() - e1.getValue());
        return sortedEntries;
    }
}
