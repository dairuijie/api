package com.api.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName:  ValidateRepeatableRequest   
 * @Description:TODO(防止重复提交注解)   
 * @author: drj 
 * @date:   2018年11月29日 下午11:14:09   
 *     
 * @Copyright: 2018 
 *
 */
@Documented
@Retention(RUNTIME)
@Target({ TYPE, METHOD })///接口、类、枚举、注解、方法
public @interface ValidateRepeatableRequest {

}
