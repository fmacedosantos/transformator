package br.com.fmacedosantos.reflection;

public class Transformator {

    public <I, O> O transform(I input) throws ClassNotFoundException {
        Class<?> source = input.getClass();
        Class<?> target = Class.forName(source.getName() + "DTO");
    }
}
