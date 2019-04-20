package com.md.pyramid.service;

import com.md.pyramid.exception.MdPyramidException;
import com.md.pyramid.utils.FileUtil;

/**
 * 
 * @author Basavaraj.m
 *
 */
public class MdPyramidServiceImpl implements MdPyramidService {

    private static final int ZERO = 0;

    private static final int ONE  = 1;

    private static final int TWO  = 2;

    /**
     * {@inheritDoc}
     */
    @Override
    public int calculateMaxSum(final String inputFilePath) {
        if (null == inputFilePath || inputFilePath.isEmpty()) {
            throw new MdPyramidException("File path should not be null or empty");
        }
        return getMaxSumFromArray(FileUtil.prepareArrayFromInputFile(inputFilePath));
    }

    /**
     * This method will calculate the max sum form given array
     * 
     * @param leftTriangleArray
     * @return max sum
     */
    private int getMaxSumFromArray(final int[][] leftTriangleArray) {
        int size = leftTriangleArray.length - ONE;
        int[][] result = new int[size + ONE][size + ONE];
        boolean isEvenNumber = isEvenNumber(leftTriangleArray[ZERO][ZERO]);
        isEvenNumber = isEvenNumber(size + ONE) ? Boolean.TRUE : isEvenNumber;
        // looping from bottom to up
        for (int row = size - ONE; row >= ZERO; row--) {
            // calculating size -1 row max sum and updating to result array
            isEvenNumber = updateColumnWiseSum(leftTriangleArray, result, isEvenNumber, row);
        }
        return result[ZERO][ZERO] + leftTriangleArray[ZERO][ZERO];
    }

    private boolean isEvenNumber(final int number) {
        return number % TWO == ZERO;
    }

    /**
     * This method will calculate the max sum from the each row with its left child and right child and update to the result array by considering the even or odd
     * 
     * @param leftTriangleArray
     * @param result
     * @param isEvenNumber
     * @param row
     * @return boolean flag to that indicates to consideration even or odd number in next column
     */
    private boolean updateColumnWiseSum(final int[][] leftTriangleArray, final int[][] result, final boolean isEvenNumber, final int row) {
        for (int column = ZERO; column <= row; column++) {
            // finding the left child number from the current
            int leftChild = (isEvenNumber(leftTriangleArray[row + ONE][column]) == isEvenNumber ? leftTriangleArray[row + ONE][column] : ZERO);
            // finding the right child number from the current
            int rightChild = (isEvenNumber(leftTriangleArray[row + ONE][column + ONE]) == isEvenNumber ? leftTriangleArray[row + ONE][column + ONE] : ZERO);
            // if both numbers are even or odd then taking the max number
            int validNodeNumber = getValidNodeNumber(leftChild, rightChild);
            // updating the max number in result array
            result[row][column] = validNodeNumber + (leftChild == validNodeNumber ? result[row + ONE][column] : result[row + ONE][column + ONE]);
        }
        return isEvenNumber ? Boolean.FALSE : Boolean.TRUE;
    }

    /**
     * This method is used to get the max number from the given left child and right child numbers
     * 
     * @param leftChild
     * @param rightChild
     * @param isEvenNumber
     * @return max number
     */
    private int getValidNodeNumber(final int leftChild, final int rightChild) {
        if (leftChild != ZERO && rightChild != ZERO) {
            return leftChild > rightChild ? leftChild : rightChild;
        } else if (leftChild != ZERO) {
            return leftChild;
        } else if (rightChild != ZERO) {
            return rightChild;
        } else {
            return 0;
        }
    }
}
