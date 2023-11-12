package com.example.springnotionapidemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SampleDataBaseDTO {
    public String name;
    public List<String> tag;
    public String url;

    public Number number;

}
