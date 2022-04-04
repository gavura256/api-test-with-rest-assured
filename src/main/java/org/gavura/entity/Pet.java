package org.gavura.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Pet {
    private int id;
    private Category category;
    private String name;
    private ArrayList<String> photoUrls;
    private ArrayList<Tag> tags;
    private String status;
}
