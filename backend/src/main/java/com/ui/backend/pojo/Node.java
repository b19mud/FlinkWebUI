package com.ui.backend.pojo;

import lombok.Data;

import java.util.Properties;

@Data
public abstract class Node {
    String className;
    int parallelism;
    Properties properties;
}
