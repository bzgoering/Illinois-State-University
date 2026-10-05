import pandas as pd

students = pd.read_csv('students.csv')
high_students = students[students['score'] > 79]
high_students.to_csv('high_scores.csv', index = False)

print('First 3 rows:\n', students.head(3),sep = '')
print('\nShape:\n', students.shape, sep = '')
print('\nStudents with scores >= 80:\n', high_students, sep = '')
print('high_scores.cs has been saved')

#problem 2
students = pd.read_csv('cities.csv', na_values = ['Unknown'])
num_missing = students.isna().sum()
name_score = students[['name', 'score']]

print("\nProblem 2")
print('Data:\n', students, sep = '')
print('\nMissing values in each column:\n', num_missing, sep = '')
print('\nName and score columns:\n', name_score, sep = '')
