public class QuickUnion implements UnionFind {

    private final int[] id;
    private int count;

    public QuickUnion(final int size) {
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
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
        id[pRoot] = qRoot;
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