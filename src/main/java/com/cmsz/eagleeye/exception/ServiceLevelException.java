package com.cmsz.eagleeye.exception;

public class ServiceLevelException extends Exception {

    private static final long serialVersionUID = 988592186626034036L;

    public ServiceLevelException() {
        super();
    }

    public ServiceLevelException(String arg0) {
        super(arg0);
    }

    public ServiceLevelException(Throwable arg1) {
        super(arg1);
    }

    public ServiceLevelException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
}
