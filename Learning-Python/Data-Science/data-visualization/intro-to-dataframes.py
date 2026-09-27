import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
import math

diamonds_df = sns.load_dataset("diamonds")
print("First 5:")
print(diamonds_df.head())
print("\ndf Shape:")
print(diamonds_df.shape)
print("\ndf types:")
print(diamonds_df.dtypes)
print("\nmissing values:")
print(diamonds_df.isna().sum())

diamonds_df["price_per_carat"] = diamonds_df["price"]/diamonds_df["carat"]
diamonds_df["high_price_carat"] = np.where(diamonds_df['price_per_carat'] > 3500,1,0)
print(diamonds_df['price_per_carat'].head())
print(diamonds_df["high_price_carat"].head())
print(diamonds_df.value_counts())

diamonds_df = sns.load_dataset("diamonds")
diamonds_df['rounded carat'] = diamonds_df['carat'].apply(math.ceil)
print(diamonds_df[['rounded carat', 'carat']].head())
diamonds_df = diamonds_df.drop(columns=['rounded carat'])
print(diamonds_df.head())
