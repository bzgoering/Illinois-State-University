class Person:
    def __init__(self,first,last):
        self.first = first
        self.last = last
        self.email = first[0] + last[:5] + "@ilstu.edu"
        self.email = self.email.lower()

    def print_directory(self):
        print(self.first,self.last)
        print(self.email)

class Student(Person):
    def __init__(self,first,last,major,classLevel):
        super().__init__(first,last)
        self.major = major
        self.classLevel = classLevel

    def print_directory(self):
        super().print_directory()
        print(self.major)
        print(self.classLevel)

class Employee(Person):
    def __init__(self,first,last,department,office):
        super().__init__(first,last)
        self.department = department
        self.office = office

    def print_directory(self):
        super().print_directory()
        print(self.department)
        print(self.office)
    
class Faculty(Employee):
    def __init__(self,first,last,department,office,researchArea):
        super().__init__(first,last,department,office)
        self.researchArea = researchArea

    def print_directory(self):
        super().print_directory()
        print(self.researchArea)


p1 = Person("John", "Doe")
p2 = Person("Ben", "Goering")

s1 = Student("John", "Doe", "Political Science", "Junior")
s2 = Student("Ben", "Goering", "Computer Science", "Junior")

e1 = Employee("John", "Doe", "English", "STV 404B")
e2 = Employee("Mehdi", "Karimi", "Math", "STV 314")

f1 = Faculty("Mangolika", "Bhattacharya", "Information Technology", "WIH 17A", "Machine Learning, Natural Language Procsessing, DIgital Twin Technologies")
f2 = Faculty("Mehdi", "Karimi", "Math", "STV 314", "Quantum Computing")

myList = [p1,p2,s1,s2,e1,e2,f1,f2]

for x in myList:
    x.print_directory()
    print()
