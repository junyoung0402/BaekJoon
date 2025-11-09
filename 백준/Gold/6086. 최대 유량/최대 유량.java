import java.util.*;

class Main{
    static Scanner sc = new Scanner(System.in);
    static int num = sc.nextInt();
    static HashMap<Character,Integer> saveindex= new HashMap<Character,Integer>(53);
    static int[][] graph = new int[52][52];
    public static void main(String[] args) {
        setSaveindex();
        for(int i=0;i<num;i++) {
            String c1 = sc.next();
            String c2 = sc.next();
            int value = sc.nextInt();

            graph[saveindex.get(c1.charAt(0))][saveindex.get(c2.charAt(0))] += value;
            graph[saveindex.get(c2.charAt(0))][saveindex.get(c1.charAt(0))] += value;
        }
        System.out.print(maxflow(saveindex.get('A'),saveindex.get('Z')));
    }

    public static int maxflow(int source, int sink){
        int[][] flow = new int[52][52];
        int maxflow = 0;
        int[] parent = new int[52];
        while(bfs(source, sink, parent, flow)){
            int pathflow = Integer.MAX_VALUE;
            int v = sink;
            int u = parent[v];
            while(v != source){
                pathflow = Math.min(pathflow, graph[u][v] - flow[u][v]);
                v = u;
                u = parent[v];
            }
            v = sink;
            u = parent[v];
            while (v != source){
                flow[u][v] = flow[u][v] + pathflow;
                flow[v][u] =  flow[v][u] - pathflow;
                v = u;
                u = parent[v];
            }
            maxflow = maxflow + pathflow;
        }
        return maxflow;
    }

    public static boolean bfs(int source, int sink, int[] parent, int[][] flow) {
        boolean[] visited = new boolean[52];
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(parent, -1);
        queue.add(source);
        visited[source] = true;
        while(!queue.isEmpty()){
            int u = queue.poll();
            for(int v=0;v<52;v++){
                if(!visited[v] && graph[u][v] - flow[u][v] > 0){
                    parent[v] = u;
                    visited[v] = true;
                    if(v == sink)
                        return true;
                    queue.add(v);
                }
            }
        }
        return false;
    }

    public static void setSaveindex() {
        char alp = 'a';
        int i=0;
        while(alp <= 'z'){
            saveindex.put(alp++,i++);
        }
        alp = 'A';
        while(alp <='Z'){
            saveindex.put(alp++, i++);
        }
    }
}
