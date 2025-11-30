package br.com.fmacedosantos;

public class Pessoa {

    private Integer id;
    private String nome;
    private String cpf;

    public Pessoa(Integer id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
}
