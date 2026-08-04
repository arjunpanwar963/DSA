class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int min = nums[0];
        int max = nums[0];
        for(int n : nums){
            set.add(n);
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = min+1; i < max; i++){
            if(!set.contains(i))
            ans.add(i);
        }
        return ans;
    }
}