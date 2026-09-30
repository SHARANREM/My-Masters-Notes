import numpy as np

arr = np.array([10, 20, 30, 40, 50])

# Slicing
print("Slicing:", arr[1:4])

# Integer indexing
print("Integer indexing:", arr[[0, 2, 4]])

# Boolean indexing
print("Boolean indexing:", arr[arr > 25])