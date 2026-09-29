import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
import math
dataset_url = "https://raw.githubusercontent.com/TrainingByPackt/Interactive-Data-Visualization-with-Python/master/datasets/athlete_events.csv"
olympics_df = pd.read_csv(dataset_url)
selected_sports = ['Athletics', 'Swimming', 'Rowing', 'Football', 'Hockey']
olympics_2016 = olympics_df[(olympics_df['Year'] == 2016) &
                            (olympics_df['Sport'].isin(selected_sports)) &
                            (olympics_df['Medal'].notna())].copy()
olympics_clean = olympics_2016.dropna(subset=['Age']).copy()

plt.figure()
sns.violinplot(
    data = olympics_clean,
    x = 'Medal',
    y = 'Age',
    hue = 'Sex',
    palette = 'Set2',
    inner = 'quart',
    order = ['Bronze', "Silver", 'Gold']
)
plt.title("Age Distribution by Medal Type and Gender")
plt.xlabel("Medal Type")
plt.ylabel("Age")
plt.tight_layout()

olympics_clean = olympics_2016.dropna(subset=['Height', 'Weight', 'Age']).copy()
g = sns.pairplot(
    data = olympics_clean,
    vars = ['Height', 'Weight', 'Age'],
    hue = 'Medal',
    palette = 'Set2',
    corner = True,
    diag_kind = 'hist',
    plot_kws = {'alpha': 0.7, 's': 40, 'edgecolor': 'none'},
    diag_kws = {'bins': 15, 'alpha': 0.7, 'linewidth': 0.5}
)
g.figure.suptitle("Pairwise Relationships: Height, Weight, and Age\n2016 Medal Winners", y = 1)
g.figure.subplots_adjust(top=0.90)
g.figure.tight_layout()
plt.show()
