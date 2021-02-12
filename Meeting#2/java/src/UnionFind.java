public interface UnionFind {

    /**
     * @param p element index.
     * @param q element index.
     * @return True if p and q are connected.
     */
    boolean connected(final int p, final int q);

    /**
     * Adds a connection between p and q.
     *
     * @param p element index.
     * @param q element index.
     */
    void union(final int p, final int q);

    /**
     * @return Number of connected components.
     */
    int count();

    /**
     * @param p element index.
     * @return Component identifier (root element) for p.
     */
    int find(int p);

}