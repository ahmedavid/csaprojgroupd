package quiz3;

import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Tram {
    static final int INF = Integer.MAX_VALUE;
    static final int MAX = 101;
    static int n, a,b;
    static ArrayList<int[]>[] g;
    static int[] dist;
    public static void main(String[] args) throws FileNotFoundException {
        // URL path = Tram.class.getResource("Tram.txt");
        // File f = new File(path.getFile());
        // try(Scanner sc = new Scanner(f)){
        FastScanner sc = new FastScanner(System.in);
            n = sc.nextInt();
            a = sc.nextInt();
            b = sc.nextInt();

            g = new ArrayList[n+1];
            for(int i = 0; i <= n; i++)
                g[i] = new ArrayList<int[]>();
            dist = new int[n+1];


            for(int i=1;i<=n;i++){
                int m = sc.nextInt();
                for(int j=1;j<=m;j++){
                    int k = sc.nextInt();
                    int w = j == 1 ? 0 : 1;
                    int[] arr = {k,w};
                    g[i].add(arr);
                }
            }

            bfs(a);

            if(dist[b] == INF) System.out.println(-1);

            else System.out.println(dist[b]);
        
    }

    public static void bfs(int start) {
        Arrays.fill(dist, INF);
        dist[start] = 0;

        Deque<Integer> q = new LinkedList<>();
        q.add(start);

        while(!q.isEmpty()) {
            int v = q.pollFirst();
            for(int i=0;i<g[v].size();i++) {
                int to = g[v].get(i)[0];
                int w = g[v].get(i)[1];
                if(dist[to] > dist[v] + w) {
                    dist[to] = dist[v] + w;
                    if(w == 1) q.addLast(to);
                    else q.addFirst(to);
                }
            }
        }
    }
}


// class FastScanner
// {
//   BufferedReader br;
//   StringTokenizer st;
 
//   public FastScanner(InputStream inputStream)
//   {
//     br = new BufferedReader(new InputStreamReader(inputStream));
//     st = new StringTokenizer("");
//   }
 
//   public String next()
//   {
//     while (!st.hasMoreTokens())
//     {
//       try
//       {
//         st = new StringTokenizer(br.readLine());
//       } catch (Exception e)
//       {
//         return null;
//       }
//     }
//     return st.nextToken();
//   }
 
//   public int nextInt()
//   {
//     return Integer.parseInt(next());
//   }
// }