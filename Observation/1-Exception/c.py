# Using else and finally

try:
    num = int(input("Enter a number: "))
    print("Number:", num)

except ValueError:
    print("Invalid input")

else:
    print("No exception occurred")

finally:
    print("Program completed")