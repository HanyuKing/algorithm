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
}
