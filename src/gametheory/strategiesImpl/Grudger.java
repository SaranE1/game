package gametheory.strategiesImpl;

import gametheory.Strategy;

public class Grudger extends Strategy {
    private boolean opponentBetrayed = false;

    @Override
    public int makeMove() {
        if (opponentBetrayed) {
            return 0;  // Defect if the opponent has ever defected
        }
        return 1;  // Cooperate if the opponent hasn't betrayed yet
    }

    public void updateMemory(int myMove, int opponentMove) {
        if (opponentMove == 0) {
            opponentBetrayed = true;  // The opponent defected, so hold a grudge
        }
    }
}
