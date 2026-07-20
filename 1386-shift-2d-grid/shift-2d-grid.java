class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int tot = m*n;
        k %= tot;
        int[][] shift = new int[m][n];
        for(int i = 0; i < m ;i++){
            for(int j = 0;j<n;j++){
                int newindex = (i * n + j + k) % tot;
                shift[newindex/n][newindex % n] = grid[i][j];
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for(int[] row : shift){
            List<Integer> list = new ArrayList<>();
            for(int val : row){
                list.add(val);
            }
            ans.add(list);
        }
        return ans;
    }
}