package me.jaeuk;

public class Book2 {

    public static String A = "A";

    private String B = "B";

    public void c(){
        System.out.println("C");
    }

    public Book2() {
    }

    public Book2(String b) {
        B = b;
    }

    public int sum(int left, int right){
        return left + right;
    }
}
