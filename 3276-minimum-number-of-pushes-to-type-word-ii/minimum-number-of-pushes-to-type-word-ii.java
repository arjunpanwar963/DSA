class Solution {
    public int minimumPushes(String word) {
       int[] freq = new int[26];
       for(char  c : word.toCharArray()){
       freq[c - 'a']++;
    }
    Arrays.sort(freq);
    int push = 0;
    int in = 0;
    for(int i = 25; i >= 0; i--){
        if (freq[i] == 0){
             break;
        }
        push += freq[i] * ((in/8) + 1);
        in++;
    }
    return push;

    }
}