import pandas as pd
import _sqlite3 as sql
import numpy as np

students = pd.DataFrame({
    'name': ['Alice', 'Bob', 'Carol', 'David', 'Emma'],
    'major': ['IT', 'CS', 'IT','CS', 'IT'],
    'score': [92, 75, 85, 68, 90]
})

connection = sql.connect(
    'students.db'
)
students.to_sql(
    'students',
    connection,
    if_exists = 'replace',
    index = 'False'
)

high_score = pd.read_sql_query('SELECT * FROM students WHERE score >= 80 ORDER BY score DESC', connection)
print(f'Problem 1:\n{high_score}')
connection.close()

students = pd.DataFrame({
    'name': ['Alice', 'Bob', 'Carol', 'David', 'Emma'],
    'score': [90, np.nan, 80, 100, np.nan],
    'age': [20, 21, np.nan, 22, 20]
})
num_missing = students.isna().sum()
average = students['score'].mean()

filled_students = students.fillna({
    'score': average,
    'age': 20
})

print('\nProblem 2')
print(f'Original DataFrame:\n{students}')
print(f'\nMissing values in each column:\n{num_missing}')
print(f'\nAverage score:\n{average}')
print(f'\n Filled DataFrame:\n{filled_students}')
