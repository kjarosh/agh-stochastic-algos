package pl.edu.agh.student.kjarosz.stochastic;

import java.util.Random;

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

    public static Point randpoint(Point upper, Point lower) {
        Random rand = new Random();
        return new Point(
                rand.nextFloat() * (upper.x - lower.x) + lower.x,
                rand.nextFloat() * (upper.y - lower.y) + lower.y);
    }
}
