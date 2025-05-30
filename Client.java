import java.util.ArrayList;

public class Client {

    static ArrayList<ArrayList<Integer>> A = arrayToList(new int[][] {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1},
        {0, 0, 0, 4, 0, 0, 0, 0, 1, 1, 1},
        {0, 0, 0, 0, 0, 0, 8, 0, 1, 0, 1},
        {0, 0, 7, 0, 0, 1, 1, 1, 1, 0, 1},
        {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1}
    });

    public static void main(String[] args) {
        ArrayList<String> path = findPath(A);
        printPath(A, path);
    }

    public static ArrayList<String> findPath(ArrayList<ArrayList<Integer>> map) {
        ArrayList<String> path = new ArrayList<>();
        boolean[][] visited = new boolean[map.size()][map.get(0).size()];
        dfs(map, 0, map.get(0).size() - 1, visited, path);
        return path;
    }

    public static boolean dfs(ArrayList<ArrayList<Integer>> map, int r, int c, boolean[][] visited, ArrayList<String> path) {
        if (r < 0 || c < 0 || r >= map.size() || c >= map.get(0).size()
            || visited[r][c] || map.get(r).get(c) != 1) return false;

        visited[r][c] = true;
        path.add("[" + r + "][" + c + "]");

        if (r == map.size() - 1 && c == 0) return true; // Reached bottom-left

        int[] dr = {-1, 1, 0, 0}; // up, down, left, right
        int[] dc = {0, 0, -1, 1};

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (dfs(map, nr, nc, visited, path)) return true;
        }

        path.remove(path.size() - 1); // backtrack
        return false;
    }

    public static void printPath(ArrayList<ArrayList<Integer>> map, ArrayList<String> path) {
        ArrayList<ArrayList<String>> output = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            ArrayList<String> row = new ArrayList<>();
            for (int j = 0; j < map.get(0).size(); j++) {
                row.add(" ");
            }
            output.add(row);
        }

        for (String coord : path) {
            int r = Integer.parseInt(coord.split("\\[")[1].split("\\]")[0]);
            int c = Integer.parseInt(coord.split("\\[")[2].split("\\]")[0]);
            output.get(r).set(c, "1");
        }

        for (ArrayList<String> row : output) {
            System.out.print("[ ");
            for (String val : row) {
                System.out.print(val + " , ");
            }
            System.out.println("]");
        }
    }

    public static ArrayList<ArrayList<Integer>> arrayToList(int[][] array) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int[] row : array) {
            ArrayList<Integer> listRow = new ArrayList<>();
            for (int val : row) {
                listRow.add(val);
            }
            list.add(listRow);
        }
        return list;
    }
}