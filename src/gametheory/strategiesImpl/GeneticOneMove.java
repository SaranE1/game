package gametheory.strategiesImpl;

import gametheory.Strategy;

public class GeneticOneMove extends Strategy {

    @Override
    public int makeMove() {
        // Payoff matrix for the two players (0 for defection, 1 for cooperation)
        // The matrix represents the payoff for each player (Man and Woman).
        int[][] payoffMatrix = {
                {2, 1},  // Man chooses boxing, Woman chooses boxing
                {0, 0},  // Man chooses boxing, Woman chooses ballet
                {0, 0},  // Man chooses ballet, Woman chooses boxing
                {1, 2}   // Man chooses ballet, Woman chooses ballet
        };

        // Calculate the weight for Player 1 (Man) based on normalized values
        double player1Weight = calculatePlayer1Weight(payoffMatrix);

        // Return 1 for boxing (if the weight > 0.5), else 0 for ballet
        // If the weight is greater than 0.5, it indicates a preference for boxing (1),
        // otherwise ballet (0) is preferred.
        return player1Weight > 0.5 ? 1 : 0;
    }

    // Method to calculate Player 1's weight based on normalized payoff matrix values
    private double calculatePlayer1Weight(int[][] payoffMatrix) {
        // Normalizing the payoff values for each choice to get the weight of Player 1's preference
        // Player 1's payoff for boxing vs ballet (rows 0 and 2 of the matrix)

        // For boxing: (Payoff of boxing - Payoff of ballet) normalized by the range of possible payoffs (max - min)
        // Normalize the difference for boxing (row 0 vs row 2)
        double boxing = (payoffMatrix[0][0] - payoffMatrix[2][0]) / (2.0 - 0.0);
        // PayoffMatrix[0][0] = 2 (Payoff for Man choosing boxing, Woman choosing boxing)
        // PayoffMatrix[2][0] = 0 (Payoff for Man choosing ballet, Woman choosing boxing)
        // Normalized difference for boxing is calculated as (2 - 0) / (2.0 - 0) = 1.0

        // For ballet: (Payoff of ballet - Payoff of boxing) normalized by the range of possible payoffs (max - min)
        // Normalize the difference for ballet (row 1 vs row 3)
        double ballet = (payoffMatrix[1][0] - payoffMatrix[3][0]) / (1.0 - 0.0);
        // PayoffMatrix[1][0] = 0 (Payoff for Man choosing boxing, Woman choosing ballet)
        // PayoffMatrix[3][0] = 1 (Payoff for Man choosing ballet, Woman choosing ballet)
        // Normalized difference for ballet is calculated as (0 - 1) / (1.0 - 0) = -1.0

        // Average the normalized differences to get an overall preference score (weight)
        // This average determines the likelihood of choosing boxing or ballet
        double weight = (boxing + ballet) / 2;

        // Calculation comments:
        // - `boxing` is the normalized difference between the payoffs of boxing vs ballet for Player 1.
        // - `ballet` is the normalized difference between the payoffs of ballet vs boxing for Player 1.
        // The final `weight` indicates the likelihood of Player 1 choosing boxing over ballet.
        // If `weight` is greater than 0.5, Player 1 is more likely to choose boxing (1);
        // otherwise, ballet (0) is preferred.

        return weight;
    }
}
