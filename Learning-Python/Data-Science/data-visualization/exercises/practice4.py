import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
import math
mpg_df = sns.load_dataset("mpg")

mpg_clean = mpg_df.dropna(subset=['mpg', 'model_year']).copy()
mpg_clean['decade'] = pd.cut(
    mpg_clean['model_year'],
    bins = [69,79,89],
    labels = ["1970s", '1980s']
)
plt.figure()
sns.violinplot(
    data = mpg_clean,
    x = 'decade',
    y = 'mpg',
    inner = 'quart'
)
plt.title("MPG Distribution by Decade")
plt.xlabel("Decade")
plt.ylabel("Miles Per Gallon (MPG)")
plt.tight_layout()

plt.figure()
sns.violinplot(
    data = mpg_clean,
    x = 'decade',
    y = 'mpg',
    hue = 'origin',
    inner = 'quart',
    split = False
)
sns.stripplot(
    data = mpg_clean,
    x = 'decade',
    y = 'mpg',
    hue = 'origin',
    dodge = True,
    jitter = True,
    palette = ["white", "white", "white"],
    edgecolor = 'red',
    linewidth = 1,
    legend = False
)
plt.title("MPG Distribution by Decade and Origin")
plt.xlabel("Decade")
plt.ylabel("Miles Per Gallon (MPG)")
plt.tight_layout()
plt.show()


