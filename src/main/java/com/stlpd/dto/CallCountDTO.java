package com.stlpd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.stlpd.model.Call;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CallCountDTO {
    private Long callId;
    private LocalDateTime callDatetime;
    private String callLocation;
    private String callType;
    private Long countId;
    private LocalDateTime countDatetime;
    private Integer countLocation;
    private Integer countType;
    private String displayDateTime;
    private Integer countValue;

    public CallCountDTO(Call call) {

        this.callId = call.getId();
        this.callDatetime = call.getDatetime();
        this.callLocation = call.getLocation();
        this.callType = call.getType();

    }
}
