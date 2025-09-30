import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] music = new int[M + 1];

        for (int i = 0; i <= M; i++) {
            music[i] = -1;
        }

        music[S] = 0;

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            int volumn = Integer.parseInt(st.nextToken());

            List<Integer> list = new ArrayList<>();

            for (int j = 0; j <= M; j++) {
                if (music[j] == (i - 1)) {
                    int plus = j + volumn;
                    int minus = j - volumn;

                    if (0 <= plus && plus <= M) {
                        list.add(plus);
                    }
                    if (0 <= minus && minus <= M) {
                        list.add(minus);
                    }
                }
            }

            for (int j = 0; j < list.size(); j++) {
                music[list.get(j)] = i;
            }
        }

        int max = -1;

        for (int i = 0; i <= M; i++) {
            if (music[i] == N) {
                max = Math.max(max, i);
            }
        }

        System.out.println(max);
    }
}