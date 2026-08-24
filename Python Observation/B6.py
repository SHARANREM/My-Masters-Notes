# 1. Single Inheritance
class Animal:
    def eat(self):
        print("Animal eats")


class Dog(Animal):
    def bark(self):
        print("Dog barks")


print("1. Single Inheritance")
d = Dog()
d.eat()
d.bark()


# 2. Multilevel Inheritance
class Grandparent:
    def house(self):
        print("Grandparent has a house")


class Parent(Grandparent):
    def car(self):
        print("Parent has a car")


class Child(Parent):
    def bike(self):
        print("Child has a bike")


print("\n2. Multilevel Inheritance")
c = Child()
c.house()
c.car()
c.bike()


# 3. Multiple Inheritance
class Father:
    def skills1(self):
        print("Father: Driving")


class Mother:
    def skills2(self):
        print("Mother: Cooking")


class Child2(Father, Mother):
    pass


print("\n3. Multiple Inheritance")
c2 = Child2()
c2.skills1()
c2.skills2()


# 4. Hierarchical Inheritance
class Vehicle:
    def start(self):
        print("Vehicle starts")


class Car(Vehicle):
    def drive(self):
        print("Car is driving")


class Bike(Vehicle):
    def ride(self):
        print("Bike is riding")


print("\n4. Hierarchical Inheritance")
car = Car()
car.start()
car.drive()

bike = Bike()
bike.start()
bike.ride()


# 5. Hybrid Inheritance
class Person:
    def show_person(self):
        print("I am a person")


class Student(Person):
    def study(self):
        print("I am studying")


class Employee(Person):
    def work(self):
        print("I am working")


class WorkingStudent(Student, Employee):
    pass


print("\n5. Hybrid Inheritance")
ws = WorkingStudent()
ws.show_person()
ws.study()
ws.work()