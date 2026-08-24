class Number:
    def __init__(self, value):
        self.value = value

    def __add__(self, other):
        return Number(self.value + other.value)

    def __sub__(self, other):
        return Number(self.value - other.value)

    def __mul__(self, other):
        return Number(self.value * other.value)

    def __str__(self):
        return str(self.value)


n1 = Number(10)
n2 = Number(20)

print("First Number :", n1)
print("Second Number:", n2)
print("Addition     :", n1 + n2)
print("Subtraction  :", n1 - n2)
print("Multiplication:", n1 * n2)