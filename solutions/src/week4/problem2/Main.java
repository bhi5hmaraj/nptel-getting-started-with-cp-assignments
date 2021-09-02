package week4.problem2;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {


    /************************ SOLUTION STARTS HERE ***********************/


    static class DSU {
        private int weight[];
        private int parent[];
        private int size[];
        private int cnt;

        DSU(int length, int[] w) {
            this.cnt = length;
            weight = w;
            parent = new int[length + 10];
            size = new int[length + 10];
            Arrays.fill(size, 1);
            for (int i = 0; i < parent.length; i++)
                parent[i] = i;
        }

        int root(int p) {
            while(p != parent[p]) p = parent[p];
            return p;
        }

        int sizeOf(int p) {
            return size[root(p)];
        }

        boolean connected(int u, int v) {
            return root(u) == root(v);
        }

        int components() {
            return cnt;
        }

        int changeWeight(int u, int delta) {
            return  weight[root(u)] += delta;
        }


        void union(int u, int v) {
            if (!connected(u, v)) {
                cnt--;
                int rootU = root(u);
                int rootV = root(v);
                if (size[rootU] < size[rootV]) {
                    parent[rootU] = rootV;
                    size[rootV] += size[rootU];
                    weight[rootV] += weight[rootU];

                } else {
                    parent[rootV] = rootU;
                    size[rootU] += size[rootV];
                    weight[rootU] += weight[rootV];
                }
            }

        }
    }

    @SuppressWarnings("unchecked")
    private static void solve(FastScanner scan, PrintWriter out) {

        int N = scan.nextInt();
        int M = scan.nextInt();
        int Q = scan.nextInt();

        int[] A = scan.nextIntArrayOneBased(N);
        int[][] edges = new int[M + 1][];
        for (int i = 1; i <= M; i++)
            edges[i] = scan.nextIntArray(2);

        boolean[] deleted = new boolean[M + 1];
        boolean[] isDeleteQuery = new boolean[Q];
        Stack<Integer>[] weightUpdates = (Stack<Integer>[]) new Stack[N + 1];
        Stack<Integer> edgeDeletions = new Stack<>();
        Stack<Integer> weightUpdIndices = new Stack<>();

        for (int i = 1; i <= N; i++) {
            weightUpdates[i] = new Stack<>();
            weightUpdates[i].push(A[i]);
        }

        for (int i = 0; i < Q; i++) {
            switch (scan.nextInt()) {
                case 1:
                    int e = scan.nextInt();
                    deleted[e] = true;
                    edgeDeletions.push(e);
                    isDeleteQuery[Q - i - 1] = true;
                    break;
                case 2:
                    int idx = scan.nextInt();
                    int x = scan.nextInt();
                    weightUpdIndices.push(idx);
                    A[idx] = x;
                    weightUpdates[idx].push(x);

                    break;
            }
        }

        DSU dsu = new DSU(N, A);
        for (int i = 1; i <= M; i++)
            if (!deleted[i]) {
                dsu.union(edges[i][0], edges[i][1]);
            }

        Stack<Integer> ans = new Stack<>();

        for (boolean isDelete : isDeleteQuery) {
            ans.push(IntStream.rangeClosed(1, N).map(i -> dsu.changeWeight(i, 0)).max().getAsInt());
            if (isDelete) {
                int edge[] = edges[edgeDeletions.pop()];
                dsu.union(edge[0], edge[1]);
            } else {
                int idx = weightUpdIndices.pop();
                int delta = -weightUpdates[idx].pop() + weightUpdates[idx].peek();
                dsu.changeWeight(idx, delta);
            }
        }

        while (Q-->0) out.println(ans.pop());
    }


    /************************ SOLUTION ENDS HERE ************************/


    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out =
                new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        solve(in, out);
        in.close();
        out.close();
    }

    static class FastScanner {
        BufferedReader reader;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            st = null;
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = reader.readLine();
                    if (line == null) {
                        return null;
                    }
                    st = new StringTokenizer(line);
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
            return st.nextToken();
        }

        String nextLine() {
            String s = null;
            try {
                s = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s;
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        char nextChar() {
            return next().charAt(0);
        }

        int[] nextIntArray(int n) {
            int[] arr = new int[n];
            int i = 0;
            while (i < n) {
                arr[i++] = nextInt();
            }
            return arr;
        }

        long[] nextLongArray(int n) {
            long[] arr = new long[n];
            int i = 0;
            while (i < n) {
                arr[i++] = nextLong();
            }
            return arr;
        }

        int[] nextIntArrayOneBased(int n) {
            int[] arr = new int[n + 1];
            int i = 1;
            while (i <= n) {
                arr[i++] = nextInt();
            }
            return arr;
        }

        long[] nextLongArrayOneBased(int n) {
            long[] arr = new long[n + 1];
            int i = 1;
            while (i <= n) {
                arr[i++] = nextLong();
            }
            return arr;
        }

        void close() {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /************************ TEMPLATE ENDS HERE ************************/
}