#!/bin/bash

sizes=(64 128 256 512 1024 2048)

for n in "${sizes[@]}"; do
    echo "Ejecutando perf para n = $n"
    
    # It is possible to change the options of perf to obtain different data
    sudo perf stat python3 matrix_multiplication.py $n 2> perf_matrix_$n.txt
    
done
