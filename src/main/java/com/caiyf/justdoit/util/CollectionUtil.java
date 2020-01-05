package com.caiyf.justdoit.util;

import java.util.Map;

/**
 * collection util
 *
 * @author caiiyf
 * @date 2020-01-04
 */
public class CollectionUtil {

    public static boolean isEmpty(Map value) {
        return null == value || 0 == value.size();
    }

}
