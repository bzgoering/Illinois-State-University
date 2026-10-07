import pandas as pd
import requests

students = pd.read_excel('students.xlsx', sheet_name = 'Students')
high_score = students[students['score'] > 79]
high_score.to_excel('high_scores.xlsx', index = False)

print(f'All Students:\n{students}')
print(f'\nStudents with score >= 80:\n{high_score}')
print('\nData saved to high_scores.xlsx')



url = ('https://api.github.com/repos/pandas-dev/pandas/issues')
response = requests.get(
    url,
    params = {'state': 'open', 'per_page': 5},
    timeout = 10
)

response.raise_for_status()
data = response.json()

issues = pd.DataFrame(
    data,
    columns = [
        'number',
        'title',
        'state'
    ]
)

print("\nProblem 2")
print(f'Status code: {response.status_code}')
print(f'\nOpen GitHub Issues:\n{issues}')
