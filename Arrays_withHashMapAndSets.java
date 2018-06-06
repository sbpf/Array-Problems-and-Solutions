import java.util.*;
public class Arrays_withHashMapAndSets {

	/*532. K-diff Pairs in an Array
	 * Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

		Example 1:
		Input: [3, 1, 4, 1, 5], k = 2
		Output: 2
		Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
		Although we have two 1s in the input, we should only return the number of unique pairs.
	 */
	public static int findPairs(int[] nums, int k) {
        
        if(nums.length == 0 || k < 0 || nums == null)
            return 0;
        
        int count =0;
        
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int n : nums)
        {
           map.put(n, map.getOrDefault(n,0) + 1);              
        }
        
        for(Map.Entry<Integer, Integer> entry : map.entrySet())
        {
            if(k==0)
            {
                if(entry.getValue() >= 2)
                    count++;
            }
            else
            {                    
                if(map.containsKey(entry.getKey() + k))
                    count++;
            }
        }
        
        return count;        
    }
	
	/*219. Contains Duplicate II
	 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

		Example 1:		
		Input: nums = [1,2,3,1], k = 3
		Output: true
		Example 3:
		Input: nums = [1,2,3,1,2,3], k = 2
		Output: false
	 */
	public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        
        for(int i = 0; i<nums.length; i++)
        {
            List<Integer> indices = map.get(nums[i]);
            if(indices == null)
            {
                indices = new ArrayList<Integer>();
                indices.add(i);
                map.put(nums[i],indices);
                continue;
            }
            
            for(Integer index: indices)
            {
                if(Math.abs(i-index) <= k)
                    return true;  
            }
            indices.add(i);
            map.put(nums[i],indices);
        }
        return false;
    }
	
	/*697. Degree of an Array
	 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

		Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
		
		Example 1:
		Input: [1, 2, 2, 3, 1]
		Output: 2
		Explanation: 
		The input array has a degree of 2 because both elements 1 and 2 appear twice.
		Of the subarrays that have the same degree:
		[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
		The shortest length is 2. So return 2.
	 */
	
	public static int findShortestSubArray(int[] nums) {
        
        HashMap<Integer,Integer> count = new HashMap<Integer,Integer>();
        HashMap<Integer,Integer> left = new HashMap<Integer,Integer>();
        HashMap<Integer,Integer> right = new HashMap<Integer,Integer>();
        
        for(int i = 0; i<nums.length; i++)
        {
            int n = nums[i];            
            if (left.get(n) == null) left.put(n, i);
            right.put(n, i);
            count.put(n, count.getOrDefault(n,0)+1);
        }
        
        int ans = nums.length;
        int degree = Collections.max(count.values());
        for (int x: count.keySet()) {
            if (count.get(x) == degree) {
                ans = Math.min(ans, right.get(x) - left.get(x) + 1);
            }
        }
        return ans;
    }
	public static void main(String[] args) {
		//Testing findPairs
		int[] nums = {3, 1, 4, 1, 5};
		int k=2;		
		System.out.println(findPairs(nums,k));		
	}

}
