import java.util.ArrayList;

public class Client {

    // Static input array - replace this with any test map
    static int[][] A = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1},
        {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 1},
        {0, 0, 7, 0, 0, 0, 0, 3, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    static int[][] B = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {0, 1, 0, 0, 0, 2, 0, 0, 0, 1, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
        {4, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0},
        {0, 0, 3, 0, 0, 0, 1, 1, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0}
    };

    public static void main(String[] args) {
        System.out.println(findPath(A));
        printPath(A, findPath(A));
        System.out.println(findPath(B));
        printPath(A, findPath(B));
    }

    public static ArrayList<String> findPath(int[][] map) {
        ArrayList<String> path = new ArrayList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];

        // Find starting point (first 1 on any edge)
        outer:
        for (int i = 0; i < map.length; i++) {
            if (map[i][0] == 1) {
                dfs(map, i, 0, visited, path);
                break outer;
            }
            if (map[i][map[0].length - 1] == 1) {
                dfs(map, i, map[0].length - 1, visited, path);
                break outer;
            }
        }
        for (int j = 0; j < map[0].length; j++) {
            if (map[0][j] == 1) {
                dfs(map, 0, j, visited, path);
                break;
            }
            if (map[map.length - 1][j] == 1) {
                dfs(map, map.length - 1, j, visited, path);
                break;
            }
        }

        return path;
    }

    public static boolean dfs(int[][] map, int r, int c, boolean[][] visited, ArrayList<String> path) {
        if (r < 0 || c < 0 || r >= map.length || c >= map[0].length || visited[r][c] || map[r][c] != 1)
            return false;

        visited[r][c] = true;
        path.add("A[" + r + "][" + c + "]");

        int[] dr = {-1, 1, 0, 0}; // up, down, left, right
        int[] dc = {0, 0, -1, 1};

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (dfs(map, nr, nc, visited, path)) {
                return true;
            }
        }

        // If no path found ahead, backtrack
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr >= 0 && nc >= 0 && nr < map.length && nc < map[0].length &&
                map[nr][nc] == 1 && !visited[nr][nc]) {
                if (dfs(map, nr, nc, visited, path)) return true;
            }
        }

        return true;
    }

    public static void printPath(int[][] map, ArrayList<String> path) {
        String[][] output = new String[map.length][map[0].length];

        for (String coord : path) {
            int r = Integer.parseInt(coord.split("\\[")[1].split("\\]")[0]);
            int c = Integer.parseInt(coord.split("\\[")[2].split("\\]")[0]);
            output[r][c] = "1";
        }

        for (int i = 0; i < map.length; i++) {
            System.out.print("[ ");
            for (int j = 0; j < map[0].length; j++) {
                System.out.print((output[i][j] != null ? output[i][j] : " ") + " , ");
            }
            System.out.println("]");
        }
    }
}