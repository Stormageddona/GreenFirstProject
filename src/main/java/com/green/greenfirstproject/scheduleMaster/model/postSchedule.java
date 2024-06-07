package com.green.greenfirstproject.scheduleMaster.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class postSchedule {
    @JsonIgnore
    private long scd_seq;

    private long scd_user_seq;
    private String scd_start_dt;
    private String scd_end_dt;
    private String scd_title;
    private String scd_text;
    private String scd_location;
    private String scd_input_dt;
    private String scd_update_dt;

}
