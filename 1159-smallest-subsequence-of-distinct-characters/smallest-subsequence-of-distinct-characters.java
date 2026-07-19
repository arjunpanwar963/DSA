class Solution {
    public String smallestSubsequence(String s) {
        int[] last = new int[26];
        for(int i = 0; i < s.length(); i++){
             last[s.charAt(i)- 'a'] = i;
        } 
        boolean[] visit = new boolean[26];
        StringBuilder stack = new StringBuilder() ;
        for( int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (visit[c - 'a']){
                continue;
            }
            while(stack.length()> 0 &&  stack.charAt(stack.length() - 1) > c && last[stack.charAt(stack.length() - 1) - 'a'] > i){
                visit[stack.charAt(stack.length() - 1) - 'a'] = false;
                stack.deleteCharAt(stack.length() - 1);
            }
            stack.append(c);
            visit[c - 'a'] = true;
        }
        return stack.toString();
    }
}