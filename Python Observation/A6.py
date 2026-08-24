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


person1 = Person(input("Enter name: "),
                 int(input("Enter age: ")),
                 float(input("Enter weight (kg): ")),
                 float(input("Enter height (ft): ")))

person2 = Person(input("Enter name: "),
                 int(input("Enter age: ")),
                 float(input("Enter weight (kg): ")),
                 float(input("Enter height (ft): ")))

print("\n--- BMI Results ---")
print(person1.name, ":", person1.get_bmi_result())
print(person2.name, ":", person2.get_bmi_result())