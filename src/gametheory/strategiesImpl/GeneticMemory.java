package gametheory.strategiesImpl;

import gametheory.Strategy;
import java.util.ArrayList;
import java.util.List;

public class GeneticMemory extends Strategy {
    private List<Integer> opponentMoves = new ArrayList<>();
    private List<Integer> myMoves = new ArrayList<>();
    private int memoryLength = 5;  // Number of past moves to consider
    private int defectThreshold = 3;  // Threshold for deciding to defect

    @Override
    public int makeMove() {
        if (opponentMoves.isEmpty()) {
            return 1;  // Cooperate on the first move
        }

        int opponentDefects = countDefections(opponentMoves);

        if (opponentDefects >= defectThreshold) {
            return 0;  // Defect if the opponent defected frequently
        }

        return 1;  // Otherwise, cooperate
    }

    public void updateMemory(int myMove, int opponentMove) {
        myMoves.add(myMove);
        opponentMoves.add(opponentMove);

        if (opponentMoves.size() > memoryLength) {
            opponentMoves.remove(0);  // Remove oldest memory
            myMoves.remove(0);
        }
    }

    private int countDefections(List<Integer> moves) {
        int count = 0;
        for (Integer move : moves) {
            if (move == 0) {
                count++;
            }
        }
        return count;
    }
}
