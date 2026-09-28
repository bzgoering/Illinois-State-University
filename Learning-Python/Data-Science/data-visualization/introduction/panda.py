import pandas as pd

frame = pd.DataFrame({
    'state': ['Ohio', 'Ohio', 'Nevada', 'Nevada'],
    'population': [1.5,1.8,2.4,2.9],
    'debt': [1.0,2.0,3.0,4.0]
})

print(f'Original DataFrame:\n{frame}')
print(f'\nSelected Data:\n{frame.loc[frame['population'] > 2.0, ['state', 'population']]}')

frame['debt_per_population'] = frame['debt'] / frame['population']
sorted_frame = frame.sort_values(
    by = 'debt_per_population',
    ascending = False
)

print(f'\nSorted DataFrame:\n{sorted_frame}')
