# Exception Handling

try:
    choice = int(input("Enter choice (1-5): "))

    if choice == 1:
        num = int(input("Enter a number: "))
    elif choice == 2:
        print([10, 20, 30][5])
    elif choice == 3:
        print(name)
    elif choice == 4:
        print("5" + 10)
    elif choice == 5:
        print(10 / 0)
    else:
        print("Invalid choice")

except ValueError:
    print("ValueError: Invalid value")

except IndexError:
    print("IndexError: Index out of range")

except NameError:
    print("NameError: Variable not defined")

except TypeError:
    print("TypeError: Invalid data type")

except ZeroDivisionError:
    print("ZeroDivisionError: Cannot divide by zero")