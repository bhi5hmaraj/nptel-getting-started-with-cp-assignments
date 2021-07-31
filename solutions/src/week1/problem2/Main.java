package week1.problem2;

import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

public class Main {


    /************************ SOLUTION STARTS HERE ***********************/

    // Sieve of Erathanoses
    public static boolean[] isPrimeArray(int N)  {
        boolean num[] = new boolean[N + 1];
        Arrays.fill(num, true);
        num[1] = num[0] =  false;

        for (int i = 2; i * i <= N; i++)
            if (num[i])  // i is prime
                for (int j = i * i; j <= N; j += i)
                    num[j] = false;

        return num;
    }

    static int MAX_N = (int) 1e6;
    static boolean[] isPrime = isPrimeArray(MAX_N);
    static int[] primes = IntStream.rangeClosed(1, MAX_N).filter(i -> isPrime[i]).toArray();


    private static void solve(FastScanner scan
            , PrintWriter out) {

        int T = scan.nextInt();
        while (T-->0) {
            long N = scan.nextLong();
            int cnt = 0;
            for (long prime : primes) {
                long pow = prime * prime;
                for (int i = 2; pow <= N; pow *= prime, i++) {
                    cnt += isPrime[i + 1] ? 1 : 0;
                }
            }

            out.println(cnt);
        }

    }


    /************************ SOLUTION ENDS HERE ************************/


    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out =
                new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(System.out)), false);
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