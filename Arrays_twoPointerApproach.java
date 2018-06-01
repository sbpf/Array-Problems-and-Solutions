import java.util.*;

public class Arrays_twoPointerApproach {

	//Leetcode 15. 3Sum
	/*Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

	Note:		
	The solution set must not contain duplicate triplets.		
	Example:		
	Given array nums = [-1, 0, 1, 2, -1, -4],		
	A solution set is:
	[
	  [-1, 0, 1],
	  [-1, -1, 2]
	]*/
	public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        
        for(int i=0; i<nums.length-1; i++)
        {
            if(i>0 && nums[i] == nums[i-1]) continue;
            
            int left = i+1, right = nums.length - 1;
            
            while(left<right)
            {
                if(nums[i] + nums[left] + nums[right] == 0)
                {
                    result.add(new ArrayList<>(Arrays.asList(nums[i],nums[left],nums[right])));
                    left++;
                    right--;
                    while(left<right && nums[left] == nums[left-1]) left++;
                    while(left<right && nums[right] == nums[right+1]) right--;
                }
                else if(nums[i] + nums[left] + nums[right] < 0)
                    left++;
                else
                    right--;
            }
        }  
        return result;
    }
	public static void main(String[] args) {
		
		//Testing 3Sum
		//input: [-1,0,1,2,-1,-4]
		//expected output: [[-1,-1,2],[-1,0,1]]
		
		int[] arr = {-1,0,1,2,-1,-4};
		System.out.println(threeSum(arr));

	}

}
