class Solution {
    public int maximumProduct(int[] nums) {
        int a = Integer.MIN_VALUE;
         int s = Integer.MIN_VALUE;
          int d = Integer.MIN_VALUE;
          int f = Integer.MAX_VALUE;
          int j = Integer.MAX_VALUE;
          for(int x : nums){
            if(x > a){
                d = s ;
                s = a;
                a = x;
            }else if (x > s){
                d = s;
                s = x;
            }else if (x > d){
                d = x;
            }
            if(x < f){
                j = f;
                f = x;
            }else if(x < j){
                j = x;
            }
          }
          return Math.max(a*s*d, a*f*j);
}

    }
