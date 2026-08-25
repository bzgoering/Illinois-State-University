all: main.o ArrayList.o
	g++ main.o ArrayList.o -o hw

main.o: main.cpp ArrayListClass.h
	g++ -c main.cpp

Arraylist.o: ArrayList.cpp ArrayListClass.h
	g++ -c ArrayList.cpp