package gametheory.strategiesImpl;

import gametheory.Strategy;

import java.util.ArrayList;
import java.util.List;

public class TitForTat extends Strategy {

    private List<Integer> opponentMoveHistory;

    public TitForTat() {
        super();
        opponentMoveHistory = new ArrayList<>();
    }

    @Override
    public int makeMove() {
        if (opponentMoveHistory.isEmpty()) {
            return 1;  // Cooperate on the first move
        }
        return opponentMoveHistory.get(opponentMoveHistory.size() - 1);  // Mimic opponent's last move
    }

    public void updateHistory(int opponentMove) {
        opponentMoveHistory.add(opponentMove);
    }

    public List<Integer> getOpponentMoveHistory() {
        return opponentMoveHistory;
    }
}
