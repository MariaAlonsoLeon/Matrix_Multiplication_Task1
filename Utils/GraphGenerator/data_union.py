import os
import re

def extract_metrics_from_file(file_path):

    metrics = {
        'time_execution': None,
        'cpus-utilized': None,
        'time-elapsed': None,
        'user-time': None,
        'sys-time': None
    }

    with open(file_path, 'r') as file:
        content = file.read()

        task_clock_match = re.search(r"(\d{1,3}(?:[\.,]\d{3})*(?:[\.,]\d+)) msec task-clock", content)
        if task_clock_match:
            time_execution_str = task_clock_match.group(1)
            time_execution_str = time_execution_str.replace('.', '').replace(',', '.')
            metrics['time_execution'] = float(time_execution_str)
        else:
            print("'task-clock' not found")

        cpus_utilized_match = re.search(r"#\s+(\d{1,3}(?:[\.,]\d{3})*(?:[\.,]\d+)) CPUs utilized", content)
        if cpus_utilized_match:
            cpus_utilized_str = cpus_utilized_match.group(1)
            cpus_utilized_str = cpus_utilized_str.replace('.', '').replace(',', '.')
            metrics['cpus-utilized'] = float(cpus_utilized_str)
        else:
            print("'cpus-utilized' not found")

        time_elapsed_match = re.search(r"(\d{1,3}(?:[\.,]\d{3})*(?:[\.,]\d+)) seconds time elapsed", content)
        if time_elapsed_match:
            time_elapsed_str = time_elapsed_match.group(1)
            time_elapsed_str = time_elapsed_str.replace('.', '').replace(',', '.')
            metrics['time-elapsed'] = float(time_elapsed_str)
        else:
            print("'time elapsed' not found")

        user_time_match = re.search(r"(\d{1,3}(?:[\.,]\d{3})*(?:[\.,]\d+)) seconds user", content)
        if user_time_match:
            user_time_str = user_time_match.group(1)
            user_time_str = user_time_str.replace('.', '').replace(',', '.')
            metrics['user-time'] = float(user_time_str)
        else:
            print("'user-time' not found")

        sys_time_match = re.search(r"(\d{1,3}(?:[\.,]\d{3})*(?:[\.,]\d+)) seconds sys", content)
        if sys_time_match:
            sys_time_str = sys_time_match.group(1)
            sys_time_str = sys_time_str.replace('.', '').replace(',', '.')
            metrics['sys-time'] = float(sys_time_str)
        else:
            print("'sys-time' not found")

    return metrics


def process_files_in_directory(directory_path):
    results = {}

    for file_name in os.listdir(directory_path):
        if file_name.endswith(".txt"):
            matrix_size = int(
                re.search(r'(\d+)', file_name).group(1))
            file_path = os.path.join(directory_path, file_name)

            metrics = extract_metrics_from_file(file_path)

            results[matrix_size] = metrics

    return results


def print_results(results):
    for matrix_size, metrics in results.items():
        print(f"Matrix size: {matrix_size}x{matrix_size}")
        for metric, value in metrics.items():
            print(f"  {metric.replace('-', ' ').capitalize()}: {value}")
        print("=" * 40)


def get_metric_list(results, metric_name):
    metric_list = []

    for matrix_size, metrics in sorted(results.items()):
        metric_value = metrics.get(metric_name, None)
        metric_list.append(metric_value)

    return metric_list
