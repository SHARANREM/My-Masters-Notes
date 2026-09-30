# Using raise with exception class

class NumberError(Exception):
    pass

try:
    num = int(input("Enter a positive number: "))

    if num <= 0:
        raise NumberError("Number must be positive")

    print("Valid number")

except NumberError as e:
    print("NumberError:", e)