public class QuickFind implements UnionFind {

    private final int[] id;
    private int count;

    public QuickFind(final int size) {
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
        count = size;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void union(int p, int q) {
        if (p == q) {
            return;
        }
        final int pId = find(p);
        final int qId = find(q);
        if (pId == qId) {
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
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
        return id[p];
    }

}