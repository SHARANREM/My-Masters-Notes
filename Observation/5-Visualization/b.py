import pandas as pd

df = pd.DataFrame({
    "Department": ["CS", "IT", "CS", "IT"],
    "Marks": [80, 70, 90, 85]
})

result = df.groupby("Department")["Marks"].mean()

print(result)