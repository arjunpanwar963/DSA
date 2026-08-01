class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for(int i= n - 1; i >= 0; i--){
            ans[i] = nums[i];
            for(int j = i+ 1; j < n ; j++){
                ans[j] = Math.max(nums[i] - ans[j], nums[j] - ans[j-1]);
            }
        }
        return ans[n-1] >= 0;
    }
}