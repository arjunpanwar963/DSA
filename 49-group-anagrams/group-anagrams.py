from collections import defaultdict
class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        a = defaultdict(list)
        res = []
        for s in strs:
            sorted_s = tuple(sorted(s))
            a[sorted_s].append(s)
        for val in a.values():
            res.append(val)
        return res