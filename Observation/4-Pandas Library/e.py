import pandas as pd

df = pd.DataFrame({
    "A": [10, 20, 30],
    "B": [40, 50, 60]
})

series = pd.Series([70, 80, 90])

print("DataFrame to NumPy:\n", df.to_numpy())
print("Series to NumPy:", series.to_numpy())