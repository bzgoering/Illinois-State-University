def calculate_total (price, quantity = 1):
    return price * quantity
print(calculate_total(12.5, 4))
print(calculate_total(8))

def get_statistics(x, y, z):
    smallest = min(x, y, z)
    largest = max(x, y, z)
    return (smallest, largest)
smallest, biggest = get_statistics(12,5,20)
print('Minimum:', smallest)
print('Maximum:', biggest)

def generate_multiples_of_three(limit):
    current = 0
    while current <= limit:
        if current%3 == 0:
            yield current
        current += 1
try:
    user_input = float(input("Enter the limit: "))
    for number in generate_multiples_of_three(user_input):
        print(number)
except ValueError:
    print("Please enter a valid number")

print("\n-Problem 2-\n")
with open("scores.txt", mode ='w', encoding='utf-8') as file:
    file.write('85\n')
    file.write('92\n')
    file.write('78\n')
with open("scores.txt", mode = 'a', encoding = 'utf-8') as file:
    file.write("95\n")
with open("scores.txt", mode = 'r', encoding = 'utf-8') as file:
    for line in file:
        print(line.rstrip())

