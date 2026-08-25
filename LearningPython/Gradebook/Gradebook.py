class Grade:
    totalPoints = 0
    totalCorrect = 0
    #constructor
    #parm: correct questions and total questions
    def __init__(self, correct, total, name):
         self.name = name
         self.correct = int(correct)
         self.total = int (total)
         Grade.totalCorrect = Grade.totalCorrect + correct
         Grade.totalPoints = Grade.totalPoints + self.total
         self.percent = self.calcPercent()
         self.letter = self.calcLetter()

    #calculates letter grade based off percent
    def calcLetter(self):
        if self.percent < 60:
            return 'F'
        elif self.percent < 70:
            return 'D'
        elif self.percent < 80:
            return 'C'
        elif self.percent < 90:
             return 'B'
        elif self.percent < 100:
            return 'A'
        else:
            return ''

    #calculates percent based off user inputs
    def calcPercent(self):
        return self.correct / self.total * 100

    #getters and setters
    def getName(self):
        return self.name

    def getCorrect(self):
        return self.correct

    def getTotal(self):
        return self.total

    def setName(self, name):
        self.name = name
        print("Grade name changed")

    def setCorrect(self, correct):
        self.correct = correct
        print("correct questions changed")

    def setTotal(self, total):
        self.total = total
        print("total questions changed")

#User choices
def printChoice():
    print("\n0. exit")
    print("1. add student")
    print("2. add student's grade")
    print("3. edit student's record")
    print("4. delete student")
    print("5. delete student's grade")
    print("6. show student's grade")
    return input("Enter your choice: ")

#searches for student in rows
def searchStudent(matrix, student):
    for index, x in enumerate(matrix):
        if x[0] == student:
            print("student found")
            return index
    return -1

#searches column for grades
def searchGrade(matrix, gName, student):
    record = matrix[student]
    for index, x in enumerate(record[1:]):
        if x.getName() == gName:
            print("grade found")
            return index+1
    return -1

def checkRepeatStudentNames(name, matrix):
    for row in matrix:
        if row[0] == name:
            return True
    return False

def checkRepeatGradeNames(name, matrix):
    for row in matrix:
        for column in row[1:]:
            if column.getName() == name:
                return True
    return False
#toString
def toString(matrix):
    for row in matrix:
        print("Student: " + row[0])
        for column in row[1:]:
            print(column.getName() + ": " + str(column.getCorrect()) + "/" + str(column.getTotal()))
        print()

#display
print("Welcome to your Gradebook/nPlease select an option:")

#variables
gradeBook = []
done = False
gradeBook.append(["josh"])
gradeBook[0].append(Grade(90,100,"Spelling"))

gradeBook.append(["bill"])
gradeBook[1].append(Grade(90,100,"Spelling"))

try:
    # check if user entered a valid entry
    while not done:
        #gets user choice
        userInput = printChoice()

        #validates choice
        if not (userInput == '0' or userInput == '1' or userInput == '2' or userInput == '3' or userInput == '4' or userInput == '5' or userInput == '6'):
            userInput = printChoice()

        #exit
        if userInput == '0':
            done = True

        #adds student
        elif userInput == '1':
            StudentName = input("Enter the student's name: ")
            if not (checkRepeatStudentNames(StudentName, gradeBook)):
                gradeBook.append([StudentName])
            else:
                print("Student's name already exists - please use a unique name")

        #adds student grade
        elif userInput == '2':
            #gets student name
            studentName = input("Enter the student's name: ")
            #search for student
            studentIndex = searchStudent(gradeBook, studentName)
            #validates that student is in the record
            if len(gradeBook) == 0 or studentIndex == -1:
                print("Student Not Found")
            else:
                #adds new grade and validate user inputs
                try:
                    correct = int(input("Enter number of correct questions: "))
                    total = int(input("Enter total number of questions: "))
                    name = input("Enter a name for this grade: ")

                    if not checkRepeatGradeNames(name, gradeBook):
                        gradeBook[studentIndex].append(Grade(correct, total, name))
                    else:
                        print("Grade Name already exists - please use a unique name")
                except ValueError:
                    print("Error - Invalid Input")

        #edit gradebook
        elif userInput == '3':
            #gets student name and validates
            studentName = input("Enter the student's name: ")
            studentIndex = searchStudent(gradeBook, studentName)
            if len(gradeBook) == 0 or studentIndex == -1:
                print("Student Not Found")
            #gets edit request
            else:
                print("What are you editing")
                print("1. student name")
                print("2. grade name")
                print("3. edit student's record")
                userEditInput = input("Enter your choice: ")
                #edit students name
                if userEditInput == '1':
                    newName = input("Enter new name: ")
                    if not (checkRepeatStudentNames(newName, gradeBook)):
                        gradeBook[studentIndex][0] = newName
                    else:
                        print("Student's name already exists - please use a unique name")
                #edit grade name
                elif userEditInput == '2':
                    #gets grade details
                    oldGradeName = input("Enter old name: ")
                    newGradeName = input("Enter new grade name: ")
                    
                    if not checkRepeatGradeNames(newGradeName, gradeBook):
                        # searches for grade and validates
                        grade = searchGrade(gradeBook, oldGradeName, studentIndex)
                        # changes
                        if grade > -1:
                            gradeBook[studentIndex][grade].setName(newGradeName)
                        else:
                            print("Grade Not Found")
                    else:
                        print("Grade Name already exists - please use a unique name")

                #edit grade score
                elif userEditInput == '3':
                    gradeName = input("Enter grade name: ")
                    grade = searchGrade(gradeBook, gradeName, studentIndex)
                    if grade > -1:
                         newCorrect = input("Enter new correct grade: ")
                         newTotal = input("Enter new total grade: ")

        #delete student (this will delete whole column)
        elif userInput == '4':
            try:
                del gradeBook[searchStudent(gradeBook, input("Enter student's name: "))]
                print("Student Deleted")
            except ValueError:
                print("Error - Student Not Found")

        #delete grade (this will only delete a single grade)
        elif userInput == '5':
            StudentName = input("Enter the student's name: ")
            studentIndex = searchStudent(gradeBook, StudentName)
            if len(gradeBook) == 0 or studentIndex == -1:
                print("Student Not Found")
            else:
                gradeName = input("Enter grade name: ")
                gradeIndex = searchGrade(gradeBook, gradeName, studentIndex)
                try:
                    del gradeBook[studentIndex][gradeIndex]
                except:
                    print("Grade Not Found")

        #show grades
        elif userInput == '6':
            toString(gradeBook)
except:
    print("Error - unknown error has occured, please restart the program.")