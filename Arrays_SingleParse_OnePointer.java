import java.util.*;

public class Arrays_SingleParse_OnePointer {
	/*665. Non-decreasing Array
	 *  Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.
		We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

		Example 1:
		Input: [4,2,3]
		Output: True
		Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
		Example 2:
		Input: [4,2,1]
		Output: False
		Explanation: You can't get a non-decreasing array by modify at most one element.
	 */
	//Time complexity: O(N) Space: O(1)
	public static boolean checkPossibility(int[] nums) {
        int index = -1;
        for(int i=0; i<nums.length-1; i++)
        {
            if(nums[i]>nums[i+1])
            {
                if (index!=-1) return false;
                else
                    index = i;
            }                
        }
        return( index==-1 || index==0 || index == nums.length-2 || nums[index-1] <= nums[index+1] || nums[index] <= nums[index+2]);
    }
	
	
	/*747. Largest Number At Least Twice of Others
	 * In a given integer array nums, there is always exactly one largest element.

	Find whether the largest element in the array is at least twice as much as every other number in the array.
	
	If it is, return the index of the largest element, otherwise return -1.
	
	Example 1:	
	Input: nums = [3, 6, 1, 0]
	Output: 1
	Explanation: 6 is the largest integer, and for every other number in the array x,
	6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.
	 */
	//This function parses the array twice.
	//Time complexity: O(N) Space: O(1)
	public static int dominantIndex(int[] nums) {
        int max = -1, max_Index = -1;
        for(int i=0; i<nums.length; i++)
        {
            if(nums[i] > max)
            {
                max = nums[i];
                max_Index = i;
            }
        }
        for(int j = 0; j<nums.length; j++)
        {
            if(max < (nums[j] + nums[j]) && max_Index != j)                
            return -1;
        }
        return max_Index;
    }
	
	/* 189. Rotate Array
	 * Given an array, rotate the array to the right by k steps, where k is non-negative.

		Example 1:
		
		Input: [1,2,3,4,5,6,7] and k = 3
		Output: [5,6,7,1,2,3,4]
		Explanation:
		rotate 1 steps to the right: [7,1,2,3,4,5,6]
		rotate 2 steps to the right: [6,7,1,2,3,4,5]
		rotate 3 steps to the right: [5,6,7,1,2,3,4]
	 */
	//Time complexity: O(N) Space: O(1)
	public static int[] rotate(int[] nums, int k) {
        k = k % nums.length;
        int start=0,current=0,next=0,prev = 0,count = 0;
        for(start = 0; count<nums.length; start++)
        {
            int temp;
            prev = nums[start];         
            current = start;
            do{
                next = (current+k) % nums.length;
                temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;            
                count++;                
            }while(current != start);
        }
        return nums;
    }
	
	/* 414. Third Maximum Number
	 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).
		
		Example 1:
		Input: [3, 2, 1]		
		Output: 1		
		Explanation: The third maximum is 1.
	 */	
	//Time complexity: O(N) Space: O(1)	
	public static int thirdMax(int[] nums) {
        Integer firstMax = null, secondMax = null, thirdMax = null;
        
        for(Integer n: nums)
        {
            if(n.equals(firstMax) || n.equals(secondMax) || n.equals(thirdMax))
                continue;
            
            if(firstMax == null || n > firstMax)
            { 
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = n;                
            }
            else if(secondMax == null || n > secondMax)
            {
                thirdMax = secondMax;
                secondMax = n;
            }
            else if(thirdMax == null || n>thirdMax)
                thirdMax = n;
        }
        return thirdMax != null ? thirdMax :firstMax;        
    }
	
	 /* 605. Can Place Flowers
     * Suppose you have a long flowerbed in which some of the plots are planted and some are not. However,
     *  flowers cannot be planted in adjacent plots - 
     * they would compete for water and both would die.

	   Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty),
	   and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.
	   Example 1:
		Input: flowerbed = [1,0,0,0,1], n = 1
		Output: True
		Example 2:
		Input: flowerbed = [1,0,0,0,1], n = 2
		Output: False
     * 
     */
    public static boolean canPlaceFlowers(int[] flowerbed, int n)
    {
        int i = 0, count = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i++] = 1;
                count++;
            }
             if(count>=n)
                return true;
            i++;
        }
        return false;
    }
    
	public static void main(String[] args) {
		//Testing non-decreasing array
		int[] nums = {1,4,2,3};
		System.out.println(checkPossibility(nums));
		
		// Testing dominantIndex
		int[] arr = {6,3,2,12};
		System.out.println(dominantIndex(arr));
		
		//Testing rotate 
		System.out.println(Arrays.toString(rotate(arr,2)));
		
		//Testing thirdMax
		System.out.println(thirdMax(arr));
	}
	
}

