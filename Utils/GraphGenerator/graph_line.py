import matplotlib.pyplot as plt
import seaborn as sns
from data_union import *
from data_python import *
from data_java import *

def generate_line_graph(results_dict, metric_name, title, x_label, y_label):
    sns.set(style="whitegrid", palette="muted", font_scale=1.5)

    plt.figure(figsize=(10, 6))

    for language, results in results_dict.items():
        sizes = sorted(results.keys())  # Tamaños de las matrices ordenados
        values = [results[size].get(metric_name, None) for size in sizes]

        plt.plot(sizes, values, label=language, marker='o', markersize=6, linewidth=2)

    plt.title(title, fontsize=16)
    plt.xlabel(x_label, fontsize=14)
    plt.ylabel(y_label, fontsize=14)

    plt.xticks(sizes, rotation=45, ha='right')

    plt.legend(title="Lenguaje", fontsize=12, loc="best")

    plt.tight_layout()

    output_file = f"{metric_name}_comparison.png"

    plt.savefig(output_file)
    plt.close()

    print(f"Graph generated and stored as'{output_file}'")

#Example of creating graphs; however, the parameters or data sources can be changed.

results_dict = {
    #If you want to use benchmark data just uncomment the following lines
    #"Python": extract_benchmark_stats_from_file("..\\..\\Python\\Benchmark\\BenchMarkTasks\\.benchmarks\\Windows-CPython-3.12-64bit\\0001_benchmark_results.json.json"),
    #"Java": extract_java_benchmark_stats_from_file("..\\..\\Java\\Benchmark\\MatrixMultiplicationJava\\benchmark_results2.json"),
    "Python": process_files_in_directory("..\\..\\Python\\shared_folder"),
    "Java": process_files_in_directory("..\\..\\Java\\shared_folder"),
    "C++": process_files_in_directory("..\\..\\C++\\shared_folder"),
    "Rust": process_files_in_directory("..\\..\\Rust\\shared_folder"),
}

generate_line_graph(
    results_dict=results_dict,
    metric_name="time_execution",  # You can change this to other metrics
    title="Time execution using perf command",
    x_label="Matrix Size (x)",
    y_label="Time execution (milliseconds)",
)

print("Graph generated and saved according to the selected metric.")
