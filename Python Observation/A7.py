import threading

even_count = 0


def count_even(numbers):
    global even_count

    for num in numbers:
        if num % 2 == 0:
            even_count += 1


numbers = []

n = int(input("Enter number of values: "))

for i in range(n):
    numbers.append(int(input("Enter number: ")))

mid = n // 2

part1 = numbers[:mid]
part2 = numbers[mid:]

t1 = threading.Thread(target=count_even, args=(part1,))
t2 = threading.Thread(target=count_even, args=(part2,))

# Start threads
t1.start()
t2.start()

t1.join()
t2.join()

print("\nTotal even numbers:", even_count)