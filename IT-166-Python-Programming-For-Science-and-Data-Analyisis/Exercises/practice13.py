import pandas as pd
import numpy as np

students = pd.DataFrame({
    'student_name': ['Alice', 'Bob', 'Carol', 'Alice', 'David', 'Emma'],
    'major_code':['IT','CS', 'IT', 'IT', 'CS', 'IT'],
    'score': [90,85,-999,90,78,92]
})
major_names = {
    'IT': 'Information Technology',
    'CS': 'Computer Science'
}

clean_students = students.drop_duplicates()
clean_students = clean_students.replace(-999, np.nan)
clean_students = clean_students.rename(columns = {'student_name': 'name'})
clean_students['major'] = clean_students['major_code'].map(major_names)

print(f'Original DataFrame:\n{students}')
print(f'\nCleaned DataFrame:\n{clean_students}')

students = pd.DataFrame({
    'name':[' Alice Smith', 'BOB JOHNSON', ' Carol Lee ', 'DAVID BROWN'],
    'email': ['alice@gmail.com', 'bob@yahoo.com', 'carol@gmail.com', 'david@outlook.com']
})

clean_students = students.copy()
clean_students['name'] = clean_students['name'].str.strip().str.lower()
clean_students['first_name'] = clean_students['name'].str.split().str[0]
clean_students['email_domain'] = clean_students['email'].str.split('@').str[1]
gmail_users = clean_students[clean_students['email_domain'].str.contains('gmail')]

print('\nProbelm 2')
print(f'Original DataFrame:\n{students}')
print(f'\nCleaned DataFrame:\n{clean_students}')
print(f'\nStudents with Gmail address:\n{gmail_users}')
