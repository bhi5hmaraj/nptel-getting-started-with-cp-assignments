package week2.problem1;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {


    /************************ SOLUTION STARTS HERE ***********************/

    static class Pair implements  Comparable<Pair> {
        int position, value;
        Pair (int pos, int val) {
            position = pos;
            value = val;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(value, o.value);
        }
    }

    private static void solve(FastScanner scan, PrintWriter out) {

        int N = scan.nextInt();
        int K = scan.nextInt();

        int[] arr = scan.nextIntArray(N);

        List<Pair> enumeration = IntStream.range(0, N)
                                    .mapToObj(i -> new Pair(i, arr[i]))
                                    .collect(Collectors.toList());

        Collections.sort(enumeration);

        int cnt = 0;
        for (int i = 0; i < N; ) {
            int maxCover = enumeration.get(i).position + K;
            cnt++;
            i++;

            while (i  < N &&
                    enumeration.get(i).value == enumeration.get(i - 1).value) {
                if (enumeration.get(i).position >= maxCover) {
                    cnt++;
                    maxCover = enumeration.get(i).position + K;
                }
                i++;
            }
        }

        out.println(cnt);
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