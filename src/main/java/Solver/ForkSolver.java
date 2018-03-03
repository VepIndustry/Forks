package Solver;

import Fork.GeneratorFork.Fork;

import java.util.Set;

/**
 * Интерфейс определяющий поведение которое будет тем или иным образом реализовывать вилки
 */
public interface ForkSolver {

    boolean solve(Set<Fork> forks);

}
