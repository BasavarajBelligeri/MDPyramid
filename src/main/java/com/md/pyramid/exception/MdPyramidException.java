package com.md.pyramid.exception;

public class MdPyramidException extends RuntimeException {

    private static final long serialVersionUID = -5918324412488576141L;

    public MdPyramidException(final String message) {
        super(message);
    }

    public MdPyramidException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}
