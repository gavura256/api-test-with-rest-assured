package org.gavura.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

@Builder
@EqualsAndHashCode
@Getter
public class Store {
    @EqualsAndHashCode.Exclude
    private Long id;
    private Long petId;
    private Integer quantity;
    private Date shipDate;
    private String status;
    private Boolean complete;
}