import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt

######loading data
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

######Setting up the environment
pd.set_option('display.max_rows', 50)
pd.set_option('display.max_columns', None)
pd.set_option('display.width', 1000)
sns.set_theme(style="whitegrid", context="notebook")

######Data minipulation
diamonds_df["large_diamond"] = np.where(diamonds_df["carat"] > 1, 1, 0) #adds a new column and assigns 1 if carat is greater than 1, else assigns 0

######Data visualization
print(diamonds_df.head()) #standared output of the first 5 rows of the dataframe
print(diamonds_df.head(10)) #standared output of the first 10 rows of the dataframe

print(diamonds_df.shape) 
print(diamonds_df.dtypes) 


