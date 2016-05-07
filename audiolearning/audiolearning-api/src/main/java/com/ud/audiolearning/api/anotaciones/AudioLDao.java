package com.ud.audiolearning.api.anotaciones;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
@Retention(RetentionPolicy.RUNTIME)
public @interface AudioLDao {

}
