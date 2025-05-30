import java.util.ArrayList;

public class Client {

    // Sample maps converted to ArrayLists
    static ArrayList<ArrayList<Integer>> A = arrayToList(new int[][] {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1},
        {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 1},
        {0, 0, 7, 0, 0, 0, 0, 3, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    });

    static ArrayList<ArrayList<Integer>> B = arrayToList(new int[][] {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {0, 1, 0, 0, 0, 2, 0, 0, 0, 1, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
        {4, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0},
        {0, 0, 3, 0, 0, 0, 1, 1, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0}
    });

    public static void main(String[] args) {
        ArrayList<String> pathA = findPath(A);
        System.out.println(pathA);
        printPath(A, pathA);

        ArrayList<String> pathB = findPath(B);
        System.out.println(pathB);
        printPath(B, pathB);
    }

    public static ArrayList<String> findPath(ArrayList<ArrayList<Integer>> map) {
        ArrayList<String> path = new ArrayList<>();
        boolean[][] visited = new boolean[map.size()][map.get(0).size()];

        // Find start point
        outer:
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i).get(0) == 1) {
                dfs(map, i, 0, visited, path);
                break outer;
            }
            if (map.get(i).get(map.get(0).size() - 1) == 1) {
                dfs(map, i, map.get(0).size() - 1, visited, path);
                break outer;
            }
        }
        for (int j = 0; j < map.get(0).size(); j++) {
            if (map.get(0).get(j) == 1) {
                dfs(map, 0, j, visited, path);
                break;
            }
            if (map.get(map.size() - 1).get(j) == 1) {
                dfs(map, map.size() - 1, j, visited, path);
                break;
            }
        }

        return path;
    }

    public static boolean dfs(ArrayList<ArrayList<Integer>> map, int r, int c, boolean[][] visited, ArrayList<String> path) {
        if (r < 0 || c < 0 || r >= map.size() || c >= map.get(0).size() || visited[r][c] || map.get(r).get(c) != 1)
            return false;

        visited[r][c] = true;
        path.add("[" + r + "][" + c + "]");

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (dfs(map, nr, nc, visited, path)) return true;
        }

        return true;
    }

    public static void printPath(ArrayList<ArrayList<Integer>> map, ArrayList<String> path) {
        ArrayList<ArrayList<String>> output = new ArrayList<>();

        // Initialize output with empty strings
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
            for (String cell : row) {
                System.out.print(cell + " , ");
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