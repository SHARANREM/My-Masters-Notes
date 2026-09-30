import pandas as pd
import matplotlib.pyplot as plt

df = pd.DataFrame({
    "Name": ["A", "B", "C", "D", "E"],
    "Marks": [60, 75, 80, 65, 90]
})

df.plot.bar(x="Name", y="Marks")
plt.show()

df["Marks"].plot.hist()
plt.show()

df["Marks"].plot.line()
plt.show()

df.plot.scatter(x="Name", y="Marks")
plt.show()