use std::env; 
use std::time::Instant;

fn matrix_multiply(n: usize) {
    let mut a = vec![vec![0.0; n]; n];
    let mut b = vec![vec![0.0; n]; n];
    let mut c = vec![vec![0.0; n]; n];

    for i in 0..n {
        for j in 0..n {
            a[i][j] = rand::random();
            b[i][j] = rand::random();
        }
    }

    for i in 0..n {
        for j in 0..n {
            for k in 0..n {
                c[i][j] += a[i][k] * b[k][j];
            }
        }
    }
}

fn main() {
    let args: Vec<String> = env::args().collect();

    if args.len() != 2 {
        eprintln!("Use: {} <matrix size>", args[0]);
        std::process::exit(1);
    }

    let size: usize = match args[1].parse() {
        Ok(n) => n,
        Err(_) => {
            eprintln!("The argument must be an integer.");
            std::process::exit(1);
        }
    };

    //Uncomment these lines to measure execution time without using the perf command
    //let start = Instant::now();
    matrix_multiply(size);
    //let duration = start.elapsed(); 
    //println!("Execution time for matrices of {}x{}: {:?}", size, size, duration);
}

