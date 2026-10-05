import pandas as pd
import plotly.express as px

url_co2 = ("https://raw.githubusercontent.com/TrainingByPackt/Interactive-Data-Visualization-with-Python/master/datasets/co2.csv")
url_gm = ("https://raw.githubusercontent.com/TrainingByPackt/Interactive-Data-Visualization-with-Python/master/datasets/gapminder.csv")
co2 = pd.read_csv(url_co2)
gm = pd.read_csv(url_gm)

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
new_co2['year'] = new_co2['year'].astype('int64')

df_co2 = new_co2[new_co2['year'] >= 1964]
df_co2 = df_co2.sort_values(by = ['country', 'year'])
df_gdp = gm[['Country', 'Year', 'gdp']].copy()
df_gdp.columns = ['country', 'year', 'gdp']

data = pd.merge(
    df_co2,
    df_gdp,
    on = ['country','year'],
    how = 'inner'
)
data['co2'] = pd.to_numeric(
    data['co2'],
    errors = 'coerce'
)
data['gdp'] = pd.to_numeric(
    data['gdp'],
    errors = 'coerce'
)
data = data.dropna(subset = ['co2', 'gdp'])

fig = px.scatter(
    data,
    x = 'year',
    y = 'co2',
    color = 'region',
    marginal_y = 'box',
    hover_name = 'country',
    hover_data = {
        'region': True,
        'year': True,
        'co2': ':.2f'
    }
)
fig.write_html("exercise1_co2_year.html")
fig.show()

data2 = pd.merge(
    df_co2,
    df_gdp,
    on = ['country','year'],
    how = 'left'
)
data2['co2'] = pd.to_numeric(
    data['co2'],
    errors = 'coerce'
)
data2['gdp'] = pd.to_numeric(
    data['gdp'],
    errors = 'coerce'
)
data2 = data.dropna(subset = ['co2', 'gdp'])

#fix marginal graphs not updated with animation (semi fix, part 1/3):
x_pad = (data2['gdp'].max() - data2['gdp'].min()) * 0.05
y_pad = (data2['co2'].max() - data2['co2'].min()) * 0.05

fig = px.scatter(
    data2,
    x = 'gdp',
    y = 'co2',
    color = 'region',
    marginal_x = 'rug',
    marginal_y = 'box',
    hover_name = 'country',
    animation_frame = 'year',
    animation_group = 'country',
    
    #fix marginal graphs not updated with animation (semi fix, part 2/3):
    range_x = [data2['gdp'].min() - x_pad, data2['gdp'].max() + x_pad],
    range_y = [data2['co2'].min() - y_pad, data2['co2'].max() + y_pad]
)

#fix marginal graphs not updated with animation (semi fix, part 3/3):
fig.layout.updatemenus[0].buttons[0].args[1]['frame']['redraw'] = True
for step in fig.layout.sliders[0].steps:
    step.args[1]['frame']['redraw'] = True

fig.write_html(" exercise2_gdp_co2_animated.html")
fig.show()
