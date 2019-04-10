/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] results;
    private double mean;
    private double standarddev;
    private double ratio = 1.96;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException(
                    "N must be greater than 0 and trials must be greater than 0");
        }
        results = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation trial = new Percolation(n);
            boolean percolates = trial.percolates();
            while (!percolates) {
                int randRow = StdRandom.uniform(1, n + 1);
                int randCol = StdRandom.uniform(1, n + 1);
                if (!trial.isOpen(randRow, randCol)) {
                    trial.open(randRow, randCol);
                    percolates = trial.percolates();
                }
            }
            results[i] = (double) trial.numberOfOpenSites() / (double) (n * n);
        }
        mean = mean();
        standarddev = stddev();

    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return mean - (ratio * standarddev / Math.sqrt(results.length));
    }

    public double confidenceHi() {
        return mean + (ratio * standarddev / Math.sqrt(results.length));
    }

    public static void main(String[] args) {
        PercolationStats simulation = new PercolationStats(Integer.parseInt(args[0]),
                                                           Integer.parseInt(args[1]));
        System.out.println("mean                    = " + simulation.mean());
        System.out.println("stddev                  = " + simulation.stddev());
        System.out.println(
                "95% confidence interval = [" + simulation.confidenceLo() + ", " + simulation
                        .confidenceHi() + "]");
    }
}
