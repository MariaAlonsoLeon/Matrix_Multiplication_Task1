import pytest
from matrix_multiply import matrix_multiply

@pytest.mark.parametrize('n', [64, 128, 256, 512, 1024, 2048])
def test_matrix_multiply(benchmark, n):
    benchmark(matrix_multiply, n)

[pytest]
benchmark_warmup_iterations = 5
benchmark_iterations = 10
benchmark_forks = 2
