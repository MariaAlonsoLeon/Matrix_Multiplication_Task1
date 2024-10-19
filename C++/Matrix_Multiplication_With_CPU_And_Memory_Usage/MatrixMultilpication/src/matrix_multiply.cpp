#include <iostream>
#include <vector>
#include <random>
#include <chrono>
#include <fstream>
#include <windows.h>
#include <psapi.h>

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

void log_metrics(int n, double execution_time, double memory_usage, double cpu_usage) {
    std::ofstream file("metrics.txt", std::ios::app);
    if (file.is_open()) {
        file << "Matrix size: " << n << "x" << n << "\n";
        file << "Execution Time: " << execution_time << "s\n";
        file << "Memory Usage: " << memory_usage << "MB\n";
        file << "CPU Usage: " << cpu_usage * 100 << "%\n";
        file << "-----------------------------\n";
    }
    file.close();
}

double get_memory_usage() {
    PROCESS_MEMORY_COUNTERS pmc;
    if (GetProcessMemoryInfo(GetCurrentProcess(), &pmc, sizeof(pmc))) {
        return static_cast<double>(pmc.WorkingSetSize) / 1024.0 / 1024.0; // MB
    }
    return 0.0;
}

double get_cpu_usage() {
    FILETIME ft_idle, ft_kernel, ft_user;
    GetSystemTimes(&ft_idle, &ft_kernel, &ft_user);
    ULARGE_INTEGER li_kernel, li_user;
    li_kernel.LowPart = ft_kernel.dwLowDateTime;
    li_kernel.HighPart = ft_kernel.dwHighDateTime;
    li_user.LowPart = ft_user.dwLowDateTime;
    li_user.HighPart = ft_user.dwHighDateTime;

    return (li_kernel.QuadPart + li_user.QuadPart) / 10000000.0; // Tiempo total de CPU en segundos
}

int main() {
    int sizes[] = {64, 128, 256, 512, 1024, 2048};
    for (int n : sizes) {
        auto start = std::chrono::high_resolution_clock::now();
        double memory_before = get_memory_usage();
        double cpu_before = get_cpu_usage();

        matrix_multiply(n);

        auto end = std::chrono::high_resolution_clock::now();
        std::chrono::duration<double> elapsed = end - start;

        double execution_time = elapsed.count();
        double memory_after = get_memory_usage();
        double cpu_after = get_cpu_usage();

        double memory_usage = memory_after - memory_before;
        double cpu_usage = cpu_after - cpu_before;

        log_metrics(n, execution_time, memory_usage, cpu_usage);

        std::cout << "n=" << n << ", Execution Time: " << execution_time << "s, Memory Usage: " << memory_usage << "MB, CPU Usage: " << cpu_usage * 100 << "%" << std::endl;
    }
    return 0;
}
