import pandas as pd

df = pd.DataFrame({
    "Name": ["Arun", "Bala", "Kiran", "Ravi", "Vijay"],
    "Marks": [80, 95, 70, 85, 90]
})

print("Largest 2:\n", df.nlargest(2, "Marks"))
print("\nSmallest 2:\n", df.nsmallest(2, "Marks"))