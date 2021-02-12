import fileinput
import time

from quick_find import QuickFind
from quick_union import QuickUnion
from weighted_quick_union import WeightedQuickUnion

start = time.time_ns()
file_line_generator = (line.strip() for line in fileinput.input())
size = int(next(file_line_generator))
union_find = WeightedQuickUnion(size)
connected = 0
unions = 0
for line in file_line_generator:
    p, q = map(int, line.split(" "))
    connected += 1
    if union_find.connected(p, q):
        continue
    union_find.union(p, q)
    unions += 1
    # print(f"{p}, {q}")
    print(f"{unions} union operations.")
end = time.time_ns()
print(f"{size} sites.")
print(f"{connected} connection queries.")
print(f"{unions} union operations.")
print(f"{union_find.count()} connected components.")
print("Finished in {:.2f} milliseconds.".format((end - start) / 1000000))
