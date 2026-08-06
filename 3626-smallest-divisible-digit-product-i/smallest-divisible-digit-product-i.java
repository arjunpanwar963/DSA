class Solution {
    public int smallestNumber(int n, int t) {
        while(true){
            if(dp(n) % t == 0){
                return n;
            }
            n++;
        }
    }
    private int dp(int n){
        int p = 1;
        while(n>0){
            p *= (n % 10);
            n /= 10;
        }
        return p;
    }
}