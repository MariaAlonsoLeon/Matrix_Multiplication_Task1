import json

def extract_java_benchmark_stats_from_file(file_path, mode_filter="avgt"):
    with open(file_path, 'r') as f:
        data = json.load(f)

    benchmark_stats = {}

    for benchmark in data:
        mode = benchmark.get("mode", "unknown")
        if mode != mode_filter:
            continue
        matrix_size = benchmark.get("params", {}).get("size", "unknown")
        primary_metric = benchmark.get("primaryMetric", {})

        score = primary_metric.get("score", 0)
        score_error = primary_metric.get("scoreError", 0)
        #Uncomment the lines to include percentile information.
        #percentiles = primary_metric.get("scorePercentiles", {})
        unit = primary_metric.get("scoreUnit", "")

        benchmark_stats[int(matrix_size)] = {
            "time_execution": score,
            "score_error": score_error,
            #"percentiles": percentiles,
            "unit": unit
        }
    return benchmark_stats
