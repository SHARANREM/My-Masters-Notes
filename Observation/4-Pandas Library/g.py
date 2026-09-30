import pandas as pd

df = pd.DataFrame({
    "Name": ["Arun", "Bala", "Kiran"],
    "Age": [20, 21, 22]
})

# Row selection
print("First row:\n", df.iloc[0])

# Row addition
df.loc[3] = ["Ravi", 23]
print("\nAfter adding row:\n", df)

# Row deletion
df = df.drop(1)
print("\nAfter deleting row:\n", df)