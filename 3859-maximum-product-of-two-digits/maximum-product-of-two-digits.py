class Solution:
    def maxProduct(self, n: int) -> int:
        m = 0
        l = 0
        while n > 0:
            d = n % 10
            if d >= m:
                l = m
                m = d
            elif d >= l:
                l = d
            n //= 10
        return m * l 
