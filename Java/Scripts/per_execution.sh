#!/bin/bash

sizes=(64 128 256 512 1024 2048)

javac Main.java

for n in "${sizes[@]}"; do
    echo "Executing perf for n = $n"
   
    # It is possible to change the options of perf to obtain different data
    sudo perf stat java Main $n 2> perf_matrix_$n.txt
done