scores = [85,72,None,94,58,88]
valid = 0
total = 0
for score in scores:
    if score is None:
        continue
    else:
        if score >= 60:
            print(score, ': Pass')
        else:
            print(score, ": Fail")
        valid += 1
        total += score
print("Total:", total)
print("Number of valid scores:", valid)

courses = ["IT 166", "IT 168", "IT 178"]
courses.append("IT 261")
courses.insert(1, "IT 191")
courses.remove("IT 168")
print("First two courses: ", courses[0:2])
print("Last course: ", courses[-1])
print("Final course list:", courses)

print("exercise 1\n")
scores = {
    "Alice" : 90,
    "Bob" : 85,
    "Carol" : 95,
    "David" : 78,
}

print("Carol's score:", scores.get("Carol"))
scores.update({"Emma": 88})

print("\nAll students")
for name in scores:
    print(name, ":", scores.get(name))

print("\nStudents with scores of 90 or higher")
for name in scores:
    if scores.get(name) >= 90:
        print(name, ":", scores.get(name))

print("\n--------------------------------")
print("\nexercise 2\n")
set1 = {1,2,3,4,5}
set2 = {4,5,6,7,8}
union_set = set1.union(set2)
print("Union:", union_set)
intersection_set = set1.intersection(set2)
print("\nIntersection:", intersection_set)
square_list = [x*x for x in range(1,11)]
print("\nSquares:", square_list)
even_squares = [x for x in square_list if x%2 == 0]
print("\nSquares of even numbers: ", even_squares)
