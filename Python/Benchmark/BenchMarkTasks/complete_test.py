import time
import psutil
import json
import os
from matrix_multiply import matrix_multiply

def log_metrics(n, execution_time, memory_usage, cpu_usage):
    data = {
        "size": n,
        "execution_time": execution_time,
        "memory_usage": memory_usage,
        "cpu_usage": cpu_usage
    }

    if not os.path.exists('metrics.json'):
        with open('metrics.json', 'w') as f:
            json.dump([data], f, indent=4)
    else:
        with open('metrics.json', 'r+') as f:
            existing_data = json.load(f)
            existing_data.append(data)
            f.seek(0)
            json.dump(existing_data, f, indent=4)


if __name__ == "__main__":
    sizes = [64, 128, 256, 512, 1024, 2048]
    for n in sizes:
        process = psutil.Process(os.getpid())

        start_time = time.time()
        start_cpu = psutil.cpu_percent(interval=None)
        start_memory = process.memory_info().rss
        matrix_multiply(n)
        execution_time = time.time() - start_time
        end_cpu = psutil.cpu_percent(interval=None)
        end_memory = process.memory_info().rss

        cpu_usage = end_cpu - start_cpu
        memory_usage = (end_memory - start_memory) / (1024 * 1024)

        log_metrics(n, execution_time, memory_usage, cpu_usage)
        print(f"n={n}, Execution Time: {execution_time}s, Memory Usage: {memory_usage}MB, CPU Usage: {cpu_usage}%")
