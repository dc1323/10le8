package com.ruoyi.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CodeUtils {

    public static int randomNum(int start, int end) {
        return (int) (Math.random() * ((end + 1) - start)) + start;
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        String customRule = "00000000000009000C000C0008000800";
        map.put("cbFreeTime", Integer.parseInt(getNewSubstring(customRule.substring(0, 4)),16));
        map.put("cbBetTime", Integer.parseInt(getNewSubstring(customRule.substring(4, 8)),16));
        map.put("cbEndTime", Integer.parseInt(getNewSubstring(customRule.substring(8, 12)),16));
        map.put("cbDWZWin", Integer.parseInt(getNewSubstring(customRule.substring(12, 16)),16));
        map.put("cbXWZWin", Integer.parseInt(getNewSubstring(customRule.substring(16, 20)),16));
        map.put("cbWZWin",  Integer.parseInt(getNewSubstring(customRule.substring(20, 24)),16));
        map.put("cbDLLost", Integer.parseInt(getNewSubstring(customRule.substring(24, 28)),16));
        map.put("cbXLLost", Integer.parseInt(getNewSubstring(customRule.substring(28, 32)),16));
        System.out.println(map);
    }

    public static String getNewSubstring(String needString){
        String frontStr = needString.substring(0, 2);
        String laterStr = needString.substring(2, needString.length());
        String str = laterStr + frontStr;
        return str;
    }
}
