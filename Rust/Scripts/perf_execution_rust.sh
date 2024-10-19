#!/bin/bash

sizes=(64 128 256 512 1024 2048)

executable="./target/release/matrix_multiplication"

if [ -f "$executable" ]; then
    echo "Removing the existing executable..."
    rm -f "$executable"
fi

echo "Compiling the project..."
cargo build --release

for n in "${sizes[@]}"; do
    echo "Executing perf for n = $n"

    # It is possible to change the options of perf to obtain different data
    sudo perf stat "$executable" $n 2> perf_matrix_$n.txt
done

