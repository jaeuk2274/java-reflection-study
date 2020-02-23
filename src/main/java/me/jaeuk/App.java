package me.jaeuk;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws NoSuchFieldException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        /*
        Class 인스턴스 만들기
        Class.newInstance()는 deprecated 됐으며 이제부터는
        생성자를 통해서 만들어야 한다.

        생성자로 인스턴스 만들기
        Constructor.newInstance(params)
        */
        Class<?> bookClass = Class.forName("me.jaeuk.Book2");
        Constructor<?> constructor = bookClass.getConstructor(String.class);
        Book2 book = (Book2) constructor.newInstance("myBook");
        System.out.println(book);

        /*
        필드 값 접근하기/설정하기
        특정 인스턴스가 가지고 있는 값을 가져오는 것이기 때문에 인스턴스가 필요하다.
        Field.get(object)
        Filed.set(object, value)
        Static 필드를 가져올 때는 object가 없어도 되니까 null을 넘기면 된다.
        */
        Field a = Book2.class.getDeclaredField("A");
        System.out.println("1 : " + a.get(null));
        a.set(null, "AAAAA");
        System.out.println("2 : " + a.get(null));

        Field b = Book2.class.getDeclaredField("B");
        // private 이기 때문에
        b.setAccessible(true);
        System.out.println("1 : " + b.get(book));
        b.set(book, "BBBBBB");
        System.out.println("1 : " + b.get(book));


        // 메소드 실행하기
        // Object Method.invoke(object, params)
        Method c = Book2.class.getDeclaredMethod("c");
        // private 이면 setAccessible true 로 실행 가능.
        //c.setAccessible(true);
        c.invoke(book); // 파라미터 없기 때문에 그냥 호출

        // 파라미터 타입을 준다.
        Method sum = Book2.class.getDeclaredMethod("sum", int.class, int.class);
        int invoke = (int) sum.invoke(book, 1,2);
        System.out.println(invoke);
    }

    public void annotation(){
        // annotation_study
        System.out.println("MyBook 이 상속받는 Book이 가지고 있는 어노테이션까지 ");
        Arrays.stream(MyBook.class.getAnnotations()).forEach(System.out::println);

        System.out.println("MyBook에 있는 어노테이션만 보고싶다면");
        Arrays.stream(MyBook.class.getDeclaredAnnotations()).forEach(System.out::println);

        System.out.println("필드에 붙은 어노테이션 등 다양한 방식 응용 가능");
        Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
            Arrays.stream(f.getAnnotations()).forEach(System.out::println);
            Arrays.stream(f.getAnnotations()).forEach(a -> {
                FieldAnnotation fieldAnnotation = (FieldAnnotation) a;
                System.out.println(fieldAnnotation.name());
                System.out.println(fieldAnnotation.number());
            });
        });
    }


    public void reflection_study() throws NoSuchFieldException {
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

        Arrays.stream(MyBook.class.getMethods()).forEach(f -> {
            int modifiers = f.getModifiers();
            System.out.println(f);
            System.out.println(Modifier.isPrivate(modifiers));
            System.out.println(Modifier.isStatic(modifiers));
        });
    }
}
