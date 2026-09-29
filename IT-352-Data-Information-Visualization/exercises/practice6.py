import pandas as pd

co2_wide = pd.DataFrame({
    'country': ['Canada', 'Mexico', 'Japan'],
    'region' : [
        'North America',
        'Latin America',
        'East Asia'
    ],
    '2020': [14.2, 3.7, 8.4],
    '2021': [14.5, 'missing', 8.1]
})

co2_long = pd.melt(
    co2_wide,
    id_vars = ['country', 'region'],
    var_name = 'year',
    value_name = 'co2'
)
co2_long['year'] = pd.to_numeric(co2_long['year'], errors = 'coerce')
co2_long['co2'] = pd.to_numeric(co2_long['co2'], errors = 'coerce')
co2_long = co2_long.dropna(subset = ['co2'])
co2_long['year'] = co2_long['year'].astype('int64')
co2_long_clean = co2_long.sort_values(by = ['country', 'year']).reset_index(drop = True)

print("Original wide-format data:\n", co2_wide)
print('\nLong-format data:\n',co2_long)
print('\nCleaned CO₂ data:\n', co2_long_clean)
