import matplotlib.pyplot as plt

# Memory Data
matrix_size = [64, 128, 256, 512, 1024, 2048]
python = [0.50390625, 1.5, 1.96484375, 1.625, 1.5390625, 2.8125]
java = [4.0, 4.481986999511719, 6.409843444824219, 12.487838745117188, 25.391586303710938, 106.521728515625]
c_plus_plus = [0.0625, 0.0625, 0.460938, 0.277344, 0.578125, 0.308594]
rust = [22747.16796875, 22747.12109375, 22744.87109375, 22740.2109375, 22636.47265625, 22179.51171875]


plt.figure(figsize=(10, 6))

plt.plot(matrix_size, python, label='Python', marker='o', linestyle='-', color='blue')
plt.plot(matrix_size, java, label='Java', marker='o', linestyle='-', color='green')
plt.plot(matrix_size, c_plus_plus, label='C++', marker='o', linestyle='-', color='red')
plt.plot(matrix_size, rust, label='Rust', marker='o', linestyle='-', color='purple')

plt.xlabel('Matrix size (elements)')
plt.ylabel('Memory Usage (MB)')
plt.title('Memory Usage Comparison by Programming Language')

plt.legend()

plt.grid(True)
plt.xticks(matrix_size)

plt.yscale('log')

plt.show()
