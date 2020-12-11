package com.ruoyi.common.utils;

import java.util.Random;

public class CodeUtils {

    public static int randomNum(int start, int end) {
        return (int) (Math.random() * ((end + 1) - start)) + start;
    }
}
