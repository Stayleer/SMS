package service;

public abstract class RateLimiter {
    protected final int maxRequestPerSec;

    public RateLimiter(int maxRequestPerSec) {
        this.maxRequestPerSec = maxRequestPerSec;
    }

    public abstract void checkAllowRequest();
}
