public class WeightedQuickUnion implements UnionFind {

    private final int[] id;
    private final int[] weight;
    private int count;

    public WeightedQuickUnion(final int size) {
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
        weight = new int[size];
        for (int i = 0; i < size; i++) {
            weight[i] = 1;
        }
        count = size;
    }

    @Override
    public boolean connected(final int p, final int q) {
        return find(p) == find(q);
    }

    @Override
    public void union(final int p, final int q) {
        if (p == q) {
            return;
        }
        final int pRoot = find(p);
        final int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        if (weight[pRoot] < weight[qRoot]) {
            id[pRoot] = qRoot;
            weight[qRoot] += weight[pRoot];
        } else {
            id[qRoot] = pRoot;
            weight[pRoot] += weight[qRoot];
        }
        count--;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int find(int p) {
        final int length = id.length;
        if (p < 0 || p >= length) {
            throw new IllegalArgumentException(String.format("P value %1$d isn't between 0 and %2$d", p, length));
        }
        return root(p);
    }

    private int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }

}