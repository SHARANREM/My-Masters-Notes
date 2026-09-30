import pandas as pd

# From CSV file
df1 = pd.read_csv("data.csv")
print("CSV Data:\n", df1)

# From Excel file
df2 = pd.read_excel("data.xlsx")
print("\nExcel Data:\n", df2)