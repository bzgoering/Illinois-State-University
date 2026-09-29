import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
import math
mpg_df = sns.load_dataset("mpg")

#Graph 1
plt.figure()
sns.histplot(
    data = mpg_df.weight,
    bins = 40,
    kde = False
)
plt.axvline(
    x = np.median(mpg_df.weight),
    color = 'orange',
    label = "Median"
)
plt.axvline(
    x = np.mean(mpg_df.weight),
    color = 'red',
    label = "Mean"
)
plt.title("Distribution of Vehicle Weight")
plt.xlabel("Weight")
plt.ylabel("Frequency")
plt.legend(loc = 'upper right')

#Graph 2
mpg_clean = mpg_df.dropna(subset=['mpg', 'cylinders']).copy()
plt.figure()
sns.boxplot(
    data = mpg_clean,
    x = 'cylinders',
    y = 'mpg',
    boxprops = {'edgecolor': 'red'},
    whiskerprops = {'color': 'red'},
    capprops = {'color': 'red'}
)
plt.title("MPG by Number of Cylinders")
plt.xlabel("Number of Cylinders")
plt.ylabel("MPG")

plt.show()
