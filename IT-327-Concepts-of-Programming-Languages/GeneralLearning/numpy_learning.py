import numpy as np

print('Problem 1')
scores = np.array([72,85,90,68,88])
print("Original scores:", scores)

updated_scores = scores + 5
print("Updated scores:", updated_scores)
print("Mean:", updated_scores.mean())
print("Maximum:", updated_scores.max())
print("Minimum:", updated_scores.min())
print("Scores at least 85:", updated_scores[updated_scores > 84])
print("\nproblem 2")
data = np.array([[1,2,3,4], [5,6,7,8], [9,10,11,12]])
print("Number of dimension:", data.ndim)
print('Shape:', data.shape)
print('second row:', data[1])
print('Element at row 2, column 3:', data[2,3])
print('First 2 rows and last 2 columns:', data[:2,-2:], sep = "\n")
data[data < 6] = 0
print('Modified array:', data, sep = "\n")
print('transpose of the modified array:', data.T, sep = "\n")

rng = np.random.default_rng(seed = 42)
temperatures = rng.integers(50, 100, size = (10))
mean_temp = temperatures.mean()
max_temp = temperatures.max()
min_temp = temperatures.min()
number_high = temperatures[temperatures > 79]
classified = np.where(temperatures >= 80, 'Hot', "Normal")

print("Problem 1")
print("Temperatures:", temperatures)
print("Mean temperature:", mean_temp)
print("Minimum temperature:", min_temp)
print("Maximum temperature:", max_temp)
print("Numbr of hot temperature:", number_high)
print("Classification:", classified)

scores = np.array([
[85, 78, 92],
[68, 74, 70],
[90, 88, 95],
[55, 65, 60]
])
avg_score = np.round(np.mean(scores, axis = 1), 2)
test_avg = np.round(np.mean(scores, axis = 0), 2)
results = np.where(avg_score >= 70, 'Pass', 'Fail')
high_score = scores.max()
low_score = scores.min()
sorted_avg = np.sort(avg_score)
print("\nProblem 2")
print("Scores:\n", scores)
print("\nStudent average:", avg_score)
print("Test average:", test_avg)
print("Results:", results)
print("Highest score:", high_score)
print("Lowest score:", low_score)
print("Sorted averages:", sorted_avg)
