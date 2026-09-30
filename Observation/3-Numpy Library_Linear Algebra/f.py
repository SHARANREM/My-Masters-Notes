import numpy as np
from PIL import Image

# NumPy array to image
arr = np.zeros((200, 200, 3), dtype=np.uint8)
arr[:, :, 0] = 255
Image.fromarray(arr).save("image.png")

# Image to NumPy array
image = Image.open("image.png")
new_arr = np.array(image)

print("Image converted to NumPy array")
print("Shape:", new_arr.shape)