package pl.edu.agh.student.kjarosz.stochastic;

import java.util.*;
import java.util.function.Supplier;

public class Main {
    private static float PARAM_A = 1f;
    private static float PARAM_B = 100f;

    private final Random rand = new Random();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        algo2(1_000_000, 3, 10,
                new Point(5f, 5f), new Point(-5f, -5f));
    }

    private float epsilon = 0.4f;

    private void algo2(int steps, int levels, int pointsCount, Point upper, Point lower) {
        List<Point> lastPoints = new ArrayList<>();
        Supplier<Point> randomizer = () -> {
            if (lastPoints.size() == 0) {
                return randpoint(upper, lower);
            }

            Point point = lastPoints.get(rand.nextInt(lastPoints.size()));
            float diff = rand.nextFloat() * 2 * epsilon - epsilon;
            float angle = rand.nextFloat() * 2 * epsilon - epsilon;

            Point random = new Point(
                    point.x + diff * (float) Math.cos(angle),
                    point.y + diff * (float) Math.sin(angle));
            return random;
        };
        for (int level = 0; level < levels; ++level, epsilon /= 100f) {
            Collection<Point> newPoints = prs(steps, randomizer, pointsCount);
            lastPoints.clear();
            lastPoints.addAll(newPoints);
        }

        System.out.println(lastPoints);
    }

    private Queue<Point> prs(int steps, Supplier<Point> randomizer, int count) {
        float minValue = Float.POSITIVE_INFINITY;
        Queue<Point> points = new PriorityQueue<>(count);

        for (; steps > 0; --steps) {
            Point p = randomizer.get();

            float val = rosenbrock(p.x, p.y);
            if (val < minValue) {
                minValue = val;
                if (points.size() == count) {
                    points.remove();
                }
                points.add(p);
            } else if(points.size() < count){
                points.add(p);
            }
        }
        System.out.println(points);
        return points;
    }

    private Point randpoint(Point upper, Point lower) {
        return new Point(
                rand.nextFloat() * (upper.x - lower.x) + lower.x,
                rand.nextFloat() * (upper.y - lower.y) + lower.y);
    }

    private float rosenbrock(float x, float y) {
        float c = PARAM_A - x;
        float d = PARAM_B * (y - x * x);
        return c * c + d * d;
    }

    @FunctionalInterface
    public interface Function2D {
        float apply(float x, float y);
    }

    public class Point {
        float x;
        float y;

        public Point() {
            this.x = 0f;
            this.y = 0f;
        }

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
