// class Solution {
//     int n,m;
//     public int countIslands(int[][] grid, int k) {
//         m = grid.length;
//         n = grid[0].length;
//         int count  = 0;
//         for(int i = 0;i<m;i++){
//             for(int j =0;j<n;j++){
//                 if(grid[i][j] > 0){
//                     int sum = dfs(grid,i,j);
//                     if(sum % k == 0)
//                     count++;
//                 }
//             }
//         }
//         return count;
//     }
//     int dfs(int[][] grid, int i, int j){
//         Stack<int[]> st = new Stack<>();
//         st.push(new int[]{i,j});
//         long sum = 0;
//         while(!st.isEmpty()){
//             int[] a = st.pop();
//             int r = a[0], c = a[1];
        
//        if (r < 0 || c < 0 || r >= m || c >= n ||
//                 grid[r][c] == 0)
//                 continue;

//             sum += grid[r][c];
//             grid[r][c] = 0;

//             st.push(new int[]{r + 1, c});
//             st.push(new int[]{r - 1, c});
//             st.push(new int[]{r, c + 1});
//             st.push(new int[]{r, c - 1});
//         }
//         return sum;
//     }
// }

import java.util.*;

class Solution {
    int n, m;

    public int countIslands(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long sum = dfs(grid, i, j);
                    if (sum % k == 0)
                        count++;
                }
            }
        }
        return count;
    }

    long dfs(int[][] grid, int i, int j) {
        Stack<int[]> st = new Stack<>();
        st.push(new int[]{i, j});
        long sum = 0;

        while (!st.isEmpty()) {
            int[] a = st.pop();
            int r = a[0], c = a[1];

            if (r < 0 || c < 0 || r >= m || c >= n ||
                grid[r][c] == 0)
                continue;

            sum += grid[r][c];
            grid[r][c] = 0;

            st.push(new int[]{r + 1, c});
            st.push(new int[]{r - 1, c});
            st.push(new int[]{r, c + 1});
            st.push(new int[]{r, c - 1});
        }
        return sum;
    }
}
