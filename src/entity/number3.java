package entity;

import java.util.Scanner;

public class number3 {
    public static void main(String[] args) {
        int max = 0;

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
            if (i % 2 == 1)
                numbers[i] *= -1;
        }
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += numbers[i];
        System.out.println(sum);
        for (int i = 0; i < n - 1; i++){
            int x = numbers[i] + numbers[i+1];
            if (max < Math.abs(x) && x < 0){
                max = Math.abs(x);

            }
        }
        System.out.println(sum + max*2);
    }
}
