package com.ui.backend.flink.operator.transformation;

import org.apache.flink.api.common.functions.MapFunction;

public class MyTestTransformation implements MapFunction<String, String> {
    @Override
    public String map(String s) {
        return s;
    }
}
