package pl.edu.agh.student.kjarosz.stochastic;

import java.util.Random;

import static pl.edu.agh.student.kjarosz.stochastic.Rosenbrock.rosenbrock;

public class AdaptiveStepSizeRandomSearch {
    private static final Random rand = new Random();
    private static float RADIUS = .5f;
    private static float DRADIUS = .001f;
    private static int I = 6;

    public static void main(String[] args) {
        Point lower = new Point(-5f, -5f);
        Point upper = new Point(5f, 5f);

        assrs(lower, upper, 10_000);
    }

    private static void assrs(Point lower, Point upper, int steps) {
        Point current = Point.randpoint(lower, upper);

        int higher_count = 0;
        for (int step = 0; step < steps; ++step) {
            float r = rand.nextFloat() * RADIUS;
            double a = rand.nextFloat() * 2 * Math.PI;
            Point next = new Point(current.x + (float) (r * Math.sin(a)), current.y + (float) (r * Math.cos(a)));
            r = RADIUS + rand.nextFloat() * DRADIUS;
            a = rand.nextFloat() * 2 * Math.PI;
            Point next2 = new Point(current.x + (float) (r * Math.sin(a)), current.y + (float) (r * Math.cos(a)));

            float next_val = rosenbrock(next);
            float next2_val = rosenbrock(next2);

//            System.out.println(RADIUS);
//            System.out.println(current);
//            System.out.println(next + " " + next_val);
//            System.out.println(next2 + " " + next2_val);
//            System.out.println();

            if (next_val < next2_val) {
                higher_count += 1;
                if (higher_count >= I) {
                    higher_count = 0;
                    RADIUS -= DRADIUS;
                }

                if (next_val < rosenbrock(current)) {
                    current = next;
                }
            } else {
                higher_count = 0;
                RADIUS += DRADIUS;
                if (next2_val < rosenbrock(current)) {
                    current = next2;
                }
            }
        }

        System.out.println(current);
        System.out.println(rosenbrock(current));
        System.out.println(RADIUS);
    }
}
