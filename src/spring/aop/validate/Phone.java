/**
 * 
 */
package spring.aop.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * leb测试用源代码
 * 
 * 项目名称 : design_patterns
 * 创建日期 : 2017年5月18日
 * 类  描  述 : 自定义注解
 * 修改历史 : 
 *     1. [2017年5月18日]创建文件 by lwk
 */
@Target({ ElementType.FIELD, ElementType.METHOD  })
/*
 * 作用：用于描述注解的使用范围（即：被描述的注解可以用在什么地方）
 *  取值(ElementType)有：
 *        1.CONSTRUCTOR:用于描述构造器
 *        2.FIELD:用于描述域
 *        3.LOCAL_VARIABLE:用于描述局部变量
 *        4.METHOD:用于描述方法
 *        5.PACKAGE:用于描述包
 *        6.PARAMETER:用于描述参数
 *        7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
 */
@Retention(RetentionPolicy.RUNTIME)
/*
 * 作用：表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效）
 * 取值（RetentionPolicy）有：
 * 1.SOURCE:在源文件中有效（即源文件保留）
 * 2.CLASS:在class文件中有效（即class保留）
 * 3.RUNTIME:在运行时有效（即运行时保留）
 */
@Constraint(validatedBy = { PhoneValidator.class })
public @interface Phone {
    //默认错误消息
    String message() default "手机号码错误";

    //分组
    Class<?>[] groups() default {};

    //负载
    Class<? extends Payload>[] payload() default {};

    @Documented
    @interface List {
        Phone[] value();
    }
}
