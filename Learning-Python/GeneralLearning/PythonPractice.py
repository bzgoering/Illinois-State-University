#This file contains instructions and starter code for your first Python practice homework.
# Mary Elaine Califf and Ben Goering

# we'll get two integers from the user
num1 = int(input("Enter a number: "))
num2 = int(input("Enter another number: "))

print()

# put the sum of the numbers into a variable called sum
sum = num1 + num2

# put num1^num2 into a variable called power
power = num1**num2

# divide num1 by num2 and put the (floating point) result into a variable called quotient
quotient = float(num1/num2)

# divide num1 by num2 and put the (integer) result into a variable called int_quotient
int_quotient = int(num1/num2)

# now we'll print those results
print("Sum:", sum)
print("Power:", power)
print("Quotient:", quotient)
print("Int Quotient:", int_quotient)

# write an if statement that prints the appropriate one of the following messages:
# 1) Both numbers are even
# 2) Both numbers are odd
# 3) num1 is even and num2 is odd
# 4) num1 is odd and num2 is even

if num1%2 == 0 and num2%2 == 0:
    print("Both numbers are even")
elif num1%2 == 0:
    print("num1 is even and num2 is odd")
elif num2%2 == 0:
    print("num2 is even and num1 is odd")
else:
    print("Both numbers are odd")

# now we'll get a string from the user
str1 = input("Enter a string: ")

print()

# print the length of the string
length = len(str1)
print(length)

# print the last character of the string
print(str1[length-1])

# write a for loop to print each character of the string on a separate line
for character in str1:
    print(character)

# do the same thing printing the index for each character before the character. 
# For example, if the string were "the", the output would be
# 0 t
# 1 h
# 2 e
num = 0
for character in str1:
    print(num, character)
    num += 1

# do the task one more time using a while loop (with the indices again)
num = 0
while num < length:
    print(num, str1[num])
    num += 1

# write a for loop to print the multiples of num2 that are less than 100, starting with num2 itself
# for example if num2 is 10, the loop should print 10 20 30 40 50 60 70 80 90 (one number per line)
# do not use an if statement in your loop
# hint: look at the parameters to range
for result in range(num2,100,num2):
    print(result)
     





