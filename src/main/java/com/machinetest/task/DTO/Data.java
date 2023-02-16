package com.machinetest.task.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    private String competition;
    private Integer year;
    private String team1;
    private String team1goals;
    private String team2;
    private String team2goals;
}
