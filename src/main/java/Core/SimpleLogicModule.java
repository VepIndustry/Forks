package Core;

import Fork.GeneratorFork.Fork;

import java.util.HashSet;
import java.util.Set;

public class SimpleLogicModule implements LogicModule {
    @Override
    public Set<Fork> calculate(Set<Fork> forks) {
        Set<Fork> validForks = new HashSet<>();
        for (Fork fork : forks) {
            if (fork.calculateUsuallyPercent() > 2) {
                validForks.add(fork);
            }
        }
        return validForks;
    }
}
