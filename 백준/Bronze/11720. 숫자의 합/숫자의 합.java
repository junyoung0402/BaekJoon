import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        String num = sc.next();
        int sum = 0;
        for(int i=0; i<count; i++){
            sum = sum + Character.getNumericValue(num.charAt(i));
            
        }
        System.out.println(sum);
    }
}