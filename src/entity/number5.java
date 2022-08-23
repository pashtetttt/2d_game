package entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class number5 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String []nq=reader.readLine().split(" ");
        int n = Integer.parseInt(nq[0]);
        int q = Integer.parseInt(nq[1]);
        int[] answers = new int[q];
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = sc.nextLine();

        }
        for (int i = 0; i < q; i++){
            String []req=reader.readLine().split(" ");
            int k = Integer.parseInt(req[0]);
            String str = req[1];
            int count = 0;
            while (count != n){
                if (words[count].startsWith(str)){
                    k--;
                }
                count++;
                if (k == 0){
                    answers[i] = count;
                    break;
                }

            }
            if (k != 0)
                answers[i] = -1;
        }
        for (int j: answers)
            System.out.println(j);
    }
}
