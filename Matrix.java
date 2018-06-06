import java.util.*;

public class Matrix {

	/*840. Magic Squares In Grid
	   A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.
	
	   Given an grid of integers, how many 3 x 3 "magic square" subgrids are there?
	   (Each subgrid is contiguous).
	   Example 1:
	
		Input: [[4,3,8,4],
		        [9,5,1,9],
		        [2,7,6,2]]
		Output: 1
		Explanation: 
		The following subgrid is a 3 x 3 magic square:
		438
		951
		276
		
		while this one is not:
		384
		519
		762
		
		In total, there is only one magic square inside the given grid.
		Note:
		
		1 <= grid.length <= 10
		1 <= grid[0].length <= 10
		0 <= grid[i][j] <= 15
	*/
	public static int numMagicSquaresInside(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int result = 0;
        
        if(rows<3 || cols<3)
            return 0;
        
        for(int r = 0; r<rows-2; r++)
        {
            for(int c=0; c<cols-2; c++)
            {
                int[] gridElements = {grid[r][c],grid[r][c+1],grid[r][c+2],
                                      grid[r+1][c],grid[r+1][c+1],grid[r+1][c+2],
                                      grid[r+2][c],grid[r+2][c+1],grid[r+2][c+2]};
                
                if (isMagic(gridElements))
                   result++;
            }
        }   
        return result;
     } 
    
    public static boolean isMagic(int[] nums)
    {
        if(nums.length != 9) return false;
        if(nums[4] != 5) return false;
        
        //check if there are duplicate elements
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i=0; i<9; i++)
        {
            if(nums[i] > 9 ||nums[i] < 1 || set.contains(nums[i]))
                return false;
            set.add(nums[i]);
        }
        
        if(nums[0] + nums[1] + nums[2] == 15 &&
           nums[3] + nums[4] + nums[5] == 15 &&
           nums[6] + nums[7] + nums[8] == 15 &&
           nums[0] + nums[4] + nums[8] == 15 &&
           nums[2] + nums[4] + nums[6] == 15 &&
           nums[0] + nums[3] + nums[6] == 15 &&
           nums[1] + nums[4] + nums[7] == 15 &&
           nums[2] + nums[5] + nums[8] == 15)
            return true;
        
        return false;        
    }
    
    
	public static void main(String[] args) {
		int[][] grid = {{4,3,8,4},{9,5,1,9},{2,7,6,2}};
		System.out.println("Number of magic squares: " + numMagicSquaresInside(grid));
	}
}
