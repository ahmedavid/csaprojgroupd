package quiz3;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class MagneticCushions {
    static int n,m,time;
    static ArrayList<Integer>[] g;
    static int[] used,d,up;
    static TreeSet<Integer> ArtPoints = new TreeSet<Integer>();
    public static void main(String[] args) throws FileNotFoundException {
        // URL path = MagneticCushions.class.getResource("MagneticCushions.txt");
        // File f = new File(path.getFile());
        // try(Scanner sc = new Scanner(f)){
        try(Scanner sc = new Scanner(System.in)){
            n = sc.nextInt();
            m = sc.nextInt();

            int len = n+m+1;
            g = new ArrayList[len];
            for(int i = 1; i < len; i++)
              g[i] = new ArrayList<Integer>();

            used = new int[len];
            d = new int[len];
            up = new int[len];

            for(int i=1;i<=m;i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                int c = sc.nextInt();

                g[n+i].add(a);
                g[a].add(n+i);

                g[n+i].add(b);
                g[b].add(n+i);

                g[n+i].add(c);
                g[c].add(n+i);
            }

            time = 1;
            for(int i = 1; i <= n; i++)
              if (used[i] == 0) dfs(i,-1);

            ArrayList<Integer> numList = new ArrayList<>();
            while(!ArtPoints.isEmpty()) {
                int artPoint = ArtPoints.pollFirst();
                if(artPoint > n) {
                    numList.add(artPoint);
                }
            }

            if(numList.size() == 0) {
                System.out.println(0);
            }
            else {
                System.out.println(numList.size());
                for(int num : numList) System.out.print(num - n + " ");
                System.out.println();
            }
        }
    }

    static void dfs (int v, int p)
    {
      int i, to, children;
      used[v] = 1;
      d[v] = up[v] = time++;
      children = 0;
      for (i = 0; i < g[v].size(); i++) 
      {
        to = g[v].get(i);
        if (to == p)  continue;
        if (used[to] == 1)
          up[v] = Math.min(up[v], d[to]);
        else 
        {
          dfs (to, v);
          up[v] = Math.min(up[v], up[to]);
          if ((up[to] >= d[v]) && (p != -1)) ArtPoints.add(v);
          children++;
        }
      }
      if ((p == -1) && (children > 1)) ArtPoints.add(v);
    }
}
