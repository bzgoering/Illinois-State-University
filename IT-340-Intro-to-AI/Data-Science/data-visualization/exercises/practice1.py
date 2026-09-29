import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt

diamonds_url = (
    "https://raw.githubusercontent.com/TrainingByPackt/"
    "Interactive-Data-Visualization-with-Python/master/datasets/diamonds.csv"
)
diamonds_df = sns.load_dataset('diamonds')
try:
    diamonds_df = pd.read_csv(diamonds_url)
    print("[OK] Dataset successfully loaded from URL.\n")
except Exception as e:
    print(f"[Warning] Could not load from URL due to: {e}")
    print("Loading from Seaborn instead...\n")
    diamonds_df = sns.load_dataset('diamonds')

diamonds_df["large_diamond"] = np.where(diamonds_df["carat"] > 1, 1, 0)
print(diamonds_df.head())
print(diamonds_df['large_diamond'].value_counts())
sns.scatterplot(
    data = diamonds_df,
    x="carat",
    y="price",
    hue="cut",
    alpha = 0.5
)

mask = (diamonds_df["cut"] == "Premium") & (diamonds_df["color"] == "E")
diamonds_df["premium_diamond"] = np.where(mask, 1, 0)
print(diamonds_df.head())
print(diamonds_df['premium_diamond'].value_counts())

def round_up(price):
    return math.ceil(price/100)*100

diamonds_df["rounded_price_100"] = diamonds_df["price"].apply(round_up)
print(diamonds_df[["price", "rounded_price_100"]].head())
diamonds_df = diamonds_df.drop("rounded_price_100", axis=1)
print(diamonds_df.head())
plt.show()

