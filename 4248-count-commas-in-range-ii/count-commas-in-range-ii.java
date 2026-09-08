class Solution {
    public long countCommas(long n) {
      long sum = 0;

        if (n >= 1000) {
            sum += Math.min(n, 999999L) - 1000 + 1;
        }

        if (n >= 1000000) {
            sum += (Math.min(n, 999999999L) - 1000000 + 1) * 2;
        }

        if (n >= 1000000000) {
            sum  += (Math.min(n, 999999999999L) - 1000000000 + 1) * 3;
        }

        if (n >= 1000000000000L) {
            sum += (Math.min(n, 999999999999999L) - 1000000000000L + 1) * 4;
        }
        if (n >= 1000000000000000L) {
            sum += (n - 1000000000000000L + 1) * 5;
}

        return sum;
    }
}