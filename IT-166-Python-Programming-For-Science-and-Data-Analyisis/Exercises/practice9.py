import pandas as pd

students = pd.DataFrame({
    'name': ['Alice', 'Bob', 'Carol', 'David'],
    'score': [85,92,78,95]
})
mean_score = students['score'].mean()
min_score = students['score'].min()
max_score = students['score'].max()
info_score = students['score'].describe()

print(f'Student Data:\n{students}')
print(f'\nMean Score:\n{mean_score}')
print(f'\nMinimum Score:\n{min_score}')
print(f'\nMaximum Score:\n{max_score}')
print(f'\nSummary Statistics:\n{info_score}')

students = pd.DataFrame({
    'name': ['Alice', 'Bob', 'Carol', 'David', 'Emma', 'Frank'],
    'grade': ['A','C','B','A','B','C']
})
count = students['grade'].value_counts()
high_grade = students[students['grade'].isin(['A','B'])]

print('\nProblem 2')
print(f'Student Data:\n{students}')
print(f'\nGrade Counts:\n{count}')
print(f'\nStudents with Grade A or B:\n{high_grade}')

