/**
 * 
 */
package com.cmsz.eagleeye.util;

/**
 * @author fangding
 * 
 */
public class Message<E> {
    /**
     * 输出结果布尔类型,true,false
     */
    private boolean success;
    /**
     * 输出消息
     */
    private String message;

    /**
     * object对象
     */
    private E data;

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success
     *            the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the data
     */
    public E getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(E data) {
        this.data = data;
    }

}
