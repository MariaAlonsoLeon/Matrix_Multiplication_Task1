import json
import re

def extract_benchmark_stats_from_file(file_path):
    with open(file_path, 'r') as f:
        data = json.load(f)

    benchmark_stats = {}

    for benchmark in data.get("benchmarks", []):
        name = benchmark.get("name", "unknown")

        size_match = re.search(r'\[(\d+)\]', name)
        if size_match:
            matrix_size = int(size_match.group(1))
        else:
            matrix_size = "unknown"

        stats = benchmark.get("stats", {})

        benchmark_stats[matrix_size] = {
            "min_time": stats.get("min", 0) * 1000,
            "max_time": stats.get("max", 0) * 1000,
            "time_execution": stats.get("mean", 0) * 1000,
            "median_time": stats.get("median", 0) * 1000,
            "stddev": stats.get("stddev", 0) * 1000,
            "rounds": stats.get("rounds", 0),
            "total_time": stats.get("total", 0) * 1000,
            "outliers": stats.get("outliers", ""),
            "ops": stats.get("ops", 0)
        }

    return benchmark_stats
