package org.gavura.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
public class Tag {
    private int id;
    @EqualsAndHashCode.Include
    private String name;
}