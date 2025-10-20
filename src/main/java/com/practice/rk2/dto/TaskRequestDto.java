package com.practice.rk2.dto;

import com.practice.rk2.model.Stat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDto {
    private String title;
    private Stat stat;
    private int xpReward;
}
