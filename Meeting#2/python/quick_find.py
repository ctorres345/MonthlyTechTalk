class QuickFind:

    def __init__(self, size):
        self._ids = list(range(0, size))
        self._count = size
        pass

    def connected(self, p: int, q: int) -> bool:
        return self.find(p) == self.find(q)

    def union(self, p: int, q: int) -> None:
        if p == q:
            return
        p_id = self.find(p)
        q_id = self.find(q)
        if p_id == q_id:
            return
        for i in range(0, len(self._ids)):
            if self._ids[i] == p_id:
                self._ids[i] = q_id
        self._count -= 1

    def count(self) -> int:
        return self._count

    def find(self, p: int) -> int:
        if p < 0 or p >= len(self._ids):
            raise ValueError(f"P value {p} isn't between 0 and {len(self._ids)}")
        return self._ids[p]
