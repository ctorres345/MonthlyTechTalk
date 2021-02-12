class WeightedQuickUnion:

    def __init__(self, size):
        self._ids = list(range(0, size))
        self._weights = [1 for _ in range(size)]
        self._count = size
        pass

    def connected(self, p: int, q: int) -> bool:
        return self.find(p) == self.find(q)

    def union(self, p: int, q: int) -> None:
        if p == q:
            return
        p_root = self.find(p)
        q_root = self.find(q)
        if p_root == q_root:
            return
        if self._weights[p_root] < self._weights[q_root]:
            self._ids[p_root] = q_root
            self._weights[q_root] += self._weights[p_root]
        else:
            self._ids[q_root] = p_root
            self._weights[p_root] += self._weights[q_root]
        self._count -= 1

    def count(self) -> int:
        return self._count

    def find(self, p: int) -> int:
        if p < 0 or p >= len(self._ids):
            raise ValueError(f"P value {p} isn't between 0 and {len(self._ids)}")
        return self._root(p)

    def _root(self, i: int) -> int:
        while i != self._ids[i]:
            i = self._ids[i]
        return i
