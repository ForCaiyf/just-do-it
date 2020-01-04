package com.caiyf.justdoit.util;

/**
 * string util
 *
 * @author caiyf
 * @date 2020-01-04
 */
public class StringUtil {

    /**
     * string is empty or not
     *
     * @param source
     * @return
     */
    public static boolean isEmpty(CharSequence source) {
        return null == source || 0 == source.length();
    }

}
