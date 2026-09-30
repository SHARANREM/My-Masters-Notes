import numpy as np
from PIL import Image

# Create a white image
arr = np.ones((300, 300, 3), dtype=np.uint8) * 255

image = Image.fromarray(arr)
image.save("image.png")

print("White image created successfully")