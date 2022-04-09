package org.gavura.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Store {
    @EqualsAndHashCode.Exclude
    private Long id;
    private Long petId;
    private Integer quantity;
    private Date shipDate;
    private String status;
    private Boolean complete;
}
