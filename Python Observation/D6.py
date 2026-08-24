from abc import ABC, abstractmethod


class Person(ABC):
    def __init__(self, name, weight, height):
        self.name = name
        self.weight = weight
        self.height = height

    @abstractmethod
    def get_bmi_result(self):
        pass


class Obese(Person):
    def get_bmi_result(self):
        height_m = self.height * 0.3048
        bmi = self.weight / (height_m ** 2)

        if bmi >= 25:
            return "obese"
        else:
            return "not obese"


person = Obese("John", 90, 5.8)

print("Name   :", person.name)
print("Result :", person.get_bmi_result())