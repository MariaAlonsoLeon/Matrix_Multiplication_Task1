package org.example.Algorithms;

/*import org.tensorflow.*;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.types.TFloat32;

public class TensorFlowMatrixMultiplication implements MatrixMultiplication {

    @Override
    public void multiply(double[][] A, double[][] B, double[][] C, int N) {
        try (Graph graph = new Graph()) {
            try (Session session = new Session(graph)) {

                try (Tensor<TFloat32> tensorA = convertToTensor(A, N);
                     Tensor<TFloat32> tensorB = convertToTensor(B, N)) {

                    Operation matMulOp;
                    try (GraphBuilder gb = new GraphBuilder(graph)) {
                        Output<TFloat32> a = gb.constant("A", tensorA);
                        Output<TFloat32> b = gb.constant("B", tensorB);
                        matMulOp = gb.matmul(a, b).op();
                    }

                    try (Tensor<?> result = session.runner().fetch(matMulOp).run().get(0)) {
                        convertTensorToMatrix(result, C, N); // Almacenar el resultado en la matriz C
                    }
                }
            }
        }
    }

    private Tensor<TFloat32> convertToTensor(double[][] matrix, int N) {
        TFloat32 tensor = TFloat32.tensorOf(Shape.of(N, N));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tensor.setFloat((float) matrix[i][j], i, j);
            }
        }
        return tensor;
    }

    private void convertTensorToMatrix(Tensor<?> tensor, double[][] matrix, int N) {
        TFloat32 result = (TFloat32) tensor;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = result.getFloat(i, j);
            }
        }
    }

    static class GraphBuilder implements AutoCloseable {
        private final Graph graph;

        public GraphBuilder(Graph graph) {
            this.graph = graph;
        }

        public Output<TFloat32> constant(String name, Tensor<TFloat32> tensor) {
            return graph.opBuilder("Const", name)
                    .setAttr("dtype", tensor.dataType())
                    .setAttr("value", tensor)
                    .build()
                    .output(0);
        }

        public Output<TFloat32> matmul(Output<TFloat32> a, Output<TFloat32> b) {
            return graph.opBuilder("MatMul", "matmul")
                    .addInput(a)
                    .addInput(b)
                    .setAttr("transpose_a", false)
                    .setAttr("transpose_b", false)
                    .build()
                    .output(0);
        }

        @Override
        public void close() {
        }
    }
}*/
