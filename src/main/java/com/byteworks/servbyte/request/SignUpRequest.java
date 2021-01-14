package com.byteworks.servbyte.request;

import com.byteworks.servbyte.model.CompanyType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest extends PictureRequest implements Serializable {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String phoneNumber;

    @Builder.Default
    private CompanyType companyType = CompanyType.RESTAURANT;

    @JsonIgnore
    private String city;

    @JsonIgnore
    @Builder.Default
    private Set<Long> deliveryChannelIds = new HashSet<>();

}



