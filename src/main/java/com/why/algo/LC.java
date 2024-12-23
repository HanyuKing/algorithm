package com.why.algo;

import org.junit.Test;

import java.util.*;

/**
 * @Author Hanyu.Wang
 * @Date 2024/12/19 10:25
 * @Description
 * @Version 1.0
 **/
public class LC extends Base {

    @Test
    public void testP12() {
        print(intToRoman(58));
        print(intToRoman(1994));
        print(intToRoman(3749));
    }

    public String intToRoman(int num) {
        TreeMap<Integer, String> sortedMap = new TreeMap<>((o1, o2) -> o2 - o1);
        sortedMap.put(1000, "M");
        sortedMap.put(500, "D");
        sortedMap.put(100, "C");
        sortedMap.put(50, "L");
        sortedMap.put(10, "X");
        sortedMap.put(5, "V");
        sortedMap.put(1, "I");
        sortedMap.put(4, "IV");
        sortedMap.put(9, "IX");
        sortedMap.put(40, "XL");
        sortedMap.put(90, "XC");
        sortedMap.put(400, "CD");
        sortedMap.put(900, "CM");

        sortedMap.put(3, "III");
        sortedMap.put(30, "XXX");
        sortedMap.put(300, "CCC");
        sortedMap.put(3000, "MMM");

        StringBuilder ans = new StringBuilder();

        for (Map.Entry<Integer, String> entry : sortedMap.entrySet()) {
            int curr = entry.getKey();
            while (num - curr >= 0) {
                num = num - curr;
                ans.append(entry.getValue());
            }
            if (num == 0) {
                break;
            }
        }

        return ans.toString();
    }

    @Test
    public void testP3188() {
        System.out.println(minAnagramLength("aabbabab"));
        System.out.println(minAnagramLength("abba"));
        System.out.println(minAnagramLength("aabb"));
        System.out.println(minAnagramLength("aabbbb"));
    }

    public int minAnagramLength(String s) {
        char[] chars = s.toCharArray();
        int n = s.length();
        int halfN = n / 2;
        for (int span = 1; span <= halfN; span++) {
            if (n % span != 0) {
                continue;
            }
            int[] cnt0 = new int[26];
            for (int i = 0; i < span; i++) {
                cnt0[chars[i] - 'a']++;
            }

            int[] cnt2 = new int[26];

            int i = span;
            for (; i < n; i++) {
                cnt2[chars[i] - 'a']++;
                if ((i + 1) % span == 0) {
                    if (!Arrays.equals(cnt0, cnt2)) {
                        break;
                    }
                    if (i == n - 1) {
                        return span;
                    }
                    Arrays.fill(cnt2, 0);
                }
            }
        }
        return n;
    }
    public int minAnagramLength2(String s) {
        char[] chars = s.toCharArray();
        for (int span = 1; span <= s.length() / 2; span++) {
            if (s.length() % span != 0) {
                continue;
            }
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < span; i++) {
                map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
            }
            Map<Character, Integer> map2 = new HashMap<>();

            int i = span;
            for (; i < s.length(); i++) {
                map2.put(chars[i], map2.getOrDefault(chars[i], 0) + 1);
                if ((i + 1) % span == 0) {
                    if (map2.size() != map.size()) {
                        break;
                    }
                    if (!mapEquals(map, map2)) {
                        break;
                    }
                    if (i == s.length() - 1) {
                        return span;
                    }
                    map2.clear();
                }
            }
        }
        return s.length();
    }

    private static boolean mapEquals(Map<Character, Integer> map, Map<Character, Integer> map2) {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Integer count2;
            if ((count2 = map2.get(entry.getKey())) == null
                    || !count2.equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testP3285() {
        int[] height = new int[] {10, 1, 10, 1, 10};
        int threshold = 3;

        print(stableMountains(height, threshold));
    }

    public List<Integer> stableMountains(int[] height, int threshold) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < height.length - 1; i++) {
            if (height[i] > threshold) {
                result.add(i + 1);
            }
        }
        return result;
    }

    @Test
    public void testP8() {
//        print(myAtoi("42"));
//        print(myAtoi("-42"));
//        print(myAtoi("    -42"));
//        print(myAtoi("    -042"));
//
//        print(myAtoi("2147483647"));
//        print(myAtoi("-2147483648"));
//        print(myAtoi("-21474836481"));
//        print(myAtoi("-91283472332")); // -2147483648
        print(myAtoi("-6147483648")); // -2147483648

//        print(myAtoi("0-1"));
//        print(myAtoi("-1337c0d3"));
//        print(myAtoi("1337c0d3"));
//
//        print(myAtoi("words and 987"));

//        print(myAtoi("2147483646"));


    }

    public int myAtoi(String s) {
        if (s.length() == 0) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                if (sb.length() == 0) {
                    continue;
                } else {
                    break;
                }
            }
            if ((c == '-' || c == '+') && sb.length() == 0
                    || (c >= '0' && c <= '9')) {
                sb.append(c);
            } else {
                break;
            }
        }

        if (sb.length() == 0) {
            return 0;
        }

        int result = 0;
        char[] numArray = sb.toString().toCharArray();
        int i = 0;
        if (numArray[0] == '-' || numArray[0] == '+') {
            i++;
        }

        int boundary = Integer.MAX_VALUE / 10;
        for ( ; i < numArray.length; i++) {
            int num = (numArray[i] - '0');
            if (result > boundary || result == boundary && num > 7) {
                return numArray[0] == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result = result * 10 + num;
        }

        return numArray[0] == '-' ? -result : result;
    }

    public int myAtoi2(String s) {
        if (s.length() == 0) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                if (sb.length() == 0) {
                    continue;
                } else {
                    break;
                }
            }
            if ((c == '-' || c == '+') && sb.length() == 0
                    || (c >= '0' && c <= '9')) {
                sb.append(c);
            } else {
                break;
            }
        }

        if (sb.length() == 0) {
            return 0;
        }

        int result = 0;
        char[] numArray = sb.toString().toCharArray();
        int i = 0;
        if (numArray[0] == '-' || numArray[0] == '+') {
            i++;
        }

        for ( ; i < numArray.length; i++) {
            int num = (numArray[i] - '0');
            if (result * 10 / 10 != result) {
                return numArray[0] == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            if (result * 10 <= Integer.MAX_VALUE - num) {
                result = result * 10 + num;
            } else {
                return numArray[0] == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
        }

        return numArray[0] == '-' ? -result : result;
    }
}
