package com.ui.backend.generator;

import com.ui.backend.pojo.Job;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;

public class JobXMLReader {

    public Job readJobFromXML(){
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        XmlSaxHandler xmlSaxHandler = new XmlSaxHandler();
        try {
            Resource resource = new ClassPathResource("static/job.xml");
            InputStream is = resource.getInputStream();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(is, xmlSaxHandler);
        } catch (Exception e){
            System.out.println("test");
        }
        return xmlSaxHandler.getJob();
    }

}
