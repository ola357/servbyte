package com.byteworks.servbyte.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class PictureRequest implements Serializable {

    @NotNull
    protected MultipartFile picture;

    @JsonIgnore
    protected String ownerPath;
}
