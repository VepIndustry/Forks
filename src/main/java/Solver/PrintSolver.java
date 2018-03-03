package Solver;

import Fork.GeneratorFork.Fork;
import Fork.GeneratorFork.Shoulder;

import java.util.Set;

public class PrintSolver implements ForkSolver {
    @Override
    public boolean solve(Set<Fork> forks) {
        for (Fork fork : forks) {
            System.out.println(fork);
        }
        System.out.println("Total count of forks is " + forks.size());
        return true;
    }
}
