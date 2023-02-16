package com.machinetest.task.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Matches {
    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private List<Data> data;

}
