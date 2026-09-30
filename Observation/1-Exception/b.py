# User-defined exception

class AgeError(Exception):
    pass

try:
    age = int(input("Enter your age: "))

    if age < 18:
        raise AgeError("Age must be 18 or above")

    print("Eligible")

except AgeError as e:
    print("AgeError:", e)