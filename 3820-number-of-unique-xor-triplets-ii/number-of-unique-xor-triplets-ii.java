/*class Solution {
    public int uniqueXorTriplets(int[] nums) {
    final int MAX = 2048;
    boolean[] p = new boolean[MAX];
    for(int x : nums){
        p[x] = true;
    }
    int[] v = new int[MAX];
    int m = 0;
    for(int i = 0; i < 2048; i++){
        if(p[i]) v[m++] = i;
    }
    boolean[] ans = new boolean[MAX];
     for (int i = 0; i < m; i++){
            ans[v[i]] = true;
     }
    for(int i = 0; i< m ; i++){
        for(int j = i+1; j < m; j++){
            for(int k = j+1; k < m; k++){
                ans[v[i] ^ v[j] ^ v[k]] = true;
            }
        }
    }
    int res = 0;
    for(boolean b : ans){
        if(b) res++;
    }
    return res;
    }
}*/
class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int maxVal = 0;
        for (int v : nums) {
            maxVal = Math.max(maxVal, v);
        }

        // Power of two larger than maxVal; all XOR results will be < size.
        int size = 1;
        while (size <= maxVal) {
            size <<= 1;
        }

        boolean[] present = new boolean[size];
        for (int v : nums) {
            present[v] = true;
        }

        int[] values = new int[size];
        int distinct = 0;
        for (int v = 0; v < size; v++) {
            if (present[v]) {
                values[distinct++] = v;
            }
        }

        // Step 1: all possible XORs of two values
        boolean[] pairXor = new boolean[size];
        for (int i = 0; i < distinct; i++) {
            int a = values[i];
            for (int j = i; j < distinct; j++) {
                pairXor[a ^ values[j]] = true;
            }
        }

        // Step 2: XOR each pair result with every value
        boolean[] tripletXor = new boolean[size];
        for (int p = 0; p < size; p++) {
            if (!pairXor[p]) continue;

            for (int i = 0; i < distinct; i++) {
                tripletXor[p ^ values[i]] = true;
            }
        }

        int ans = 0;
        for (boolean b : tripletXor) {
            if (b) ans++;
        }

        return ans;
    }
}