import random
import time
import sys

def matrix_multiply(n):
    A = [[random.random() for _ in range(n)] for _ in range(n)]
    B = [[random.random() for _ in range(n)] for _ in range(n)]
    C = [[0 for _ in range(n)] for _ in range(n)]

    for i in range(n):
        for j in range(n):
            for k in range(n):
                C[i][j] += A[i][k] * B[k][j]
    
    return C

def main():
    if len(sys.argv) != 2:
        print("Use: python matrix_multiply.py <matrix_size>")
        sys.exit(1)
    
    n = int(sys.argv[1])
    
    start_time = time.time()
    
    matrix_multiply(n)
    
    end_time = time.time()
    
    print(f"Time execution for a matrix of {n}x{n}: {end_time - start_time:.4f} seconds")

if __name__ == "__main__":
    main()

