package me.jaeuk;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws NoSuchFieldException {
        System.out.println( "Hello World!" );

        // 1.클래스 타입으로 가져오는 방법
        Class<Book> bookClass = Book.class;

        // 2.인스턴스가 있으면 인스턴스로 가져올 수 도 있음.
        Book book = new Book();
        Class<? extends Book> aClass = book.getClass();

        // 3. 아무것도 모르고 문자열만 알 경우 가져오는 방법
        //"me.jaeuk.Book"
        //Class<?> aClass1 = Class.forName("me.jaeuk.Book");

        /*
        Class<T>를 통해 할 수 있는 것
            필드 (목록) 가져오기
            메소드 (목록) 가져오기
            상위 클래스 가져오기
            인터페이스 (목록) 가져오기
            애노테이션 가져오기
            생성자 가져오기
         */
        System.out.println("public 한 필트 - .getFields()");
        Arrays.stream(bookClass.getFields()).forEach(System.out::println);
        System.out.println("전체 필드 - .getDeclaredFields()");
        Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println);
        System.out.println("필드 이름으로도 선택 가능 - .getDeclaredField()");
        System.out.println(bookClass.getDeclaredField("d"));

        System.out.println("전체 필드 및 필드에 들어있는 값까지 확인 가능");
        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            try{
                f.setAccessible(true);
                System.out.printf("필드:%s  값:%s\n", f, f.get(book));
            } catch (IllegalAccessException e){
                e.printStackTrace();
            }
        });

        Class<? super MyBook> superClass = MyBook.class.getSuperclass();
        System.out.println(superClass);


        Arrays.stream(MyBook.class.getInterfaces()).forEach(f -> {
            int modifiers = f.getModifiers();
            System.out.println(f);
            System.out.println(Modifier.isPrivate(modifiers));
        });
    }
}
