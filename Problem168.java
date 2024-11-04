//TC: O(n)
//SC: O(n)
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();
        
        // Step through the string in a sliding window manner
        for (int i = 0; i <= s.length() - 10; i++) {
            String substring = s.substring(i, i + 10);
            
            // If it's already in seen, add to repeated
            if (seen.contains(substring)) {
                repeated.add(substring);
            } else {
                seen.add(substring);
            }
        }
        
        // Convert the set to a list and return
        return new ArrayList<>(repeated);
    }
}
