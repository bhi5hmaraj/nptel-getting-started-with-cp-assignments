package week0.problem1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        while (T-->0) {
            System.out.println(scan.nextInt() + scan.nextInt());
        }
    }
}