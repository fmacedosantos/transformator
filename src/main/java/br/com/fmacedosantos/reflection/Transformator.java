package br.com.fmacedosantos.reflection;

public class Transformator {

    public <I, O> transform(I input) throws ClassNotFoundException {
        Class<?> source = input.getClass();
        Class<?> target = Class.forName(source.getName() + "DTO");
    }
}
