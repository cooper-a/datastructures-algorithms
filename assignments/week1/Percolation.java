/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int openSites = 0;
    private int nSize;
    private boolean[][] isOpen;
    private int[][] materials;
    private WeightedQuickUnionUF unionData;

    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException("N must be greater than 0");
        }
        isOpen = new boolean[n][n];
        materials = new int[n][n];
        int uniqueId = 0;
        nSize = n;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                materials[row][col] = uniqueId;
                isOpen[row][col] = false;
                uniqueId += 1;
            }
        }
        unionData = new WeightedQuickUnionUF((n * n) + 2);

        // Create union of bottom row and union of top row
        // n * n is connected to top
        // (n * n) + 1 is connected to bottom
        int u = n * n;
        for (int z = 0; z < n; z++) {
            unionData.union(z, n * n);
            unionData.union(u - 1, u + z + 1);
            u -= 1;
        }
    }

    public void open(int row, int col) {
        if ((row > nSize + 1 || row < 1) || (col > nSize + 1 || col < 1)) {
            throw new java.lang.IllegalArgumentException("Cols or Rows out of range");
        }

        int realRow = row - 1;
        int realCol = col - 1;

        if (!isOpen[realRow][realCol]) {
            isOpen[realRow][realCol] = true;
            openSites += 1;
            if (realCol < (nSize - 1) && isOpen[realRow][realCol + 1]) {
                unionData.union(materials[realRow][realCol + 1], materials[realRow][realCol]);
            }
            if (realRow < (nSize - 1) && isOpen[realRow + 1][realCol]) {
                unionData.union(materials[realRow + 1][realCol], materials[realRow][realCol]);
            }
            if (realCol > 0 && isOpen[realRow][realCol - 1]) {
                unionData.union(materials[realRow][realCol - 1], materials[realRow][realCol]);
            }
            if (realRow > 0 && isOpen[realRow - 1][realCol]) {
                unionData.union(materials[realRow - 1][realCol], materials[realRow][realCol]);
            }
        }

    }

    public boolean isOpen(int row, int col) {
        if ((row > nSize + 1 || row < 1) || (col > nSize + 1 || col < 1)) {
            throw new java.lang.IllegalArgumentException("Cols or Rows out of range");
        }
        else {
            return isOpen[row - 1][col - 1];
        }
    }

    public boolean isFull(int row, int col) {
        if ((row > nSize + 1 || row < 1) || (col > nSize + 1 || col < 1)) {
            throw new java.lang.IllegalArgumentException("Cols or Rows out of range");
        }
        else {
            return unionData.connected(nSize * nSize, materials[row - 1][col - 1]) && isOpen(row,
                                                                                             col);
        }
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return unionData.connected(nSize * nSize, (nSize * nSize) + 1);
    }

    public static void main(String[] args) {

    }
}
