package gametheory.strategiesImpl;

import gametheory.Strategy;

import java.util.Random;

public class Simpleton extends Strategy {
    private Random random;

    public Simpleton() {
        random = new Random();
    }

    @Override
    public int makeMove() {
        return random.nextInt(2);  // Randomly return 0 (defect) or 1 (cooperate)
    }
}
