package br.com.fmacedosantos.reflection;

import br.com.fmacedosantos.Pessoa;
import br.com.fmacedosantos.PessoaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class TransformatorTest {

    Pessoa pessoa = new Pessoa(1, "João", "1234");

    @Test
    public void shouldTransform() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Transformator transformator = new Transformator();

        PessoaDTO pessoaDTO = transformator.transform(pessoa);

        Assertions.assertInstanceOf(PessoaDTO.class, pessoaDTO);
        Assertions.assertEquals(pessoaDTO.getNome(), pessoa.getNome());
        Assertions.assertEquals(pessoaDTO.getCpf(), pessoa.getCpf());
    }
}
