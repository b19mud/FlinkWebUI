package com.ui.backend.generator;

import com.ui.backend.pojo.Job;
import com.ui.backend.pojo.operator.Operator;
import com.ui.backend.pojo.sink.Sink;
import com.ui.backend.pojo.source.Source;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XmlSaxHandler extends DefaultHandler {
    private final Job job = new Job();

    public Job getJob(){
        return job;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if("configs".equals(qName)){
            job.setConfig(new HashMap<>());
            return;
        }
        if("operators".equals(qName)){
            job.setOperators(new ArrayList<>());
            return;
        }
        Map<String, String> currentAttr = new HashMap<>();
        for(int i = 0;i < attributes.getLength();i++){
            String name = attributes.getQName(i);
            String value = attributes.getValue(name);
            currentAttr.put(name, value);
        }
        if("config".equals(qName)){
            job.getConfig().putAll(currentAttr);
        }else if("source".equals(qName)){
            Source source = new Source();
            source.setClassName(currentAttr.get("class"));
            source.setParallelism(Integer.parseInt(currentAttr.get("parallelism")));
            job.setSource(source);
        }else if("operator".equals(qName)){
            Operator operator = new Operator();
            operator.setClassName(currentAttr.get("class"));
            operator.setParallelism(Integer.parseInt(currentAttr.get("parallelism")));
            job.getOperators().add(operator);
        }else if("sink".equals(qName)){
            Sink sink = new Sink();
            sink.setClassName(currentAttr.get("class"));
            sink.setParallelism(Integer.parseInt(currentAttr.get("parallelism")));
            job.setSink(sink);
        }else{
            System.out.println("this operator is invalid  "+qName);
        }

    }
}
