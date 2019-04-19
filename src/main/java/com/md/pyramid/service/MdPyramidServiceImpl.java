package com.md.pyramid.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import com.md.pyramid.exception.MdPyramidException;

public class MdPyramidServiceImpl implements MdPyramidService {

    private static final String SPACE_REGX = "\\s+";

    private static final int    ZERO       = 0;

    private static final int    ONE        = 1;

    private static final int    TWO        = 2;

    /**
     * {@inheritDoc}
     */
    @Override
    public int calculateMaxSum(final String inputFilePath) {
        if (null == inputFilePath || inputFilePath.isEmpty()) {
            throw new MdPyramidException("File path should not be null or empty");
        }
        return getMaxSumFromArray(prepareArrayFromInputFile(inputFilePath));
    }

    private int getMaxSumFromArray(final int[][] leftTriangleArray) {
        int size = leftTriangleArray.length - ONE;
        int[][] result = new int[size + ONE][size + ONE];
        int evenFlag = leftTriangleArray[ZERO][ZERO] % TWO;
        evenFlag = (((size + ONE) % TWO) == evenFlag ? evenFlag : ZERO);
        for (int row = size - ONE; row >= ZERO; row--) {
            evenFlag = updateColumnWiseSum(leftTriangleArray, result, evenFlag, row);
        }
        return result[ZERO][ZERO] + leftTriangleArray[ZERO][ZERO];
    }

    private int updateColumnWiseSum(final int[][] leftTriangleArray, final int[][] result, int evenFlag, final int row) {
        for (int column = ZERO; column <= row; column++) {
            int leftChild = (leftTriangleArray[row + ONE][column] % TWO == evenFlag ? leftTriangleArray[row + ONE][column] : ZERO);
            int rightChild = (leftTriangleArray[row + ONE][column + ONE] % TWO == evenFlag ? leftTriangleArray[row + ONE][column + ONE] : ZERO);
            int validNodeNumber = getValidNodeNumber(leftChild, rightChild, evenFlag);
            result[row][column] = validNodeNumber + (leftChild == validNodeNumber ? result[row + ONE][column] : result[row + ONE][column + ONE]);
        }
        evenFlag = (evenFlag == ONE ? ZERO : ONE);
        return evenFlag;
    }

    private int getValidNodeNumber(final int leftChild, final int rightChild, final int evenFlag) {
        if (leftChild % TWO == evenFlag && rightChild % TWO == evenFlag && leftChild != ZERO && rightChild != ZERO) {
            return leftChild > rightChild ? leftChild : rightChild;
        } else if (leftChild % TWO == evenFlag && leftChild != ZERO) {
            return leftChild;
        } else if (rightChild % TWO == evenFlag && rightChild != ZERO) {
            return rightChild;
        } else {
            return 0;
        }
    }

    private int[][] prepareArrayFromInputFile(final String inputFilePath) {
        try {
            URL url = getClass().getResource(inputFilePath);
            if (null == url) {
                throw new MdPyramidException("File does not exist for input file name :" + inputFilePath);
            }
            File file = new File(url.getPath());
            return getNumberArray(file);
        } catch (IOException exception) {
            throw new MdPyramidException("Exception occrued while reading file " + inputFilePath, exception);
        }
    }

    private int[][] getNumberArray(final File file) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(file.getPath()));) {
            return lines.map(this::getArrayFromLine)
                    .toArray(int[][]::new);
        }

    }

    private int[] getArrayFromLine(final String line) {
        return Arrays.stream(line.trim().split(SPACE_REGX))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

}
