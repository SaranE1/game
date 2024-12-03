package gametheory.strategiesImpl;

import gametheory.Strategy;

public class AlwaysCooperate extends Strategy {
    @Override
    public int makeMove() {
        return 1; // Always cooperates
    }
}
