package entity;
import java.io.*;

import static java.lang.Math.max;

public class tinkoff {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String []first=reader.readLine().split(" ");
        String []second=reader.readLine().split(" ");
        int y_difference = max(Integer.parseInt(first[3]) - Integer.parseInt(second[1]),
        (Integer.parseInt(second[3]) - Integer.parseInt(first[1])));
        int x_difference = max(Integer.parseInt(first[2]) - Integer.parseInt(second[0]),
                (Integer.parseInt(second[2]) - Integer.parseInt(first[0])));
        int a = max(y_difference, x_difference);
        System.out.println(a * a);
    }
}
