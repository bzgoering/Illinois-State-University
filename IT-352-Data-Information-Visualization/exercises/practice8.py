import pandas as pd
from bokeh.plotting import figure
from bokeh.models import ColumnDataSource, Slider, CustomJS
from bokeh.layouts import column
from bokeh.io import output_file, save

data = pd.DataFrame({
    "country": ["USA", "China", "India","USA", "China", "India"],
    "region": ["America", "Asia", "Asia","America", "Asia", "Asia"],
    "year": [1990, 1990, 1990,2000, 2000, 2000],
    "gdp": [24000, 1000, 600,36000, 2200, 900],
    "co2": [19.0, 2.1, 0.8,20.5, 2.7, 1.0]
})

selected_year = 2000
year_df = data['year'] == selected_year
selected_data = data.loc[year_df, ['country', 'gdp', 'co2']]
print(selected_data)

data = pd.DataFrame({
    "country": [
        # 1990
        "USA", "China", "India", "Germany", "Brazil", "Japan",
        # 1995
        "USA", "China", "India", "Germany", "Brazil", "Japan",
        # 2000
        "USA", "China", "India", "Germany", "Brazil", "Japan",
        # 2005
        "USA", "China", "India", "Germany", "Brazil", "Japan",
        # 2010
        "USA", "China", "India", "Germany", "Brazil", "Japan",
        # 2015
        "USA", "China", "India", "Germany", "Brazil", "Japan"
    ],
    "year": [
        1990, 1990, 1990, 1990, 1990, 1990,
        1995, 1995, 1995, 1995, 1995, 1995,
        2000, 2000, 2000, 2000, 2000, 2000,
        2005, 2005, 2005, 2005, 2005, 2005,
        2010, 2010, 2010, 2010, 2010, 2010,
        2015, 2015, 2015, 2015, 2015, 2015
    ],
    "gdp": [
        # 1990
        24000, 1000, 600, 22000, 3000, 25000,
        # 1995
        28000, 1400, 700, 26000, 4200, 29000,
        # 2000
        36000, 2200, 900, 30000, 4800, 34000,
        # 2005
        44000, 3500, 1200, 35000, 6000, 39000,
        # 2010
        49000, 6000, 1600, 41000, 8500, 44000,
        # 2015
        57000, 8000, 2100, 46000, 9000, 39000
    ],
    "co2": [
        # 1990
        19.0, 2.1, 0.8, 12.0, 1.4, 9.5,
        # 1995
        19.5, 2.5, 0.9, 11.0, 1.6, 9.8,
        # 2000
        20.5, 2.7, 1.0, 10.5, 1.8, 9.7,
        # 2005
        19.5, 4.2, 1.2, 10.0, 1.9, 9.5,
        # 2010
        17.5, 6.5, 1.5, 9.2, 2.1, 9.0,
        # 2015
        15.5, 7.5, 1.8, 8.8, 2.2, 8.5
    ]
})

all_source = ColumnDataSource(data = {
    'year': data['year'].tolist(),
    'gdp': data['gdp'].tolist(),
    'co2': data['co2'].tolist()
})
year_df = data[data['year'] == 1990]
source = ColumnDataSource(data = {
    'x': year_df['gdp'].tolist(),
    'y': year_df['co2'].tolist()
})

plot = figure(
    title = 'CO2 vs GDP in 1990',
    width = 700,
    height = 400,
    x_axis_label = 'GDP per capita',
    y_axis_label = 'CO2 per person'
)
plot.scatter(
    x = 'x',
    y = 'y',
    source = source,
    size = 10
)

slider = Slider(
    start = 1990,
    end = 2015,
    step = 5,
    value = 1990,
    title = 'Year'
)

callback = CustomJS(
    args = dict(
        source = source,
        all_source = all_source,
        slider = slider,
        plot = plot
    ),
    code = """
    const yr = slider.value;
    const A = all_source.data;
    const x = [];
    const y = [];

    for(let i = 0; i < A.year.length; i++)
    {
        if(A.year[i] == yr)
        {
            x.push(all_source.data["gdp"][i]);
            y.push(all_source.data["co2"][i]);
        }
    }

    source.data = {x,y}
    source.change.emit();

    plot.title.text = 'CO₂ Emissions vs GDP in ' + yr;
    """)

slider.js_on_change('value', callback)
layout = column(slider, plot)
output_file('excercise2.html')
save(layout)
print("Interactive visualization saved as excercise2.html")
