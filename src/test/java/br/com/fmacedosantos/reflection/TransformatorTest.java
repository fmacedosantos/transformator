package br.com.fmacedosantos.reflection;

import br.com.fmacedosantos.Endereco;
import br.com.fmacedosantos.Pessoa;
import br.com.fmacedosantos.PessoaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TransformatorTest {

    Pessoa pessoa = new Pessoa(1, "João", "1234");
    Endereco endereco = new Endereco("Rua 10", 20);

    Transformator transformator;

    @BeforeEach
    public void setUp(){
        transformator = new Transformator();
    }

    @Test
    @DisplayName("Should transform Entity to DTO ensuring type and field values match")
    public void shouldTransformEntityToDtoWithMatchingValues() throws Exception {
        // Act
        PessoaDTO pessoaDTO = transformator.transform(pessoa);

        // Assert
        Assertions.assertAll("Verify DTO properties",
                () -> Assertions.assertInstanceOf(PessoaDTO.class, pessoaDTO),
                () -> Assertions.assertEquals(pessoa.getNome(), pessoaDTO.getNome()),
                () -> Assertions.assertEquals(pessoa.getCpf(), pessoaDTO.getCpf())
        );
    }

    @Test
    @DisplayName("Should throw ClassNotFoundException when the target DTO class does not exist")
    public void shouldThrowExceptionWhenDtoClassIsMissing(){
        Assertions.assertThrows(ClassNotFoundException.class, () -> {
            transformator.transform(endereco);
        });
    }
}
