import numpy as np

arr = np.array([[1, 2], [3, 4]])

print("Array:\n", arr)
print("Rank:", np.linalg.matrix_rank(arr))
print("Determinant:", np.linalg.det(arr))
print("Trace:", np.trace(arr))