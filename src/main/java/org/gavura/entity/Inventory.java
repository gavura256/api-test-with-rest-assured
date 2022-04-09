package org.gavura.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Inventory {
    private int id;
    private int petId;
    private int quantity;
    private Date shipDate;
    private String status;
    private boolean complete;
}
