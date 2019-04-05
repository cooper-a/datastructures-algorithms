/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int openSites = 0;
    int nSize;

    public Percolation(int n) {
        int[][] materials = new int[n][n];
        boolean[][] isOpen = new boolean[n][n];
        int uniqueId = 0;
        nSize = n;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                materials[x][y] = uniqueId;
                isOpen[x][y] = false;
                uniqueId += 1;
            }
        }
        WeightedQuickUnionUF unionData = new WeightedQuickUnionUF(n * n);

    }

    public void open(int row, int col) {
        if ((row > nSize + 1 || row < 1) || (col > nSize + 1 || col < 1)) {
            throw new java.lang.IllegalArgumentException("Cols or Rows out of range");
        }
        int realRow = row - 1;
        int realCol = col - 1;
        

    }

    public boolean isOpen(int row, int col) {

    }

    public boolean isFull(int row, int col) {

    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {

    }


    public static void main(String[] args) {

    }
}
