class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        prevm = {}
        for i, n in enumerate(nums):
            diff = target - n
            if  diff in prevm:
                return [prevm[diff], i]
            prevm[n] = i