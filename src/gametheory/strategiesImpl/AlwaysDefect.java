package gametheory.strategiesImpl;

import gametheory.Strategy;

public class AlwaysDefect extends Strategy {
    @Override
    public int makeMove() {
        return 0; // Always defects
    }
}
