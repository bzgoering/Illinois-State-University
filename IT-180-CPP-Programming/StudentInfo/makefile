all: Main.o Student.o
	g++ Main.o Student.o -o hw2

Main.o: Main.cpp StudentClass.h
	g++ -c Main.cpp

Student.o: Student.cpp StudentClass.h
	g++ -c Student.cpp