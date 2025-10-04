package com.dreamjob.transformer;

public interface Transformer<S, T> {
    T transform(S source);
}
