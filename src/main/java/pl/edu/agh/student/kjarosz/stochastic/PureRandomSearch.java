package pl.edu.agh.student.kjarosz.stochastic;

import java.util.*;
import java.util.function.Supplier;

import static pl.edu.agh.student.kjarosz.stochastic.Rosenbrock.rosenbrock;

public class PureRandomSearch {
    private static float PARAM_A = 1f;
    private static float PARAM_B = 100f;

    public static void main(String[] args) {
        new PureRandomSearch().run();
    }

    private void run() {
        prs(1_000_000, 3,
                new Point(5f, 5f), new Point(-5f, -5f));
    }

    private void prs(int steps, int count, Point upper, Point lower) {
        Point current = Point.randpoint(upper, lower);

        for (; steps > 0; --steps) {
            Point next = Point.randpoint(upper, lower);

            float val = rosenbrock(next);
            if (val < rosenbrock(current)) {
                current = next;
            }
        }
        System.out.println(current);
        System.out.println(rosenbrock(current));
    }
}
