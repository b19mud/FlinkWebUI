package com.ui.backend.flink.operator.sink;

import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

public class MyTestSink extends RichSinkFunction<String> {
    @Override
    public void invoke(String value, Context context) throws Exception {
        System.out.println(value);
    }
}
