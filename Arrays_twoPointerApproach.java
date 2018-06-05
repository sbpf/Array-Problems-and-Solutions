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
    /*163. Missing Ranges
	Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

	Example:
	Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
	Output: ["2", "4->49", "51->74", "76->99"]*/
	
	public static List<String> findMissingRanges(int[] nums, int lo, int up)
    {
        long lower = ((long)lo)-1;
        long upper = ((long)up)+1;
        List<String> res = new ArrayList<>();
        for(int i=0;i<=nums.length;i++) {
            long next = i == nums.length ? upper : nums[i];
            if(next - lower > 1) {
                String range = createMissingRange(lower+1, next - 1);
                res.add(range);
            }
            lower = next;
        }
        return res;
    }
    
    private static String createMissingRange(long a, long b) {
        return a == b ? String.valueOf(a) : String.format("%d->%d", a, b);
    }
    
    /*581. Shortest Unsorted Continuous Subarray
     * 
     */
    public static int findUnsortedSubarray(int[] nums)
    {
        int left=0, right=nums.length-1;
        
        //Find a tentative left or start index of the unsorted sub-array
        int j=0;
        for(j = 0; j<nums.length-1; j++)
        {
          if(nums[j]>nums[j+1])
          {
             left = j;
             break;
          }        
        }        
        //if left pointer reaches the end, then all the elements are sorted, so return 0
        if(j == nums.length-1)
        {
            return 0;
        }
        
        //Find a tentative right or end index of the unsorted sub-array
        for(int i=nums.length-1; i>0;i--)
        {
            if(nums[i] < nums[i-1])
            {
                right = i;
                break;
            }
        }  
         
        //Find the max and min elements between left and right indices
        int min = nums[left];
        int max = nums[left];
        for(int i = left+1; i <= right; i++)
        {
            if(nums[i]<min) min = nums[i];
            if(nums[i]>max) max = nums[i];           
        }
        
        //Find if there exists any element in sub-array (from index 0 to "left"), that is greater than the "min" element
        for(int i=0; i<left; i++)
        {
            if(nums[i] > min)
            {
                left = i;
                break;
            }
        }
        
        //Find if there exists any element in sub-array (from index right to end of the array), that is lesser than the max element
        for(int i= nums.length-1; i>right; i--)
        {
            if(nums[i] < max)
            {
                right = i;
                break;
            }
        }
        
        return right-left+1;            
    } 
   
	/*
	 * 88. Merge Sorted Array
	 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
		
		The number of elements initialized in nums1 and nums2 are m and n respectively.
		You may assume that nums1 has enough space
		 (size that is greater or equal to m + n) to hold additional elements from nums2.
		 Example:

		Input:
		nums1 = [1,2,3,0,0,0], m = 3
		nums2 = [2,5,6],       n = 3
		
		Output: [1,2,2,3,5,6]
	 */
    public static void merge(int[] A, int m, int[] B, int n) {
        
        int indexA1= m - 1;
		int indexA2 = A.length-1;
		int indexB = n-1;
		
		while(indexA1 >=0 || indexB >=0)
		{
			if(indexB<0)
				A[indexA2--] = A[indexA1--];
			else if(indexA1<0)
				A[indexA2--] = B[indexB--];
            else 
                A[indexA2--] = A[indexA1] > B[indexB] ? A[indexA1--] :B[indexB--];
		}		
    }
	public static void main(String[] args) {
		
		//Testing 3Sum
		//input: [-1,0,1,2,-1,-4]
		//expected output: [[-1,-1,2],[-1,0,1]]
		
		int[] arr = {-1,0,1,2,-1,-4};
		System.out.println(threeSum(arr));
        
        //Testing Missing ranges		
		int[] rangeNumbers = {0,1,3,50,75};
		System.out.println(findMissingRanges(rangeNumbers,0,99));
		
		//Testing findUnsortedSubarray
		int[] unsortedArray = {2, 6, 4, 8, 10, 9, 15};
		System.out.println("unsorted sub array has " + findUnsortedSubarray(unsortedArray) +" elements");

	}

}
