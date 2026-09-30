# Single inheritance
class Animal:
    def eat(self):
        print("Animal eats")

class Dog(Animal):
    def bark(self):
        print("Dog barks")


# Multilevel inheritance
class Puppy(Dog):
    def play(self):
        print("Puppy plays")


# Multiple inheritance
class Father:
    def skill1(self):
        print("Father's skill")

class Mother:
    def skill2(self):
        print("Mother's skill")

class Child(Father, Mother):
    pass


# Hierarchical inheritance
class Cat(Animal):
    def meow(self):
        print("Cat meows")


# Hybrid inheritance
class Pet(Animal):
    def care(self):
        print("Pet care")

class PetDog(Pet, Dog):
    pass


d = Dog()
d.eat()
d.bark()

p = Puppy()
p.eat()
p.bark()
p.play()

c = Child()
c.skill1()
c.skill2()

cat = Cat()
cat.eat()
cat.meow()

pd = PetDog()
pd.eat()
pd.bark()
pd.care()