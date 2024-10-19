use rand::random;
use std::time::Instant;
use std::process::Command;
use std::collections::HashMap;
use sys_info;

fn matrix_multiply(n: usize) -> Vec<Vec<f64>> {
    let mut a: Vec<Vec<f64>> = vec![vec![0.0; n]; n];
    let mut b: Vec<Vec<f64>> = vec![vec![0.0; n]; n];
    let mut c: Vec<Vec<f64>> = vec![vec![0.0; n]; n];

    for i in 0..n {
        for j in 0..n {
            a[i][j] = random::<f64>();
            b[i][j] = random::<f64>();
        }
    }

    for i in 0..n {
        for j in 0..n {
            for k in 0..n {
                c[i][j] += a[i][k] * b[k][j];
            }
        }
    }

    c
}

fn measure_performance(n: usize) -> HashMap<String, f64> {
    let start_time = Instant::now();

    matrix_multiply(n);

    let memory_usage = get_memory_usage();
    let duration = start_time.elapsed().as_secs_f64();

    let mut results = HashMap::new();
    results.insert("memory_usage_mb".to_string(), memory_usage);
    results.insert("time_execution_seconds".to_string(), duration);

    results
}

fn get_memory_usage() -> f64 {
    let total_mem_output = Command::new("wmic")
        .arg("os")
        .arg("get")
        .arg("TotalVisibleMemorySize")
        .output()
        .expect("Failed to execute process");

    let free_mem_output = Command::new("wmic")
        .arg("os")
        .arg("get")
        .arg("FreePhysicalMemory")
        .output()
        .expect("Failed to execute process");

    let total_memory_kb = if let Ok(output_str) = String::from_utf8(total_mem_output.stdout) {
        output_str
            .lines()
            .filter_map(|line| line.trim().parse::<f64>().ok())
            .next()
            .unwrap_or(0.0)
    } else {
        0.0
    };

    let free_memory_kb = if let Ok(output_str) = String::from_utf8(free_mem_output.stdout) {
        output_str
            .lines()
            .filter_map(|line| line.trim().parse::<f64>().ok())
            .next()
            .unwrap_or(0.0)
    } else {
        0.0
    };

    let used_memory_kb = total_memory_kb - free_memory_kb;

    used_memory_kb / 1024.0
}

fn main() {
    let matrix_sizes = vec![64, 128, 256, 512, 1024, 2048];

    let mut performance_dict: HashMap<usize, HashMap<String, f64>> = HashMap::new();

    for &size in matrix_sizes.iter() {
        println!("Measuring performance for matrix size {}x{}...", size, size);
        let performance = measure_performance(size);
        performance_dict.insert(size, performance);
    }

    for (size, metrics) in performance_dict.iter() {
        println!("Matrix Size: {}x{}", size, size);
        for (metric, value) in metrics {
            println!("{}: {}", metric, value);
        }
    }
}
