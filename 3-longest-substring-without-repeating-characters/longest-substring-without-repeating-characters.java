class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] a = new int[256];
        int i = 0, j = 0, m = 0;
        for(j = 0; j < s.length(); j++){
            char c = s.charAt(j);
            a[c]++;
            while(a[c] > 1){
                char l = s.charAt(i);
                a[l]--;
                i++;
            }
            m = Math.max(m,j-i+1);
        }
        return m;
    }
}