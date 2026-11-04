import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

df = pd.DataFrame({
    "Day": ["Mon", "Mon", "Tue", "Tue",
    "Wed", "Wed", "Thu", "Thu"],
    "Sales": [100, 150, 180, 120,
    200, 160, 140, 190]
})

df = df.groupby('Day')
total_sales = df['Sales'].sum()
total_sales_df = df[['Sales']].sum()
average_sales = df['Sales'].mean()

ax = sns.barplot(
    data = total_sales_df,
    x = 'Day',
    y = 'Sales',
    order = ['Mon', 'Tue', 'Wed', 'Thu'],
    legend = False
)
ax.set_title('Total Sales by Day')
ax.set_xlabel("Day")
ax.set_ylabel("Total Sales")

print(f'Original Data:\n{df.head()}')
print(f'\nTotal Sales::\n{total_sales}')
print(f'\nAverage Sales::\n{average_sales}')
plt.show()
