# Week 1

##Social network connectivity. Given a social network containing nn members and a log file containing mm timestamps at which times pairs of members formed friendships, design an algorithm to determine the earliest time at which all members are connected (i.e., every member is a friend of a friend of a friend ... of a friend). Assume that the log file is sorted by timestamp and that friendship is an equivalence relation. The running time of your algorithm should be m \log nmlogn or better and use extra space proportional to nn.

Using Quick Union-Find, track the size of the tree, perform unions corresponding with each connection. When the size of the tree reaches n, the most recent union timestamp would be the solution the to the problem

##Union-find with specific canonical element. Add a method find() to the union-find data type so that find(i) returns the largest element in the connected component containing ii. The operations, union(), connected(), and find() should all take logarithmic time or better.

##For example, if one of the connected components is \{1, 2, 6, 9\}{1,2,6,9}, then the find() method should return 99 for each of the four elements in the connected components.

Keep another array to keep track using the indexes of the array as correspondence to each value, to track the max size of its connections.

##Successor with delete. Given a set of nn integers S = \{ 0, 1, ... , n-1 \}S={0,1,...,n−1} and a sequence of requests of the following form:

##Remove xx from SS
##Find the successor of xx: the smallest yy in SS such that y \ge xy≥x.
##design a data type so that all operations (except construction) take logarithmic time or better in the worst case.

A variation of the Union Find Algorithm is used in this case.
