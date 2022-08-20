package com.ui.backend.generator;

import com.ui.backend.pojo.Job;
import com.ui.backend.pojo.operator.Operator;
import com.ui.backend.pojo.sink.Sink;
import com.ui.backend.pojo.source.Source;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class FlinkJobGenerator {

    public void generate(Job job) throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        // todo config

        DataStream<String> dataStream = parseSource(environment, job.getSource());
        DataStream<String> dataStream1 = parseTransformation(job.getOperators(), dataStream);
        parseSink(job.getSink(), dataStream1);

        environment.executeAsync();
    }

    private DataStreamSource parseSource(StreamExecutionEnvironment environment, Source source){
        SourceFunction sourceFunction = null;
        try {
            // todo 有无构造参数

            Class cls = Class.forName(source.getClassName());
            // 有构造参数
            sourceFunction = (SourceFunction) cls.getConstructor().newInstance();
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return environment.addSource(sourceFunction).setParallelism(source.getParallelism());
    }

    private DataStream<String> parseTransformation(List<Operator> operators, DataStream<String> dataStream){
        DataStream<String> temp = dataStream;
        for(Operator operator:operators){
            MapFunction mapFunction = null;
            try {
                // todo 有无构造参数
                Class cls = Class.forName(operator.getClassName());
                // 无构造参数
                mapFunction = (MapFunction) cls.newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            temp = temp.map(mapFunction).setParallelism(operator.getParallelism());
        }
        return temp;
    }

    private void parseSink(Sink sink, DataStream<String> dataStream){
        RichSinkFunction sinkFunction = null;
        try {
            // todo 有无构造参数
            Class cls = Class.forName(sink.getClassName());
            // 无构造参数
            sinkFunction = (RichSinkFunction) cls.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        dataStream.addSink(sinkFunction).setParallelism(sink.getParallelism());
    }
}
