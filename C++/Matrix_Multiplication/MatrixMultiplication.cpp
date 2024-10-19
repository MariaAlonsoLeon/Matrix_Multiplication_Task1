#include <iostream>
#include <vector>
#include <random>
#include <chrono>
#include <cstdlib> 

void matrix_multiply(int n) {
    std::vector<std::vector<double>> A(n, std::vector<double>(n));
    std::vector<std::vector<double>> B(n, std::vector<double>(n));
    std::vector<std::vector<double>> C(n, std::vector<double>(n, 0.0));

    std::mt19937 gen(0); 
    std::uniform_real_distribution<> dis(0.0, 1.0);

    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j) {
            A[i][j] = dis(gen);
            B[i][j] = dis(gen);
        }

    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j)
            for (int k = 0; k < n; ++k)
                C[i][j] += A[i][k] * B[k][j];
}

int main(int argc, char* argv[]) {
    if (argc != 2) {
        std::cerr << "Use: " << argv[0] << " <matrix_size>\n";
        return 1;
    }

    int n = std::atoi(argv[1]); 
    //Uncomment these lines to measure execution time without using the perf command
    //auto start = std::chrono::high_resolution_clock::now();
    matrix_multiply(n);
    //auto end = std::chrono::high_resolution_clock::now();
    //std::chrono::duration<double> elapsed = end - start;
    //std::cout << "Time taken for n = " << n << ": " << elapsed.count() << " seconds\n";

    return 0;
}
