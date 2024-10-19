import re
import json
import matplotlib.pyplot as plt

def process_results(file_path):
    with open(file_path, 'r', encoding='utf-8') as file:
        content = file.read()

    print("File content:")
    print(content)

    pattern = r"(Benchmark\.MatrixMultiplicationBenchmarkJMH\.[\w]+)\s+avgt\s+\d+\s+([\d,\.]+)\s+±\s+([\d,\.]+)\s+ms/op"

    results = re.findall(pattern, content)

    if not results:
        print("No results found. Please check the file format.")
        return []

    print("Results captured by the regular expression:")
    for result in results:
        print(result)

    data = []
    for result in results:
        benchmark = result[0].split('.')[-1]
        score = float(result[1].replace(',', '.'))
        error = float(result[2].replace(',', '.'))

        data.append({
            'benchmark': benchmark,
            'score': score,
            'error': error,
            'units': 'ms/op'
        })

    return data

file_path = "..\\..\\Java\\Benchmark\\MultipleAlgorithmMatrixMultiplication\\benchmark_output.txt"

results = process_results(file_path)

if results:
    results_json = json.dumps(results, indent=4)
    print("JSON Results:")
    print(results_json)

    benchmarks = [result['benchmark'] for result in results]
    scores = [result['score'] for result in results]
    errors = [result['error'] for result in results]

    fig, ax = plt.subplots(figsize=(10, 6))

    ax.barh(benchmarks, scores, xerr=errors, color='skyblue', edgecolor='black')

    ax.set_xlabel('Score (ms/op)')
    ax.set_ylabel('Benchmark')
    ax.set_title('Benchmark Comparison - Score vs. Error')

    plt.tight_layout()
    plt.show()

else:
    print("Could not process the file correctly.")
