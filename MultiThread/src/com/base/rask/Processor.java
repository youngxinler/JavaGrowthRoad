package com.base.rask;

public interface Processor<T> {
    void process(T t);
    void write(T t);
}
