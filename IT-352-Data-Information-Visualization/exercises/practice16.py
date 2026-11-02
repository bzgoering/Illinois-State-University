import pandas as pd
import altair as alt

def to_datetime_mixed(data):
    try:
        return pd.to_datetime(data, format = 'mixed', errors = 'coerce')
    except TypeError:
        return pd.to_datetime(data, errors = 'coerce')

df = pd.DataFrame({
    "Date": [
    "2026-01-01", "2026-01-02",
    "2026-01-03", "2026-01-05",
    "2026-01-06", "2026-01-08",
    "2026-01-09", "2026-01-10"
    ],
    "Sales": [120, 140, 160, 110,
    180, 200, 150, 170]
})

df['Date'] = to_datetime_mixed(df['Date'])
df = df.set_index('Date').sort_index()
weekly_sales = df.resample('W-SUN').sum()

print(f'Original DataFrame:{df}')
print(f'\nSales from January 2 to January 6:\n{df.loc['2026-01-02':'2026-01-06'].head()}')
print(f'\nWeekly Total Sales:\n{weekly_sales}')

#problem 2
df = pd.DataFrame({
    "DateTime": [
    "2026-10-01 09:00",
    "2026-10-01 12:00",
    "2026-10-01 18:00",
    "2026-10-01 21:00",
    "2026-10-02 09:00",
    "2026-10-02 12:00",
    "2026-10-02 18:00",
    "2026-10-02 21:00"
    ],
    "Orders": [20, 50, 75, 40,
    30, 45, 85, 60]
})

df['DateTime'] = to_datetime_mixed(df['DateTime'])
df['hour'] = df['DateTime'].dt.hour

plot = (
    alt.Chart(df)
    .mark_bar()
    .encode(
        x = alt.X('hour:O'),
        y = alt.Y('mean(Orders):Q'),
        tooltip = ['hour', 'mean(Orders)']
    )
    .properties(title = 'Average Online Orders by Hour', width = 800, height = 400)
)

plot.save('Orders_by_Hour.html')
