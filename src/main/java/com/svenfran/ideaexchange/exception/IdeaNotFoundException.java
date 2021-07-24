package com.svenfran.ideaexchange.exception;

public class IdeaNotFoundException  extends RuntimeException {
    public IdeaNotFoundException(String message) {
        super(message);
    }
}
