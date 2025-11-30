package br.com.fmacedosantos.reflection;

import br.com.fmacedosantos.Pessoa;
import br.com.fmacedosantos.PessoaFixture;

class JsonSerializerTest {

    public static void main(String[] args) {
        Pessoa pessoa = PessoaFixture.buildPessoa();
        JsonSerializer serializer = new JsonSerializer();

        System.out.println(serializer.serialize(pessoa));
    }
}