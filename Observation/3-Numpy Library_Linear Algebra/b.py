import numpy as np

arr = np.array([[2, 1], [1, 2]])

eigenvalues = np.linalg.eigvals(arr)

print("Matrix:\n", arr)
print("Eigenvalues:", eigenvalues)