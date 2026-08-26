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

