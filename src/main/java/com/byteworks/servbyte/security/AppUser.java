package com.byteworks.servbyte.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;


@Target(value = { ElementType.PARAMETER, ElementType.TYPE})
@AuthenticationPrincipal
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AppUser {

}
