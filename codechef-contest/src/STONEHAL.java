import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

class STONEHAL {


    /************************ SOLUTION STARTS HERE ***********************/

    static class DSU {
        private int parent[];
        private int size[];
        private int cnt;

        DSU(int length) {
            this.cnt = length;
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

        void union(int u, int v) {
            if (!connected(u, v)) {
                cnt--;
                int rootU = root(u);
                int rootV = root(v);
                if (size[rootU] < size[rootV]) {
                    parent[rootU] = rootV;
                    size[rootV] += size[rootU];
                } else {
                    parent[rootV] = rootU;
                    size[rootU] += size[rootV];
                }
            }
        }
    }


    static long computeMSTKruskal(int[][] edges, int N) {
        Arrays.sort(edges, (e1, e2) -> Integer.compare(e1[2], e2[2]));
        DSU dsu = new DSU(N);
        long cost = 0;
        for (int[] edge : edges) {
            if (dsu.components() == 1)
                break;

            if (!dsu.connected(edge[0], edge[1])) {
                dsu.union(edge[0], edge[1]);
                cost += edge[2];
            }
        }

        return cost;
    }

    private static void solve(FastScanner scan, PrintWriter out) {

        int T = scan.nextInt();
        while (T-->0) {
            int N = scan.nextInt();
            int M = scan.nextInt();
            int edges[][] = new int[M][];
            IntStream.range(0, M).forEach(i -> {
                edges[i] = scan.nextIntArray(3);
                edges[i][2] = -edges[i][2];
            });

            out.println(-computeMSTKruskal(edges, N));
        }

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