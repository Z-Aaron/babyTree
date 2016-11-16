package io.github.kolacbb.babytree.model;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */
public class Result<T> {
    List<T> results;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
