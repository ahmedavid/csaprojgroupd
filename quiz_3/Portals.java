package quiz3;

import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Vertex {
    private int id;
    private int cost;

    public Vertex(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }
}

class ConnectedComponent implements Comparable<ConnectedComponent>{
    static final int MAX = 1000001;
    private int id;
    private int lowestCost = MAX;
    private int lowestCostId = -1;

    public ConnectedComponent(int id) {
        this.id = id;
    }

    public void addVertex(Vertex v) {
        if(v.getCost() < lowestCost) {
            lowestCost = v.getCost();
            lowestCostId = v.getId();
        }
    }

    public int getLowestCost() {
        return lowestCost;
    }

    @Override
    public int compareTo(ConnectedComponent c) {
        return lowestCost - c.lowestCost;
    }
}

public class Portals {
    static final int MAX = 1000001;
    static int[][] g;
    static int[] used, cost;
    static int n,m,c;
    static ArrayList<ConnectedComponent> comps = new ArrayList<>();
    public static void main(String[] args) throws FileNotFoundException {
        // URL path = Portals.class.getResource("Portals3.txt");
        // File f = new File(path.getFile());
        // try(Scanner sc = new Scanner(f)){
        // try(Scanner sc = new Scanner(System.in)){
            FastScanner sc = new FastScanner(System.in);
            n = sc.nextInt();
            m = sc.nextInt();

            used = new int[n+1];
            cost = new int[n+1];
            g = new int[n+1][n+1];

            for(int i=1;i<=n;i++) {
                cost[i] = sc.nextInt();
            }

            for(int i=0;i<m;i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                g[a][b] = 1;
                g[b][a] = 1;
            }

            c = 0;
            for(int i=1;i<=n;i++) {
                if(used[i] == 0) {
                    comps.add(new ConnectedComponent(c));
                    dfs(i);
                    c++;
                }
            }

            Collections.sort(comps);

            int totalCost = 0;
            if(c > 1) {
                for(int i=1;i<c;i++) {
                    totalCost += comps.get(0).getLowestCost() + comps.get(i).getLowestCost();
                }
            } else {
                totalCost += comps.get(0).getLowestCost();
            }



            System.out.println(totalCost);
        }
    // }

    public static void dfs(int v) {
        used[v] = 1;
        for(int i=1;i<=n;i++) {
            comps.get(c).addVertex(new Vertex(v, cost[v]));
            if(g[v][i] == 1 && used[i] == 0) {
                comps.get(c).addVertex(new Vertex(i, cost[i]));
                dfs(i);
            }
        }
    }
}

class FastScanner
{
  BufferedReader br;
  StringTokenizer st;
 
  public FastScanner(InputStream inputStream)
  {
    br = new BufferedReader(new InputStreamReader(inputStream));
    st = new StringTokenizer("");
  }
 
  public String next()
  {
    while (!st.hasMoreTokens())
    {
      try
      {
        st = new StringTokenizer(br.readLine());
      } catch (Exception e)
      {
        return null;
      }
    }
    return st.nextToken();
  }
 
  public int nextInt()
  {
    return Integer.parseInt(next());
  }
}
