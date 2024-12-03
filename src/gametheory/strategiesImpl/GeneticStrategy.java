package gametheory.strategiesImpl;

import gametheory.Strategy;
import java.util.Random;

// GeneticStrategy for Player 2
public class GeneticStrategy extends Strategy {
    private double weight; // Probability of cooperating

    // Constructor that sets the initial weight (cooperation probability)
    public GeneticStrategy(double weight) {
        this.weight = weight;
    }

    // Make a move based on the current weight
    @Override
    public int makeMove() {
        // Cooperate with the probability equal to weight
        // If Math.random() generates a number less than weight, cooperate (return 1), else defect (return 0)
        return Math.random() < weight ? 1 : 0;
    }

    // Mutate the current strategy's weight, with a small random change
    public void mutate() {
        Random generator = new Random();

        // Randomly decide to increase or decrease the weight
        boolean positiveMutation = generator.nextDouble() > 0.5;

        // Mutation value: Random small change (between 0 and 0.004)
        double mutationValue = generator.nextDouble() * 0.004;

        // Apply the mutation to the weight, ensuring it stays between 0 and 1
        if (positiveMutation) {
            if (weight + mutationValue <= 1) {
                weight += mutationValue;
            }
        } else {
            if (weight - mutationValue >= 0) {
                weight -= mutationValue;
            }
        }
    }

    // Create a new GeneticStrategy with the current weight (mutated)
    public GeneticStrategy mutateNew() {
        return new GeneticStrategy(this.weight);
    }

    // Getter for the weight (useful for inspecting the strategy)
    public double getWeight() {
        return this.weight;
    }

    // Optionally, you can add a method to display the strategy's current state
    public void printState() {
        System.out.println("Current Weight: " + weight);
    }
}
