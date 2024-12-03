package gametheory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Strategy {
    protected List<Integer> opponentMoveHistory;
    protected List<Integer> outcomes;

    // Constructor to initialize histories and outcomes
    public Strategy() {
        this.opponentMoveHistory = new ArrayList<>();
        this.outcomes = new ArrayList<>();
    }

    // Abstract method to define the strategy's move (0 = defect, 1 = cooperate)
    public abstract int makeMove();

    // Utility method to get the strategy's name
    public String getStrategyName() {
        return this.getClass().getSimpleName();
    }

    // Add the opponent's move to the history
    public void addOpponentMove(int opponentMove) {
        this.opponentMoveHistory.add(opponentMove);
    }

    // Get the total points from the outcomes
    public int getPoints() {
        return this.outcomes.stream().mapToInt(Integer::intValue).sum();
    }

    // Clear the strategy's internal state (used between rounds or games)
    public void clearStrategy() {
        this.outcomes.clear();
        this.opponentMoveHistory.clear();
    }

    // Add a round's outcome (points) to the strategy
    public void addOutcome(int outcome) {
        this.outcomes.add(outcome);
    }

    // Get an unmodifiable list of outcomes (points per round)
    public List<Integer> getOutcomes() {
        return Collections.unmodifiableList(outcomes);
    }

    // Optional: A method for mutating the strategy (for genetic algorithms or evolution)
    public void mutate() {
        // Implement mutation logic here if necessary
    }
}
