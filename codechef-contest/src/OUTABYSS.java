import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

class OUTABYSS {


    /************************ SOLUTION STARTS HERE ***********************/


    private static void solve(FastScanner scan, PrintWriter out) {

        int T = scan.nextInt();
        while(T-->0) {
            int N = scan.nextInt();
            int M = scan.nextInt();
            int K = scan.nextInt();
            ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[N + 1];
            IntStream.rangeClosed(1, N).forEach(i -> adj[i] = new ArrayList<>());

            while (M-->0) {
                int e[] = scan.nextIntArray(2);
                adj[e[0]].add(e[1]);
                adj[e[1]].add(e[0]);
            }

            int special[] = scan.nextIntArray(K);
            int dist[] = new int[N + 1];
            boolean marked[] = new boolean[N + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            Arrays.stream(special).forEach(spl -> {
                queue.add(spl);
                dist[spl] = 0;
                marked[spl] = true;
            });

            while (!queue.isEmpty()) {
                int curr = queue.remove();
                for (int v : adj[curr])
                    if (!marked[v]) {
                        queue.add(v);
                        dist[v] = dist[curr] + 1;
                        marked[v] = true;
                    }
            }

            int Q = scan.nextInt();
            while (Q-->0) {
                int start = scan.nextInt();
                out.println(!marked[start] ? -1 : dist[start]);
            }
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