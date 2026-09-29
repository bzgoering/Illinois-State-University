import pandas as pd
import numpy as np
from bokeh.plotting import figure, show
from bokeh.models import ColumnDataSource, HoverTool

URL_CO2 = ("https://raw.githubusercontent.com/TrainingByPackt/Interactive-Data-Visualization-with-Python/master/datasets/co2.csv")
URL_GM = ("https://raw.githubusercontent.com/TrainingByPackt/Interactive-Data-Visualization-with-Python/master/datasets/gapminder.csv")
co2 = pd.read_csv(URL_CO2, keep_default_na=True)
gm = pd.read_csv(URL_GM, keep_default_na=True)

regions = gm[["Country", "region"]].dropna().drop_duplicates()
merged = (
    co2.merge(
        regions,
        left_on="country",
        right_on="Country",
        how="inner"
    )
    .drop(columns=["Country"])
)
year_cols = [c for c in merged.columns if str(c).isdigit() and len(str(c)) == 4]
new_co2 = merged.melt(
    id_vars=["country", "region"],
    value_vars=year_cols,
    var_name="year",
    value_name="co2"
)

# Convert year and CO2 values to numeric
new_co2["year"] = pd.to_numeric(new_co2["year"], errors="coerce")
new_co2["co2"] = pd.to_numeric(new_co2["co2"], errors="coerce")
df_gdp = gm[["Country", "Year", "gdp"]].rename(columns={"Country": "country", "Year": "year"}).copy()
df_gdp["year"] = pd.to_numeric(df_gdp["year"], errors="coerce")
df_gdp["gdp"] = pd.to_numeric(df_gdp["gdp"], errors="coerce")
df_gdp = (df_gdp.dropna(subset=["year", "gdp"]).drop_duplicates(["country", "year"]))
new_co2 = new_co2.dropna(subset=["year"])
new_co2["year"] = new_co2["year"].astype("int64")
df_gdp["year"] = df_gdp["year"].astype("int64")

data = new_co2.merge(
    df_gdp,
    on=["country", "year"],
    how="inner"
)

selected_year = 1964
# 1. Keep only rows for the selected year
year_df = data[data["year"] == selected_year]
# 2. Remove rows where GDP or CO2 is missing
year_df = year_df.dropna(subset=['gdp', 'co2'])

print("Data loaded successfully.")
print("Columns:", data.columns.tolist())
print()
print(year_df.head())

#Practice 2
year_df = (data.loc[data['year'] == selected_year, ['country','region', 'gdp', 'co2']]).dropna(subset = ['gdp','co2'])
year_df = year_df[year_df['co2'] > 0].copy()

source = ColumnDataSource(data = {
    'x': year_df['gdp'].tolist(),
    'y': year_df['co2'].tolist(),
    'country': year_df['country'].tolist(),
    'region': year_df['region'].tolist()
})
plot = figure(
    title="CO2 Emissions vs GDP in 1964",
    width=800,
    height=500,
    x_axis_label="GDP per person",
    y_axis_label="CO2 emissions per person",
    y_axis_type="log",
    tools="pan,wheel_zoom,box_zoom,reset,save,hover"
)
plot.scatter(
    x = 'x',
    y = 'y',
    source = source,
    size = 8,
    fill_alpha = 0.7
)

hover = plot.select_one(HoverTool)
hover.tooltips = [
    ('Country', "@country"),
    ('Region', "@region"),
    ('GDP', "@x{0,0}"),
    ('CO2', "@y{0.00}")
]

print("Data loaded successfully.")
print("Columns:", data.columns.tolist())
print()
show(plot)

#Optional
#log grows on the y levels fast, similar to a logarithmic graph
#linear graph slips the graph, as it grows less on the y axis and more on the x axis
