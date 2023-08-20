package com.duu.reggie.dto;

import com.duu.reggie.entity.Setmeal;
import com.duu.reggie.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
