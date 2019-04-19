# MDPyramid implementation	details

 This project contains program to find the maximum sum of the numbers as per the given rules below:
 
1.Start from the top and move downwards to an adjacent number as in below.

2.Allowed to walk downwards and diagonally.

3.Walk over the numbers as evens and odds subsequently. Suppose that you are on an even
number the next number you walk must be odd, or if you are stepping over an odd number the next
number must be even. In other words, the final path would be like
Odd -> even -> odd -> even …

4.Reach to the bottom of the pyramid.
 

##Sample Input:
1

8 9

1 5 9

4 5 2 3

Output:
##Max sum: 16
##Path: 1, 8, 5, 2 


##Technology stack used

Java 1.8
Maven 3.X
Junit 4.x

##Approach used to solve this problem

Used dynamic programming approach to find max sum from the given Pyramid


###Step 1: Converting given Pyramid to array:

After Conversion from pyramid to array:

[1,0,0,0]

[8,9,0,0]

[1,5,9,0]

[4,5,2,3]


Result Array: 

[0,0,0,0]

[0,0,0,0]

[0,0,0,0]

[0,0,0,0]



###Step 2: Iterating array from row-1 to 0 here initially row = 3

After completion 1st iteration Result Array: 

[0,0,0,0]

[0,0,0,0]

[4,2,2,0]

[0,0,0,0]

###Step 3: Iterating array from row-1 to 0 here row=2

After completion 2nd iteration Result Array: 

[0,0,0,0]

[7,11,0,0]

[4,2,2,0]

[0,0,0,0]

###Step 4: Iterating array from row-1 to 0 here row=1

After completion 3nd iteration Result Array: 

[15,0,0,0]

[7,11,0,0]

[4,2,2,0]

[0,0,0,0]


###Step 5: Taking the final sum from result array [0,0] element + leftTriangleArray[0,0]

Final result 16 = 15 + 1





 
