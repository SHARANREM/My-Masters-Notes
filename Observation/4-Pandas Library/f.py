import pandas as pd

df = pd.DataFrame({
    "Name": ["Arun", "Bala", "Kiran"],
    "Age": [20, 21, 22]
})

# Column selection
print("Name column:\n", df["Name"])

# Column addition
df["Marks"] = [80, 85, 90]
print("\nAfter adding column:\n", df)

# Column deletion
df = df.drop("Age", axis=1)
print("\nAfter deleting column:\n", df)