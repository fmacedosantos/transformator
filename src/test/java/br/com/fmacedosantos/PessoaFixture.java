package br.com.fmacedosantos;

public class PessoaFixture {

    public static Pessoa buildPessoa() {
        return new Pessoa(1, "João", "1234");
    }

    public static Pessoa buildPessoaSemCpf(){
        return new Pessoa("João");
    }
}
