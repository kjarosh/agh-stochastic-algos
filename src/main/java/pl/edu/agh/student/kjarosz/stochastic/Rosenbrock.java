package pl.edu.agh.student.kjarosz.stochastic;

public class Rosenbrock {
    private static float PARAM_A = 1f;
    private static float PARAM_B = 100f;

    public static float rosenbrock(Point p) {
        float x = p.x;
        float y = p.y;
        float c = PARAM_A - x;
        float d = PARAM_B * (y - x * x);
        return c * c + d * d;
    }
}
