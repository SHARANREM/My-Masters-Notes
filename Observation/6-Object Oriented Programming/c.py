class Number:
    def __init__(self, value):
        self.value = value

    def __add__(self, other):
        return Number(self.value + other.value)

    def __str__(self):
        return str(self.value)


a = Number(10)
b = Number(20)

c = a + b

print("Result:", c)