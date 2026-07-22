/*class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int[] pre = new int[n+1];
        for(int i = 0;i < n ;i++){
            pre[i+1] = pre[i] + (s.charAt(i) == '1' ? 1 : 0);
        }
            List<Integer> ans = new ArrayList<>();
            for(int[] q : queries){
                int l = q[0], r = q[1];
                int one = pre[r+1] - pre[l];
                char[] t = new char[r-l+3];
                t[0] = '1';
                for(int  i = l; i <= r ; i++){
                    t[i-l+1] = s.charAt(i);
                }
                t[t.length - 1] = '1';
                int best = 0;
                int m = t.length;
                int  i = 1;
                while(i < m -1){
                    if (t[i] != '0'){
                        i++;
                        continue;
                    }
                    int  z = i;
                    while(i < m - 1 && t[i] == '0'){
                        i++;
                    }
                    int x = i - 1;
                   if (i >= m || t[z - 1] != '1' || t[i] != '1') {
    continue;
}
                    int zero = x - z + 1;
                    int j = z;
                    while( j <= x){
                        if(t[j] == '1'){
                            int k = j;
                            while(k <= x && t[k] == '1') k++;
                            if( j > z && k - 1 < x){
                                int onl = k - j;
                                best = Math.max(best, zero - onl);
                            }
                            j = k;
                        }else{
                            j++;
                        }
                    } 
                }
                ans.add(one+best);
            }
        
            return ans;
        
    }
}
*/


class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        
        // Find all maximal contiguous '0' blocks
        List<Integer> L = new ArrayList<>();
        List<Integer> R = new ArrayList<>();
        List<Integer> A = new ArrayList<>();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == '0') {
                int start = i;
                while (i < n && s.charAt(i) == '0') i++;
                int end = i - 1;
                L.add(start);
                R.add(end);
                A.add(end - start + 1);
            } else {
                i++;
            }
        }
        
        int M = A.size();
        int totalOnes = 0;
        for (i = 0; i < n; i++) {
            if (s.charAt(i) == '1') totalOnes++;
        }
        
        int[] Larr = L.stream().mapToInt(Integer::intValue).toArray();
        int[] Rarr = R.stream().mapToInt(Integer::intValue).toArray();
        int[] Aarr = A.stream().mapToInt(Integer::intValue).toArray();
        
        // Sparse Table for adjacent sums of full blocks
        int[][] st = null;
        int[] log2 = null;
        if (M >= 2) {
            int[] P = new int[M - 1];
            for (int j = 0; j < M - 1; j++) {
                P[j] = Aarr[j] + Aarr[j + 1];
            }
            int K = 32 - Integer.numberOfLeadingZeros(M - 1); // floor(log2(M-1)) + 1
            st = new int[K][M - 1];
            st[0] = P.clone();
            for (int k = 1; k < K; k++) {
                int len = 1 << (k - 1);
                for (int j = 0; j + (1 << k) <= M - 1; j++) {
                    st[k][j] = Math.max(st[k - 1][j], st[k - 1][j + len]);
                }
            }
            log2 = new int[M];
            log2[1] = 0;
            for (int j = 2; j < M; j++) {
                log2[j] = log2[j / 2] + 1;
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int[] q : queries) {
            int l = q[0], r = q[1];
            if (M == 0) {
                ans.add(totalOnes);
                continue;
            }
            // first '0'-block that ends at or after l
            int a = lowerBound(Rarr, l);
            if (a == M || Larr[a] > r) {
                ans.add(totalOnes);
                continue;
            }
            // last '0'-block that starts at or before r
            int b = upperBound(Larr, r) - 1;
            if (a == b) {
                ans.add(totalOnes);
                continue;
            }
            
            int Ca = Math.min(Rarr[a], r) - Math.max(Larr[a], l) + 1;
            int Cb = Math.min(Rarr[b], r) - Math.max(Larr[b], l) + 1;
            int gain;
            if (a + 1 == b) {
                gain = Ca + Cb;
            } else {
                int gain1 = Ca + Aarr[a + 1];
                int gain2 = Aarr[b - 1] + Cb;
                int gain3 = 0;
                if (a + 1 <= b - 2) {
                    int left = a + 1;
                    int right = b - 2;
                    int length = right - left + 1;
                    int k = log2[length];
                    gain3 = Math.max(st[k][left], st[k][right - (1 << k) + 1]);
                }
                gain = Math.max(gain1, Math.max(gain2, gain3));
            }
            ans.add(totalOnes + gain);
        }
        return ans;
    }
    
    private int lowerBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] >= target) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
    
    private int upperBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] > target) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}