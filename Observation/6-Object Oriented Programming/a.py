class Person:
    def __init__(self, name, age, weight, height):
        self.name = name
        self.age = age
        self.weight = weight
        self.height = height

    def get_bmi_result(self):
        height_m = self.height * 0.3048
        bmi = self.weight / (height_m ** 2)

        if bmi < 18.5:
            return "underweight"
        elif bmi < 25:
            return "healthy"
        else:
            return "obese"


p = Person("Arun", 21, 65, 5.7)
print("BMI Result:", p.get_bmi_result())