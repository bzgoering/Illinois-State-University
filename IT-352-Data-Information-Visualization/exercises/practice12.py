import pandas as pd
import altair as alt
import webbrowser
from pathlib import Path
import plotly.express as px

hpi_url = "https://raw.githubusercontent.com/TrainingByPackt/Interactive-Data-Visualization-with-Python/master/datasets/hpi_data_countries.tsv"
hpi_df = pd.read_csv(hpi_url, sep = '\t')

regions = sorted(hpi_df['Region'].dropna().unique().tolist())
region_options = ['All'] + regions
region_pick = alt.param(
    name = 'Select',
    value = 'All',
    bind = alt.binding_select(
        options = region_options,
        name = 'Region: '
    )
)

color = alt.condition(
    "(Select == 'All') || (datum.Region == Select)",
    alt.Color('Region:N', title = 'Region'),
    alt.value('lightgray')
)

chart = (
    alt.Chart(hpi_df)
    .mark_circle(size = 100, opacity = 0.85)
    .encode(
        x = alt.X("Life Expectancy (years):Q"),
        y = alt.Y('Happy Planet Index:Q'),
        color = color,
        tooltip = [
            'Country:N',
            'Region:N',
            alt.Tooltip('Wellbeing (0-10)', title = 'Wellbeing (0-10)'),
            alt.Tooltip('Life Expectancy (years):Q', title = 'Life Expectancy (years)'),
            alt.Tooltip('Happy Planet Index:Q', title = 'HPI'),
        ],
    )
    .add_params(region_pick)
    .properties(width = 600, height = 420, title = 'HPI - Value Based Selection by Region')
)

chart.save('ex01.html')

co2 = pd.read_csv('co2.csv')
gm = pd.read_csv('gapminder.csv')
gm = gm.rename(columns = {
    'gdpPercap': 'gdp',
    'Year': 'year',
    'continent': 'region',
    'Country': 'country'
})
df_gm = gm[['country', 'region']].dropna().drop_duplicates()

df_w_regions = pd.merge(
    co2, df_gm, on = 'country', how = 'inner'
)
year_col = [
    c for c in df_w_regions.columns if c.isdigit() and len(c) == 4
]

new_co2 = pd.melt(
    df_w_regions,
    id_vars = ['country', 'region'],
    value_vars = year_col,
    var_name = 'year',
    value_name = 'co2'
)
new_co2['year'] = pd.to_numeric(new_co2['year'], errors = 'coerce')
new_co2['co2'] = pd.to_numeric(new_co2['co2'], errors = 'coerce')

df_co2 = (
    new_co2.dropna(subset = ['year', 'co2'])
        .query('year >= 1990 & year <= 2010')
        .astype({'year': 'int64'})
        .sort_values(by = ['country', 'year'])
)

df_gdp = gm[['country', 'year', 'gdp']].copy()
df_gdp['year'] = pd.to_numeric(df_gdp['year'], errors = 'coerce')
df_gdp['gdp'] = pd.to_numeric(df_gdp['gdp'], errors = 'coerce')
df_gdp = (
    df_gdp.dropna(subset = ['year', 'gdp'])
        .drop_duplicates(['country', 'year'])
        .astype({'year': 'int64', 'gdp': 'float64'})
)

data = pd.merge(df_co2, df_gdp, on = ['country', 'year'], how = 'inner')
data = data.dropna(subset = ['gdp', 'co2'])
data = data[(data['gdp'] > 0) & (data['co2'] >= 0)]

if data.empty:
    raise ValueError("No data left afer cleaning (check inputs)")

xmin = max(1e-6, data['gdp'].min() * 0.95)
xmax = data['gdp'].max() * 1.05
ymin = max(0.0, data['co2'].min() * 0.95)
ymax = data['co2'].max() * 1.05

title_text = f'CO₂ Emiisions vs GDP (1990 - 2010)'

fig = px.scatter(
    data,
    x = 'gdp', y = 'co2',
    animation_frame = 'year',
    animation_group = 'country',
    color = 'region',
    hover_name = 'country',
    width = 1200, height = 600,
    log_x = True,
    size_max = 18,
    labels = {
        'gdp': 'GDP per capital',
        'co2': 'CO₂ per person',
        'region': 'Region',
        'country': 'Country',
        'year': 'Year'
    },
    title = title_text
)

fig.update_layout(legend_title_text = 'Region')

fig.write_html('ex02.html', include_plotlyjs = 'inline', full_html = 'True')
data.sort_values(['year', 'country','region'], kind = 'stable').to_csv(
    'ex02_clean.html', index = False
)
