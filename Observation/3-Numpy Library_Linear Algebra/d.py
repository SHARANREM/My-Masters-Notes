import numpy as np

A = np.array([[2, 1], [1, 3]])
B = np.array([5, 6])

X = np.linalg.solve(A, B)

print("Solution:", X)