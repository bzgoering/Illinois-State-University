import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

a = np.array([
    [1,2],
    [3,5]
])

b = np.array([
    [1,2],
    [3,4]
])

np.save('matrix_a.npy', a)
loaded_a = np.load('matrix_a.npy')
element_multi = loaded_a * b
matrix_multi = loaded_a @ b

print('problem 1')
print('Loaded array:\n', loaded_a)
print('\nElement-wise multiplication:\n', element_multi)
print('\nMatrix multiplication:\n', matrix_multi)
print('\nShape of matrix multiplication result:\n', matrix_multi.shape)

rng = np.random.default_rng()
numbers = rng.integers(0, 2, size = (20))
steps = np.where(numbers == 0, -1, 1)
position = steps.cumsum()
low_pos = position.min()
high_pos = position.max()

print("\nProblem 2")
print('Random values:\n', numbers)
print('\nSteps:', steps)
print('\nposition:', position)
print('\nLowest position:', low_pos)
print('\nHighest position:', high_pos)

plt.plot(position, marker = 'o')
plt.xlabel('Step')
plt.ylabel('Position')
plt.title('Random Walk')
plt.show()

scores = pd.Series(
    [82,91,76,88,95],
    index = ['Alice', 'Bob', 'Carol', 'David', 'Emma']
)
high_score = scores[scores >= 85]
adjusted_scores = scores+3

print(f'Problem 1\nComplete Series:\n{scores}' )
print(f"\nBob's and David's scores:\n{scores.loc[['Bob','David']]}")
print(f"\nThird score:\n{scores.iloc[2]}")
print(f'\nScores greater than or equal to 85:\n{high_score}')
print(f'\nAdjusted scores:\n{adjusted_scores}')
print("\nProblem 2")

data = {
    'student': ['Alice', 'Bob', 'Carol', 'David'],
    'exam1': [82,91,76,88],
    'exam2': [86,89,80,94]
}
grades = pd.DataFrame(
    data,
    index = ['S1', 'S2', 'S3', 'S4']
)
updated_grades = grades.copy()
updated_grades.loc['S3','exam2'] = 84
updated_grades['average'] = (updated_grades['exam1'] + updated_grades['exam2'])/2
updated_grades['passed'] = updated_grades['average'] > 84

print(f'Complete DataFrame:\n{grades}')
print(f'\nNumber of rows and columns:\n{grades.shape}')
print(f'\nStudent and exam2 columns:\n{grades[['student','exam2']]}')
print(f"\nCompleted DataFrame:\n{updated_grades}")
print(f'\nStudents with an average of at least 85:\n{updated_grades[updated_grades['passed'] == True]}')
