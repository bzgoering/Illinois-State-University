import pandas as pd
import plotly.express as px

co2 = pd.read_csv('co2.csv')
gm = pd.read_csv('gapminder.csv')
df_gm = gm[['Country', 'region']].drop_duplicates()

df_w_regions = pd.merge(
    co2,
    df_gm,
    left_on = 'country',
    right_on = 'Country',
    how = 'inner'
)
df_w_regions = df_w_regions.drop('Country', axis = 'columns')

new_co2 = pd.melt(
    df_w_regions,
    id_vars = ['country', 'region'],
    var_name = 'year',
    value_name = 'co2'
)

new_co2['year'] = pd.to_numeric(
    new_co2['year'],
    errors = 'coerce'
)

df_co2 = new_co2[new_co2['year'] >= 1964].copy()
df_co2 = df_co2.sort_values(
    by = ['country', 'year']
)
print(df_co2.head(10))

new_co2['co2'] = pd.to_numeric(
    new_co2['co2'],
    errors = 'coerce'
)

df_co2 = new_co2.dropna(subset = ['year', 'co2']).copy()
df_co2 = df_co2[df_co2['year'] >= 1964]
df_co2 ['year'] = df_co2['year'].astype('int64')

df_gdp = gm[['Country', 'Year', 'gdp']].copy()
df_gdp.columns = [
    'country',
    'year',
    'gdp'
]

data = pd.merge(
    df_co2,
    df_gdp,
    on = ['country', 'year'],
    how = 'left'
)

data['gdp'] = pd.to_numeric(
    data['gdp'],
    errors = 'coerce'
)
data = data.dropna(subset = ['co2', 'gdp', 'region'])
data = data[data['gdp'] > 0]

fig = px.scatter(
    data,
    x = 'gdp',
    y = 'co2',
    color = 'region',
    hover_name = 'country',
    animation_frame = 'year',
    animation_group = 'country',
    log_x = True
)

fig.write_html(
    "exercise2_plot.html",
    include_plotlyjs=True
)

fig.show()
