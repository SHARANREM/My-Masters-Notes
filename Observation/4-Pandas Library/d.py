import pandas as pd

df = pd.DataFrame({
    "Name": ["Arun", "Bala", "Kiran", "Ravi", "Vijay"],
    "Age": [20, 21, 22, 23, 24],
    "Marks": [80, 75, 90, 85, 95]
})

print("Describe:\n", df.describe())
print("\nHead:\n", df.head())
print("\nTail:\n", df.tail())