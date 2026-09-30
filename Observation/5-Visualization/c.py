import pandas as pd

df1 = pd.DataFrame({"ID": [1, 2], "Name": ["Arun", "Bala"]})
df2 = pd.DataFrame({"ID": [1, 2], "Marks": [80, 90]})

# Merging
print("Merge:\n", pd.merge(df1, df2, on="ID"))

# Joining
print("\nJoin:\n", df1.join(df2.set_index("ID"), on="ID"))

# Concatenating
print("\nConcat:\n", pd.concat([df1, df1]))