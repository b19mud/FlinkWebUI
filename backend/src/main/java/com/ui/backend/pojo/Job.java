package com.ui.backend.pojo;

import com.ui.backend.pojo.operator.Operator;
import com.ui.backend.pojo.sink.Sink;
import com.ui.backend.pojo.source.Source;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Job {
    Map<String, String> config;
    Source source;
    List<Operator> operators;
    Sink sink;
}
