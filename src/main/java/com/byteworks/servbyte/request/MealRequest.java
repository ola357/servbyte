package com.byteworks.servbyte.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealRequest extends PictureRequest implements Serializable {

    @NotBlank
    private String name;

    @NotNull
    private Float price;

    @Max(24)
    public Integer hour;

    @Max(59)
    public Integer minutes;

    @Max(59)
    public Integer seconds;

    @NotNull
    private String description;


}
