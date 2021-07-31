package week1.problem1;

import java.io.*;
import java.util.StringTokenizer;


public class Main {


    /************************ SOLUTION STARTS HERE ***********************/

    static int compare(long arr[], long proposedS, long K) {

        int pos =  1;
        for (; pos <= arr.length && (proposedS * pos) / K == arr[pos - 1]; pos++)
            ;
        return pos == arr.length + 1 ? 0 :
                Long.compare(arr[pos - 1], (proposedS * pos) / K);
    }


    private static void solve(FastScanner scan, PrintWriter out) {

        int T = scan.nextInt();
        while (T-->0) {

            int N = scan.nextInt();
            long K = scan.nextLong();
            long[] arr = scan.nextLongArray(N);

            long l = arr[0] * K;
            long r = l + K;

            // First find a single candidate

            long mid = l + ((r - l) >> 1);

            for (int cmp = compare(arr, mid, K); cmp != 0; cmp = compare(arr, mid, K)) {

                if (cmp < 0) // we are over shooting, hence we have to reduce it
                    r = mid - 1;
                else
                    l = mid + 1;

                mid = l + ((r - l) >> 1);

            }

            long candidate = mid;

            // Now find the left most possible by binary searching on the left side

            l = arr[0] * K;
            r = candidate;

            while (l <= r) {
                mid = l + ((r - l) >> 1);
                if (compare(arr, mid, K) == 0)  // we are still in the possible region, so go left
                    r = mid - 1;
                else
                    l = mid + 1;
            }

            long ansL = compare(arr, mid, K) == 0 ? mid : mid + 1;

            // Now do the same for the right side

            l = candidate;
            r = arr[0] * K + K;

            while (l <= r) {
                mid = l + ((r - l) >> 1);
                if (compare(arr, mid, K) == 0)  // we are still in the possible region, so go right
                    l = mid + 1;
                else
                    r = mid - 1;
            }

            long ansR = compare(arr, mid, K) == 0 ? mid : mid - 1;

            out.println(ansL + " " + ansR);
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