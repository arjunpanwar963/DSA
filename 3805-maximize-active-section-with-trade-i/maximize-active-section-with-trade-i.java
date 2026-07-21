class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int one = 0;
        for(char cc : s.toCharArray()){
            if (cc == '1')
            one++;
        }
        String t = "1" + s + "1";
        int m = t.length();
        int prev = 0, prev1 = 0;
        char pre = '#', pre1 = '#';
        int ans = one;
        int i = 0;
        while(i < m){
            char curr = t.charAt(i);
            int j = i;
            while(j < m && t.charAt(j) == curr){
                j++;
            }
            int c = j - i ;
            if(pre == '0' && pre1 == '1' && curr == '0'){
                ans = Math.max(ans , one + prev + c);
            }
            pre = pre1;
            prev = prev1;
            pre1 = curr;
            prev1 = c;
            i = j;
        }
        return Math.min(ans, n);
    }
}