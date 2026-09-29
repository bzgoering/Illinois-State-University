import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
import math

diamonds_url = (
    "https://raw.githubusercontent.com/TrainingByPackt/"
    "Interactive-Data-Visualization-with-Python/master/datasets/diamonds.csv"
)

diamonds_df = sns.load_dataset('diamonds')

sns.histplot(
    data = diamonds_df,
    x = "price",
    bins = 50,
    kde = True
)

plt.title("Disribution of Diamond Prices")
plt.xlabel("Price (USD)")
plt.ylabel("Count")
plt.show()
color_list = ['D','E','F','G','H','J']

sns.barplot(
    data = diamonds_df,
    x = "cut",
    y = "price",
    hue_order = color_list,
    hue = "color"

)

plt.title("Mean Diamod Price by Cut and Color")
plt.ylabel("Mean Price (USD)")
plt.xlabel("Diamond Cut")
plt.legend(loc="upper right", bbox_to_anchor=(1.15,1.0))
sns.despine()
plt.tight_layout()
plt.show()

plt.figure() #graph 1

g1 = sns.countplot(
    data = diamonds_df,
    x = "cut"
)
ax1 = g1

for container in ax1.containers:
    ax1.bar_label(container, fmt = ("%.0f"), fontsize = 8)

ymin, ymax = ax1.get_ylim()
needed_top = ideal_count + 5000
if ymax < needed_top:
    ax1.set_ylim(ymin, needed_top)

ax1.annotate(
    "Most Common Cut",
    xy = (x_pos, ideal_count),
    xytext = (8,25),
    textcoords = "offset points",
    ha = "left", va = "bottom",
    fontsize = 10,
    bbox = dict(
        boxstyle = "round, pad = 0.35",
        facecolor = "white",
        edgecolor = "0.7",
        linewidth = 1
    ),
    arrowprops = dict(
        arrowstyle = "->",
        color = "crimson",
        linewidth = 2,
        shrinkA = 0, shrinkB = 0,
        connectionstyle = "arc3, rad = 0"
    ),
    zorder = 10,
    clip_on = False
)
plt.title("Diamonds Count by Cut")
plt.xlabel("Diamond Cut")
plt.ylabel("Count")
plt.tight_layout()

#Practice 2
URL = "https://raw.githubusercontent.com/TrainingByPackt/Interactive-Data-Visualization-with-Python/master/datasets/athlete_events.csv"
olympics_df = pd.read_csv(URL)

olympics_winners = olympics_df.dropna(subset=['Medal']).copy()

YEAR = 2016
winners_2016 = olympics_winners.loc[olympics_winners["Year"] == YEAR].copy()

top5_2016 = winners_2016["Sport"].value_counts().head(5)
print("[Counts] Medals by sport in 2016 (top 5):")
print(top5_2016, "\n")

top5_order = top5_2016.index
plt.figure() # second graph
g2 = sns.countplot(
    data = winners_2016,
    order = top5_order,
    x = "Sport",
    legend = False
)

for container in g2.containers:
    g2.bar_label(container, fmt = ("%.0f"), fontsize = 8, padding = 2)

plt.title("Top 5 Sports by Medal Count - 2016")
plt.xlabel("Sport")
plt.ylabel("Medal Count")
plt.tight_layout()
plt.show()
