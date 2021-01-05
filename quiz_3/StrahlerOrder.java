package quiz3;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StrahlerOrder {
    static int n,m;
    static int[][] g;
    static int[] used;
    static int[] order;
    public static void main(String[] args) throws FileNotFoundException {
        // URL path = StrahlerOrder.class.getResource("StrahlerOrder.txt");
        // File f = new File(path.getFile());
        // try(Scanner sc = new Scanner(f)){
        try(Scanner sc = new Scanner(System.in)){
            int t = sc.nextInt();

            while(t > 0) {
                int k = sc.nextInt();
                n = sc.nextInt();
                m = sc.nextInt();

                used = new int[n+1];
                order = new int[n+1];
                Arrays.fill(order, -1);

                g = new int[n+1][n+1];

                for(int i=0;i<m;i++) {
                    int a = sc.nextInt();
                    int b = sc.nextInt();
                    g[b][a] = 1;
                }

                dfs(n, -1);

                int ord = order[n];

                System.out.println(t + " " + ord);
                
                t--;
            }
        }
    }

    public static void dfs(int v, int parent) {
        used[v] = 1;
        // HashMap<Integer,Integer> map = new HashMap<>();
        if(v == 5) {
            int h = 1;
        }

        int numChildren = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=1;i<=n;i++) {
            if(g[v][i] == 1) {
                numChildren++;
                if(used[i] == 0) {
                    dfs(i,v);

                }
                int ord = order[i];
                map.putIfAbsent(ord, 0);
                int count = map.get(ord);
                count++;
                map.put(ord, count);
            }
        }

        if(numChildren > 0) {
            int maxOrder = -100;
            for(Map.Entry e : map.entrySet()) {
                maxOrder = Math.max(maxOrder, (int)e.getKey());

                if(map.get(maxOrder) < 2) {
                    order[v] = maxOrder;
                } else {
                    order[v] = maxOrder + 1;
                }
            }
        } else {
            order[v] = 1;
        }
    }
}
