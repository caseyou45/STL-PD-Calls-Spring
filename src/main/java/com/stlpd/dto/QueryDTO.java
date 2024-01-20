package com.stlpd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QueryDTO {

    private String source;
    private String offense;
    private String location;
    private String neighborhood;
    private String startDate;
    private String endDate;
    public String sortDirection;
    private String sortMethod;

}
