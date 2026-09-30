import numpy as np

arr = np.array([10, 30, 20, 30, 40, 30])

# Sorting
print("Sorted:", np.sort(arr))

# Searching
print("Position of 30:", np.where(arr == 30)[0])

# Counting
print("Count of 30:", np.count_nonzero(arr == 30))