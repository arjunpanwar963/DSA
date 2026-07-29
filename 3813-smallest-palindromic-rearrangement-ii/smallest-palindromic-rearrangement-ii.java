class Solution {
    static final long LIMIT = 1_000_001L;
    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for(char c: s.toCharArray()) freq[c - 'a']++;
        int[] half = new int[26];
        int halflen = 0;
        char mid = 0;
        for(int i = 0; i < 26; i++){
            half[i] = freq[i] / 2;
            halflen += half[i];
            if((freq[i] &  1) == 1 ) mid = (char)('a' + i);

        }
        long tot = countWays(half, halflen);
        if(tot < k) return "";
        StringBuilder left = new StringBuilder();
        for(int i = 0; i< halflen; i++){
            for(int c = 0; c < 26; c++){
                if(half[c] == 0) continue;
                half[c]--;
                long way = countWays(half, halflen - i - 1);
                if(way >= k){
                    left.append((char)('a'+ c));
                    break;
                }else{
                    k -= way;
                    half[c]++;
                }
            }
        }
        StringBuilder ans = new StringBuilder(left);
        if(mid != 0) ans.append(mid);
        ans.append(left.reverse());
        return ans.toString();
    }
    private long countWays(int[] cnt, int len) {
    long ways = 1;
    int remaining = len;

    for (int x : cnt) {
        if (x == 0) continue;

        ways *= nCr(remaining, x);
        if (ways >= LIMIT) return LIMIT;

        remaining -= x;
    }

    return ways;
}

private long nCr(int n, int r) {
    if (r > n - r) r = n - r;

    long res = 1;
    for (int i = 1; i <= r; i++) {
        res = res * (n - r + i) / i;
        if (res >= LIMIT) return LIMIT;
    }
    return res;
}
    }
