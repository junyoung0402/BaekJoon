import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static int n = sc.nextInt();
    public static int[][] arr = new int[n][n];
    public static StringBuilder result = new StringBuilder("");

    public static void main(String[] args) {

        for(int i=0; i<n; i++){
            String row = sc.next();
            for (int j=0; j<n; j++){
                arr[i][j] = row.charAt(j) - '0';
            }
        }

        QuadTree(0, 0, n);

        System.out.println(result);
    }

    public static void QuadTree(int x, int y, int size) {

        if(check(x, y, size)) {
            result.append(arr[x][y]);
            return;
        }

        int newSize = size / 2;

        result.append('(');

        QuadTree(x, y, newSize);
        QuadTree(x, y + newSize, newSize);
        QuadTree(x + newSize, y, newSize);
        QuadTree(x + newSize, y + newSize, newSize);
        result.append(')');
    }

    public static boolean check(int x, int y, int size) {
        for(int i=x;i<x+size;i++){
            for (int j=y;j<y+size;j++){
                if(arr[x][y] != arr[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}