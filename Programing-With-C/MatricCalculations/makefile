all: main.o Matrix.o
	g++ main.o Matrix.o -o hw3

main.o: main.cpp matrix_head.h
	g++ -c main.cpp

Matrix.o: Matrix.cpp matrix_head.h
	g++ -c Matrix.cpp