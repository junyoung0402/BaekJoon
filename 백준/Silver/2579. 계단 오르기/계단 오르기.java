import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.math.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int stepcount = sc.nextInt();
    static List<Integer> dp = new ArrayList<>();
    static int[] check= new int[stepcount];
    public static void main(String[] args) {
        int step[] = new int[stepcount+1];
        step[0] = 0;
        for(int i=1;i<=stepcount;i++){
            step[i] = sc.nextInt();
        }
        dp.add(step[0]);
        for(int i=1;i<=stepcount;i++) {
            dp.add(compare(step[i],step[i-1]));
        }
        System.out.print(dp.get(dp.size()-1));
    }
    public static int compare(int value, int preValue) {
        int len=0;
        int max =0;
        if(dp.size() == 1){
            return dp.get(0) + value;
        }
        if(dp.size() == 2){
            return dp.get(1) + value;
        }
        else
            max = Math.max(
                dp.get(dp.size()-2),
                dp.get(dp.size()-3) + preValue
        );
        return max + value;
    }
}
