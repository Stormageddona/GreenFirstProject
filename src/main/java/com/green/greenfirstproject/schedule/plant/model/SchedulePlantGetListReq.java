package com.green.greenfirstproject.schedule.plant.model;

import com.green.greenfirstproject.common.page.Paging;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SchedulePlantGetListReq extends Paging{
    private long userSeq;

    public SchedulePlantGetListReq(int page, int size, long userSeq) {
        super(page, size);
        this.userSeq = userSeq;
    }
}
