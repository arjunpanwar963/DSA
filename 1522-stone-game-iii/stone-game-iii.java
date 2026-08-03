class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] ans = new int[n+1];
        for(int i = n - 1; i >= 0; i--){
            ans[i] = Integer.MIN_VALUE;
            int sum =0 ;
            for(int k = 0; k < 3 && i + k < n; k++){
                sum += stoneValue[i+k];
                ans[i] = Math.max(ans[i], sum - ans[i+ k + 1]);
            }
        }
        if( ans[0] > 0) return "Alice";
        if(ans[0] < 0) return "Bob";
        return "Tie";
    }
}