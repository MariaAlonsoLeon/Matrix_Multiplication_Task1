# Matrix Multiplication Benchmarking Project

This project presents a detailed benchmarking analysis of matrix multiplication performance across four programming languages: Python, Java, C++, and Rust. The aim is to evaluate their execution time, memory usage, and CPU utilization for different matrix sizes, simulating the challenges encountered in Big Data applications.

## Cover Page:

- **Subject:** Big Data (BD)
- **Academic Year:** 2024-2025
- **Degree:** Data Science and Engineering (GCID)
- **School:** School of Computer Engineering (EII)
- **University:** University of Las Palmas de Gran Canaria (ULPGC)

## Development Environment:

- **IDEs/Editors Used:** PyCharm, IntelliJ, CLion, RustRover, and Bash on a Fedora Virtual Machine (1 CPU and 2048 MB).
- **Version Control:** Git & GitHub for source code management and collaboration.

## Folder Structure

The repository is organized into five main folders, one for each programming language, along with a utilities folder:

- **C++**:
  - `shared_folder`: Contains text files generated by scripts.
  - `Scripts`: Includes the `perf_execution` script to run matrix multiplication for various matrix sizes.
  - `Matrix_Multiplication`: Implements the matrix multiplication function.
  - `Matrix_Multiplication_With_CPU_And_Memory`: A CLion project for monitoring memory usage, CPU consumption, and execution time.

- **Rust**:
  - Structured similarly to the C++ folder, but with Rust-specific code adaptations.

- **Java**:
  - `shared_folder`: Functions similarly to the shared folder in C++.
  - `scripts`: Contains scripts equivalent to those in the C++ and Rust folders.
  - `Matrix_Multiplication`: Implements similar functionality as C++ and Rust.
  - `Benchmark`: Divided into:
    - `MatrixMultiplicationJava`: Java benchmarking code using IntelliJ.
    - `MultipleAlgorithmMultiplication`: Contains multiple matrix multiplication algorithms and an interface for benchmarking them.

- **Python**:
  - Follows a similar structure to the Java folder, with a benchmark project dedicated to matrix multiplication.

- **Utils**:
  - Contains the `Graphs_Generator` PyCharm project, which includes utilities for extracting data and generating performance graphs.

## Dependencies

### Python
- `pytest` for benchmarking.
- `matplotlib` for graph generation.
- `psutil` for memory and CPU tracking.

### Java
- `JMH` (Java Microbenchmark Harness) for benchmarking.
- `ManagementFactory` for system resource tracking.

### C++
- Standard C++ libraries (`iostream`, `chrono`, `psapi.h`).

### Rust
- `sysinfo` crate for system resource tracking.

## How to Run the Benchmarks

### Python
1. Navigate to the `Python/Benchmark/` folder.
2. Run the benchmark using pytest:
    ```bash
    pytest --benchmark-only
    ```

### Java
1. Navigate to the `Java/MatrixMultiplicationJava/` folder.
2. Use Maven or your preferred IDE (e.g., IntelliJ) to run the JMH benchmarks:
    ```bash
    mvn clean install
    ```

### C++
1. Navigate to the `C++/Scripts/` folder.
2. Compile and run the benchmarks:
    ```bash
    g++ -o benchmark matrix_multiplication.cpp
    ./benchmark
    ```

### Rust
1. Navigate to the `Rust/Matrix_Multiplication/` folder.
2. Compile and run the benchmark:
    ```bash
    cargo run --release
    ```

Alternatively, you can run the script `./perf_execution.sh`, which will generate text files for each matrix size.

## Results

The benchmarking results cover three key performance metrics:

- **Execution Time (ms):** Measured for each matrix size.
- **Memory Usage (MB):** The amount of memory consumed during matrix operations.
- **CPU Usage (%):** The percentage of CPU utilized during the benchmarking.

### Performance Summary

- **Rust**: Consistently delivers one of the fastest execution times but consumes the highest amount of memory and CPU resources.
- **Java**: Provides a balanced performance, achieving near-optimal execution times with better memory and CPU efficiency than Rust.
- **C++**: Demonstrates the most efficient memory usage and stable execution times, making it ideal for performance-critical tasks with constrained resources.
- **Python**: Suffers from significant performance degradation, especially with larger matrices, due to its interpreted nature and lack of low-level optimizations.

### Visualizing the Results

You can generate and visualize the benchmarking data using the `Graphs_Generator` project in the `Utils` folder. This tool provides graphs for execution time, memory usage, and CPU consumption for each language across different matrix sizes.

## Usage
To sum up, you can use the project by following these steps.

1. Open the appropriate IDE for the programming language you wish to test.
2. Navigate to the respective folder and run the provided script or project.
3. Use the Graphs Generator in the `Utils` folder to visualize the results from the benchmarking process.
