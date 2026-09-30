import numpy as np

a = np.array([1, 2, 3])
b = np.array([4, 5, 6])

print("Dot product:", np.dot(a, b))
print("Inner product:", np.inner(a, b))
print("Outer product:\n", np.outer(a, b))
print("Product:", np.prod(a))

m = np.array([[1, 2], [3, 4]])
print("Matrix exponentiation:\n", np.linalg.matrix_power(m, 2))