package com.ui.backend.pojo;

import lombok.Data;

@Data
public abstract class Node {
    String className;
    int parallelism;
}
