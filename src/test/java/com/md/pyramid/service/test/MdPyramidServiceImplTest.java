package com.md.pyramid.service.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.md.pyramid.exception.MdPyramidException;
import com.md.pyramid.service.MdPyramidService;
import com.md.pyramid.service.MdPyramidServiceImpl;

public class MdPyramidServiceImplTest extends AbstractTest {

    private static MdPyramidService mdPyramidService;

    @BeforeClass
    public static void setUp() {
        mdPyramidService = new MdPyramidServiceImpl();
    }

    @Test
    public void calculateMaxSum_givenSampleTestInput_returnMaxSum16() {
        // Given

        String inputFilePath = "/testFiles/TestCase_01.txt";
        // TestCase_01.txt contains
        // 1
        // 8 9
        // 1 5 9
        // 4 5 2 3

        // When
        int maxNumberPath = mdPyramidService.calculateMaxSum(inputFilePath);

        // Then
        assertEquals(16, maxNumberPath);
    }

    @Test
    public void calculateMaxSum_givenActualTestInput_returnMaxSum8186() {
        // Given

        String inputFilePath = "/testFiles/TestCase_02.txt";
        // TestCase_02.txt contains
        // 215
        // 192 124
        // 117 269 442
        // 218 836 347 235
        // 320 805 522 417 345
        // 229 601 728 835 133 124
        // 248 202 277 433 207 263 257
        // 359 464 504 528 516 716 871 182
        // 461 441 426 656 863 560 380 171 923
        // 381 348 573 533 448 632 387 176 975 449
        // 223 711 445 645 245 543 931 532 937 541 444
        // 330 131 333 928 376 733 017 778 839 168 197 197
        // 131 171 522 137 217 224 291 413 528 520 227 229 928
        // 223 626 034 683 839 052 627 310 713 999 629 817 410 121
        // 924 622 911 233 325 139 721 218 253 223 107 233 230 124 233

        // When
        int maxNumberPath = mdPyramidService.calculateMaxSum(inputFilePath);

        // Then
        assertEquals(8186, maxNumberPath);
    }

    @Test
    public void calculateMaxSum_givenTestFileDosentExist_returnException() {
        // Given

        String inputFilePath = "/testFiles/TestCase_03.txt";

        // When
        thrown.expect(MdPyramidException.class);
        thrown.expectMessage("File does not exist for input file name :/testFiles/TestCase_03.txt");
        mdPyramidService.calculateMaxSum(inputFilePath);
    }

    @Test
    public void calculateMaxSum_givenTestFileNameIsNull_returnException() {
        // Given

        String inputFilePath = null;

        // When
        thrown.expect(MdPyramidException.class);
        thrown.expectMessage("File path should not be null or empty");
        mdPyramidService.calculateMaxSum(inputFilePath);
    }

    @Test
    public void calculateMaxSum_givenTestFileNameIsEmpty_returnException() {
        // Given

        String inputFilePath = "";

        // When
        thrown.expect(MdPyramidException.class);
        thrown.expectMessage("File path should not be null or empty");
        mdPyramidService.calculateMaxSum(inputFilePath);
    }

}
