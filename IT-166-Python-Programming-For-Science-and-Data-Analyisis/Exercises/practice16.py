import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

sales = pd.DataFrame({
    "Coffee": [60, 70, 90, 80],
    "Tea": [45, 55, 50, 60],
    "Juice": [30, 35, 45, 40]
}, index=["Week 1", "Week 2", "Week 3", "Week 4"])

fig1 = plt.figure()
sales.plot.bar(stacked = True, rot = 0, ax = plt.gca())

plt.title("Weekly Campus Cafe Sales")
plt.xlabel('Week')
plt.ylabel("Number of Drinks Sold")

print(f'Weekly Cafe Sales:\n{sales}')

#Question 2
data = pd.DataFrame({
    "day": [1, 2, 3, 4, 5, 1, 2, 3, 4, 5],
    "height": [3, 4, 6, 7, 9, 2, 3, 4, 5, 6],
    "condition": ["Sun", "Sun", "Sun", "Sun", "Sun","Shade", "Shade", "Shade", "Shade", "Shade"]
})

fig2 = plt.figure()
sns.scatterplot(
    data = data,
    x = 'day',
    y = 'height',
    hue = 'condition'
)
plt.title('Plant Growth Under Different Conditions')
plt.xlabel('Day')
plt.ylabel('Plant Height (cm)')
plt.legend(title = 'Condition')

fig3 = plt.figure()
sns.boxplot(
    data = data,
    x = 'condition',
    y = 'height',        
)
plt.title('Plant Growth Under Different Conditions')
plt.xlabel('Condition')
plt.ylabel('Plant Height (cm)')

print(f'\nPlant Growth Data:\n{data}')
plt.show()
