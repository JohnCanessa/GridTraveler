import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;


/**
 * 
 */
public class GridTraveler {


    /**
     * Say you are a traveler on a 2D grid.
     * You begin in the top-left corner
     * and your goal is to travelto the bottom-right corner.
     * You may only move down and right.
     *
     * In how many ways can you travel to the goal on a grid with dimensions m * n?
     * 
     * Without memoization:
     * Time: O(2^m+n)  Space: O(m + n)
     * 
     * With memoization:
     * Time:  Space: O(m + n)
     * 
     * Runtime: 9 ms, faster than 5.36% of Java online submissions.
     * Memory Usage: 38.6 MB, less than 5.05% of Java online submissions.
     */
    static int gridTraveler(int m, int n) {

        // **** initialization ****
        int ans                         = 0;
        int[] callCounter               = new int[1];
        HashMap<String, Integer> memo   = new HashMap<>();

        // **** recursive call ****
        ans = gridTraveler(m, n, memo, callCounter);

        // ???? ????
        // System.out.println("<<< memo: " + memo.toString());
        // System.out.println("<<< callCounter: " + callCounter[0]);

        // **** return answer ****
        return ans;
    }


    /**
     * Say you are a traveler on a 2D grid.
     * You begin in the top-left corner
     * and your goal is to travelto the bottom-right corner.
     * You may only move down and right.
     *
     * In how many ways can you travel to the goal on a grid with dimensions m * n?
     * 
     * Recursive call.
     */
    static int gridTraveler(int m, int n, HashMap<String, Integer> memo, int[] callCounter) {

        // ???? increment call counter ????
        callCounter[0]++;

        // **** base case(s) ****
        if (m == 1 && n == 1) return 1;
        if (m == 0 || n == 0) return 0;

        // **** generate key ****
        String key = "" + (m - 1) + "," + n;

        // **** generate value for key ****
        if (!memo.containsKey(key))
            memo.put(key, gridTraveler(m - 1, n, memo, callCounter) + gridTraveler(m, n - 1, memo, callCounter));

        // **** return value ****
        return memo.get(key);
    } 


    /**
     * A robot is located at the top-left corner of a m x n grid.
     * The robot can only move either down or right at any point in time.
     * The robot is trying to reach the bottom-right corner of the grid.
     * How many possible unique paths are there?
     * 
     * No recursion using memoization.
     * 
     * Time:  O(m * n)  Space: O(m * n)
     * 
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 35.7 MB, less than 67.17% of Java online submissions.
     */
    static int gridTravelerNRM(int m, int n) {

        // **** sanity check(s) ****
        if (m == 1 && n == 1) return 1;
        if (m == 0 || n == 0) return 0;

        // **** memoization ****
        int[][] memo = new int[m][n];

        // **** loop (no recursion) ****
        for (int i = 0;  i < m; i++) {

            for (int j = 0; j < n; j++) {

                // **** save distance to target ****
                if (i == 0 || j == 0)       // top row or left column
                    memo[i][j] = 1;
                else
                    memo[i][j] = memo[i - 1][j] + memo[i][j - 1];
            }
        }

        // ???? ????
        System.out.println("<<< memo: ");
        for (int k = 0; k < m; k++)
            System.out.println(Arrays.toString(memo[k]));       

        // **** return answer ****
        return memo[m - 1][n - 1];
    }


    /**
     * Test scaffold
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read m and n ****
        int[] mn = Arrays.stream(br.readLine().trim().split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();

        // **** close buffered reader ****
        br.close();

        // **** extract m and n ****
        int m = mn[0];
        int n = mn[1];
        
        // ???? ????
        System.out.println("main <<< m: " + m + " n: " + n);

        // **** generate and display answer ****
        System.out.println("main <<< ans: " + gridTraveler(m, n));

        // **** generate and display answer ****
        System.out.println("main <<< ans: " + gridTravelerNRM(m, n));
    }
}