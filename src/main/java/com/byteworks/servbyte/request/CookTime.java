package com.byteworks.servbyte.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CookTime implements Serializable {

    @Max(24)
    public Integer hour;

    @Max(59)
    public Integer minutes;

    @Max(59)
    public Integer seconds;
}
