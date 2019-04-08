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
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                materials[x][y] = uniqueId;
                isOpen[x][y] = false;
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

        if (!isOpen[realCol][realRow]) {
            isOpen[realCol][realRow] = true;
            openSites += 1;
            if (isOpen[realCol][realRow + 1] && row != nSize) {
                unionData.union(materials[realCol][realRow + 1], materials[realCol][realRow]);
            }
            if (isOpen[realCol + 1][realRow] && col != nSize) {
                unionData.union(materials[realCol + 1][realRow], materials[realCol][realRow]);
            }
            if (isOpen[realCol][realRow - 1] && row > 1) {
                unionData.union(materials[realCol][realRow - 1], materials[realCol][realRow]);
            }
            if (isOpen[realCol - 1][realRow] && col > 1) {
                unionData.union(materials[realCol - 1][realRow], materials[realCol][realRow]);
            }
        }

    }

    public boolean isOpen(int row, int col) {
        if ((row > nSize + 1 || row < 1) || (col > nSize + 1 || col < 1)) {
            throw new java.lang.IllegalArgumentException("Cols or Rows out of range");
        }
        return isOpen[col - 1][row - 1];
    }

    public boolean isFull(int row, int col) {
        if ((row > nSize + 1 || row < 1) || (col > nSize + 1 || col < 1)) {
            throw new java.lang.IllegalArgumentException("Cols or Rows out of range");
        }
        return unionData.connected(nSize * nSize, materials[col - 1][row - 1]);
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
