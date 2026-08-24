import threading
import time


class MyThread(threading.Thread):
    def __init__(self, message, delay):
        super().__init__()
        self.message = message
        self.delay = delay

    def run(self):
        while True:
            print(self.message)
            time.sleep(self.delay)


t1 = MyThread("Good Morning", 1)
t2 = MyThread("Hello", 2)
t3 = MyThread("Welcome", 3)

t1.start()
t2.start()
t3.start()