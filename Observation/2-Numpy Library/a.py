import numpy as np

# Array from list
list1 = [10, 20, 30]
array1 = np.array(list1, dtype=float)

# Array from tuple
tuple1 = (40, 50, 60)
array2 = np.array(tuple1, dtype=float)

print("Array from list:", array1)
print("Array from tuple:", array2)