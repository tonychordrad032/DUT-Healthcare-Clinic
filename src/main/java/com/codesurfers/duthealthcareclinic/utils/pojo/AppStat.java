package com.codesurfers.duthealthcareclinic.utils.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppStat {
    private String key;
    private Long value;
}
