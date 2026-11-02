import pandas as pd

fruits = pd.Series([
    "apple", "banana", "apple",
    "orange", "banana", "apple"
])

# 1. Display the original Series
print("Original Series:")
print(fruits)

# 2. Count each fruit
print("\nValue counts:")
print(fruits.value_counts())

# 3. Convert to categorical
fruits_cat = fruits.astype("category")

# 4. Display the categories
print("\nCategories:")
print(fruits_cat.cat.categories)

# 5. Display the integer codes
print("\nInteger codes:")
print(fruits_cat.cat.codes)

# 6. Create dummy variables
dummies = pd.get_dummies(fruits, dtype=int)

# 7. Display the resulting DataFrame
print("\nDummy variables:")
print(dummies)

students = pd.DataFrame({
    "student_id": [1, 2, 3, 4],
    "name": ["Alice", "Bob", "Charlie", "Diana"]
})

scores = pd.DataFrame({
    "student_id": [2, 3, 4, 5],
    "score": [85, 92, 78, 88]
})

# 1. Display the original DataFrames
print("\nStudents:")
print(students)
print("\nScores:")
print(scores)

# 2. Inner join
inner = pd.merge(students, scores, on="student_id", how="inner")

# 3. Display the inner join result
print(f"\nInner join:\n{inner}")

# 4. Left join
left = pd.merge(students, scores, on="student_id", how="left")

# 5. Display the left join result
print("\nLeft join:")
print(left)

# 6. Number of rows in each merged DataFrame
print("\nRows in inner join:", len(inner))
print("Rows in left join:", len(left))
