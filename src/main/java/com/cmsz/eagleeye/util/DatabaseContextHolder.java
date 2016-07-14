/**
 * 
 */
package com.cmsz.eagleeye.util;

/**
 * @author chenyangjian
 * @desc 设置连接的数据源
 * 
 */
public class DatabaseContextHolder {


    // 线程安全的ThreadLocal
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setDbType(String dbType) {
        contextHolder.set(dbType);
    }

    public static String getDbType() {
        return ((String) contextHolder.get());
    }

    public static void clearDbType() {
        contextHolder.remove();
    }
}
