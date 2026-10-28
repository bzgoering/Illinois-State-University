import pandas as pd
import numpy as np
from bokeh.plotting import figure, show, output_file
from bokeh.models import DatetimeTickFormatter, DaysTicker

data = {
    "Date": [
        "2026-10-19", "2026-10-20",
        "2026-10-21", "2026-10-22",
        "2026-10-23", "2026-10-24"
    ],
    "Start": [
        "09:00", "10:30", "13:00",
        "11:15", "14:00", "09:30"
    ],
    "End": [
        "10:30", "12:00", "15:30",
        "12:45", "16:15", "11:00"
    ]
}
df = pd.DataFrame(data)
df['Date'] = pd.to_datetime(df["Date"], format = 'mixed')

dt = df.copy()
start = pd.to_timedelta(dt['Start'] + ':00')
end = pd.to_timedelta(dt['End'] + ':00')
dt['Duration'] = ((end - start).dt.total_seconds() // 60).astype(int)

longest = dt.loc[dt["Duration"].idxmax()]


output_file('study_session.html')
p = figure(
    title = 'Study Session Duration Over Time',
    x_axis_label = 'Date',
    y_axis_label = 'Duration (minutes)',
    width = 800,
    height = 400
)
p.line(dt['Date'], dt['Duration'], line_width = 2)
p.scatter(dt['Date'], dt['Duration'], size = 8)
p.xaxis.formatter = DatetimeTickFormatter(days="%b %d", months="%b %d", hours="%b %d")
p.xaxis.ticker = DaysTicker(days=list(range(1, 32)))
show(p)

print(df.head())
print('---------------------------------')
print('Study Sessions:')
print(dt.head(), '\n\nLongest Study Session:')
print('Date:', longest['Date'].date())
print('Duration:', longest['Duration'], 'minutes')
