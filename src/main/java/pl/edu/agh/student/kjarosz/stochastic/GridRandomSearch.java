package pl.edu.agh.student.kjarosz.stochastic;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import static pl.edu.agh.student.kjarosz.stochastic.Rosenbrock.rosenbrock;

public class GridRandomSearch {
    public static void main(String[] args) {
        Point lower = new Point(-5f, -5f);
        Point upper = new Point(5f, 5f);

        TreeMap<Float, Point> points = new TreeMap<>();
        grs(lower, upper, 100, 3, 5, points);
        Map.Entry<Float, Point> e = points.pollFirstEntry();
        System.out.println(e.getKey());
        System.out.println(e.getValue());
    }

    private static void grs(Point lower, Point upper, int steps, int levels, int gridSize, TreeMap<Float, Point> points) {


        float xd = (upper.x - lower.x) / gridSize;
        float yd = (upper.y - lower.y) / gridSize;

        TreeMap<Float, Runnable> actions = new TreeMap<>();
        for (int step = 0; step < steps; ++step) {
            Point base = Point.randpoint(lower, new Point(lower.x + xd, lower.y + yd));

            for (int row = 0; row < gridSize; ++row) {
                for (int col = 0; col < gridSize; ++col) {
                    Point p = new Point(base.x + row * xd, base.y + col * yd);
                    Point l = new Point(lower.x + row * xd, lower.y + col * yd);
                    Point u = new Point(l.x + xd, l.y + yd);
                    actions.put(rosenbrock(p), () -> grs(l, u, steps, levels - 1, gridSize, points));
                }
            }
        }

        if (levels == 0) {
            points.put(rosenbrock(lower), lower);
            return;
        } else {
            for (int i = 0; i < 3; ++i) {
                actions.pollFirstEntry().getValue().run();
            }
        }
    }
}
