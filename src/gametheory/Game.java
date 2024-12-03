package gametheory;

import java.util.Arrays;
import java.util.List;

public class Game {
    private Strategy player1Strategy;
    private Strategy player2Strategy;

    // Define constants for the Prisoner's Dilemma scores
    private static final int COOPERATE_REWARD = 3;
    private static final int DEFECT_PENALTY = 5;
    private static final int BOTH_DEFECT_SCORE = 1;

    public Game(Strategy player1Strategy, Strategy player2Strategy) {
        this.player1Strategy = player1Strategy;
        this.player2Strategy = player2Strategy;
    }

    // Method to execute the game for a given number of rounds
    public List<Integer> executeGame(int rounds) {
        int player1Score = 0;
        int player2Score = 0;

        for (int i = 0; i < rounds; i++) {
            // Get the moves for each player (now returns int: 1 for cooperate, 0 for defect)
            int player1Move = player1Strategy.makeMove();
            int player2Move = player2Strategy.makeMove();

            // Scoring system based on the moves
            if (player1Move == 1 && player2Move == 1) {
                player1Score += COOPERATE_REWARD; // Both cooperate
                player2Score += COOPERATE_REWARD;
            } else if (player1Move == 1 && player2Move == 0) {
                player1Score += 0; // Player 1 cooperates, Player 2 defects
                player2Score += DEFECT_PENALTY;
            } else if (player1Move == 0 && player2Move == 1) {
                player1Score += DEFECT_PENALTY; // Player 1 defects, Player 2 cooperates
                player2Score += 0;
            } else {
                player1Score += BOTH_DEFECT_SCORE; // Both defect
                player2Score += BOTH_DEFECT_SCORE;
            }

            // Update strategies with the opponent's move for future rounds
            player1Strategy.addOpponentMove(player2Move);  // Store the move as an int for future reference
            player2Strategy.addOpponentMove(player1Move);  // Store the move as an int for future reference
        }

        // Return the scores for both players
        return Arrays.asList(player1Score, player2Score);
    }
}
