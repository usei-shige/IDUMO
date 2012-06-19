package com.hixi_hyi.idumo.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import com.hixi_hyi.idumo.core.data.DataElement;

@Target({ ElementType.TYPE })
public @interface IDUMOAdaptor {
	String author();

	String name();

	String description() default "";

	Class<? extends DataElement> send();

	Class<? extends DataElement>[] receive();
}
