import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        long N = Long.parseLong(st.nextToken());
        long A = Long.parseLong(st.nextToken());

        //덧셈역
        long B = N - A;
        sb.append(B + " ");

        //곱셈역 (a*c와 n은 서로소)
        if (GCD(A, N) != 1) {
            sb.append("-1");
        }
        else {
            long[] result = Execute(A, N);

            while (result[0] < 0) {
                result[0] += N;
            }

            sb.append(result[0]);
        }

        System.out.print(sb);
    }

    //ax + by = c
    public static long[] Execute(long a, long b) {
        long[] xy = new long[2];

        if (b == 0) {
            xy[0] = 1; //x' = 1
            xy[1] = 0; //y' = 0
            return xy;
        }

        long q = a / b;
        long[] prev = Execute(b, a % b);
        xy[0] = prev[1]; //x = y'
        xy[1] = prev[0] - prev[1] * q; //y = x' - y' * q

        return xy;
    }

    public static long GCD(long a, long b) {
        long big = Math.max(a, b);
        long small = Math.min(a, b);
        long result = 0;

        while (true) {
            result = big % small;

            if (result == 0) {
                return small;
            }

            big = small;
            small = result;
        }
    }
}