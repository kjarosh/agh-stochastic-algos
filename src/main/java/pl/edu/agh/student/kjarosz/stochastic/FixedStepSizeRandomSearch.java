package pl.edu.agh.student.kjarosz.stochastic;

import java.util.Random;

import static pl.edu.agh.student.kjarosz.stochastic.Rosenbrock.rosenbrock;

public class FixedStepSizeRandomSearch {
    private static final Random rand = new Random();
    private static float RADIUS = .5f;

    public static void main(String[] args) {
        Point lower = new Point(-5f, -5f);
        Point upper = new Point(5f, 5f);

        fssrs(lower, upper, 10_000);
    }

    private static void fssrs(Point lower, Point upper, int steps) {
        Point current = Point.randpoint(lower, upper);
        for (int step = 0; step < steps; ++step) {
            float r = rand.nextFloat() * RADIUS;
            double a = rand.nextFloat() * 2 * Math.PI;
            Point next = new Point(current.x + (float) (r * Math.sin(a)), current.y + (float) (r * Math.cos(a)));
            if(rosenbrock(next) < rosenbrock(current)){
                current = next;
            }
        }

        System.out.println(current);
        System.out.println(rosenbrock(current));
    }
}
