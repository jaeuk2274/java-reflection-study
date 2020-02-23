package me.jaeuk;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
중요 애노테이션
@Retention: 해당 애노테이션을 언제까지 유지할 것인가? 소스, 클래스, 런타임
@Inherit: 해당 애노테이션을 하위 클래스까지 전달할 것인가?
@Target: 어디에 사용할 수 있는가?

리플렉션
getAnnotations(): 상속받은 (@Inherit) 애노테이션까지 조회
getDeclaredAnnotations(): 자기 자신에만 붙어있는 애노테이션 조회
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldAnnotation {

    //public String name();
    // 어차피 다 퍼블릭
    //String name();
    //int number(); // 어노테이션 쓰는 곳에서 줘도 되긴 하나
    // @MyAnnotation(name = "jaeuk", number = 100)

    // 값을 안줘도 사용 가능하게 디폴트 값
    String name() default "field jaeuk";
    int number() default 300;



}
