package week3.problem2;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {


    /************************ SOLUTION STARTS HERE ***********************/

    // Wrong solution!
    private static void solve1(FastScanner scan, PrintWriter out) {

        int T = scan.nextInt();
        while(T-->0) {
            int N = scan.nextInt();
            TreeMap<Long, Stack<Integer>> pq = new TreeMap<>();  // L_i -> [D_js]
            HashMap<Integer, Integer> distinctCounter = new HashMap<>();    // D_i -> freq[D_i]

            for (int i = 0; i < N; i++) {
                int D = scan.nextInt();
                long L = scan.nextLong();

                distinctCounter.merge(D, 1, Integer::sum);

                pq.compute(L, (length, directors) -> {
                    directors = directors == null ? new Stack<>() : directors;
                    directors.add(D);
                    return directors;
                });
            }

            long maxEnjoyment = 0;
            while (distinctCounter.size() > 0) {
                long currMax = pq.lastKey();
                maxEnjoyment += currMax * distinctCounter.size();

                pq.compute(currMax, (k, directors) -> {
                   distinctCounter.compute(directors.pop(), (d, freq) -> freq == 1 ? null : freq - 1);
                    return directors.isEmpty() ? null : directors;
                });
                out.println("pq" + pq);
                out.println("distinct " + distinctCounter);
            }

            out.println(maxEnjoyment);
        }

    }

    private static void solve2(FastScanner scan, PrintWriter out) {

        int T = scan.nextInt();
        while (T-- > 0) {
            int N = scan.nextInt();
            long maxEnjoyment = 0;
            long sum = 0;
            HashMap<Integer, Long> storeMin = new HashMap<>();
            for (int i = 0; i < N; i++) {
                int D = scan.nextInt();
                long L = scan.nextLong();
                sum += L;
                storeMin.merge(D, L, Long::min);
            }

            List<Long> minimums = storeMin.values().stream()
                                .collect(Collectors.toCollection(ArrayList::new));

            sum -=  minimums.stream().reduce(0L, Long::sum);

            Collections.sort(minimums);

            maxEnjoyment = sum * storeMin.size() +
                            IntStream.rangeClosed(1, storeMin.size())
                                .mapToLong(i -> 1L *  i * minimums.get(i - 1))
                                .sum();

            out.println(maxEnjoyment);
        }
    }


    /************************ SOLUTION ENDS HERE ************************/


    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out =
                new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        solve2(in, out);
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