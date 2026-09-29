import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
import math

diamonds_df = sns.load_dataset("diamonds")
sns.histplot(
    data = diamonds_df['price'],
    bins = 40,
    edgecolor = 'black'
)
plt.title("Distribution of Diamond Prices")
plt.xlabel("Price (USD)")
plt.ylabel("Frequency")
plt.tight_layout()
plt.show()
sns.scatterplot(
    data = diamonds_df.sample(n = min(5000, len(diamonds_df)),random_state = 42),
    x = 'carat',
    y = 'price',
    hue = 'cut',
    alpha = 0.5
)
plt.title("Carat vs. Price by Cut")
plt.xlabel("Carat")
plt.ylabel("Price (USD)")
plt.legend(loc = 'upper left')
plt.tight_layout()
plt.show()

sns.boxplot(
    data = diamonds_df,
    x = 'cut',
    y = 'price',
    showfliers = False,
    boxprops = {'edgecolor': 'red'},
    whiskerprops = {'color': 'red'},
    capprops = {'color' : 'red'},
    medianprops = {'color':'red'}
)
plt.title("Price Distribution by Cut")
plt.xlabel("Cut")
plt.ylabel("Price (USD)")
plt.tight_layout()
plt.show()

diamonds_df['price_per_carat'] = diamonds_df['price']/diamonds_df['carat']
plt.figure()
sns.violinplot(
    data = diamonds_df,
    x = 'cut',
    y = 'price_per_carat'
)
sns.boxplot(
    data = diamonds_df,
    x = 'cut',
    y = 'price_per_carat',
    whis = 1.5,
    width = 0.25,
    showcaps = True,
    boxprops = {'facecolor': 'white', 'edgecolor': 'red'},
    whiskerprops = {'color': 'red'},
    capprops = {'color' : 'red'},
    medianprops = {'color':'red'},
    showfliers = False
)
plt.title("Price per Carat by Cut (Violin + Box Overlay)")
plt.xlabel("Cut")
plt.ylabel("Price per Carat (USD)")
plt.tight_layout()
plt.show()

numeric_col = diamonds_df.select_dtypes(include = 'number')
corr = numeric_col.corr(numeric_only = True)
sns.heatmap(
    data = corr,
    annot = False,
    cmap = 'vlag',
    center = 0,
    square = True,
    cbar_kws = {'shrink': 0.8}
)
plt.title("Correlation Heatmap of Numeric Features")
plt.tight_layout()
plt.show()
