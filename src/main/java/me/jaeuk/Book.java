package me.jaeuk;

//@MyAnnotation(name = "jaeuk", number = 100)
@MyAnnotation()
public class Book {
    private static String B = "BOOK";
    private static final String C = "BOOK";

    private String a;

    @FieldAnnotation
    public String d = "d";

    protected  String e = "e";

    public Book() {
    }

    public Book(String a, String d, String e) {
        this.a = a;
        this.d = d;
        this.e = e;
    }

    public void f(){
        System.out.println("F");
    }

    public void g(){
        System.out.println("g");
    }

    public int h(){
        return 100;
    }
}
