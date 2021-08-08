import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Mod01ReverseSort {

    public static void main (String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(
                                    new InputStreamReader(System.in));

        for (int T = Integer.parseInt(reader.readLine()), tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(reader.readLine());

            List<Integer> arr = Arrays.stream(reader.readLine().split(" "))
                                    .map(Integer::parseInt).collect(Collectors.toList());

            System.out.println(
                String.format("Case #%d: ", tc) +
                IntStream.range(0, N - 1).map(i -> {
                    int j = IntStream.range(i, N)
                            .reduce(i, (x, y) -> arr.get(x) < arr.get(y)  ? x : y);
                    Collections.reverse(arr.subList(i, j + 1));
                    return j - i + 1;
                }).sum()
            );

        }
    }

}