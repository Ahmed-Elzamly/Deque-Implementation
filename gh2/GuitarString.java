package gh2;
import deque.ArrayDeque;

public class GuitarString {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private ArrayDeque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        long sz = Math.round(SR / frequency);
        buffer = new ArrayDeque<>();
        for (long i = 0; i < sz; i++) {
            buffer.addLast(0.0);
        }

    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        for (int i = 0; i < buffer.size(); i++) {
            double r = Math.random() - 0.5;
            buffer.removeFirst();
            buffer.addLast(r);
        }
    }
    public void tic() {
        double first = buffer.removeFirst();
        double second = buffer.get(0);
        double newDouble = (first + second) * DECAY / 2;
        buffer.addLast(newDouble);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(buffer.size() - 1);
    }
}
