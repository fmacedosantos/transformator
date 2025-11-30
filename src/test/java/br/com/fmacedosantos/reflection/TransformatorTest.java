package br.com.fmacedosantos.reflection;

import br.com.fmacedosantos.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TransformatorTest {

    Transformator transformator;

    @BeforeEach
    public void setUp(){
        transformator = new Transformator();
    }

    @Test
    @DisplayName("Should transform Entity to DTO ensuring type and field values match")
    public void shouldTransformEntityToDtoWithMatchingValues() throws Exception {
        // Arrange
        Pessoa pessoa = PessoaFixture.buildPessoa();

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
        // Arrange
        Endereco endereco = EnderecoFixture.buildEndereco();

        // Act & Assert
        Assertions.assertThrows(ClassNotFoundException.class, () -> {
            transformator.transform(endereco);
        });
    }

    @Test
    @DisplayName("Should transform Entity to DTO preserving null values when source field is null")
    public void shouldTransformEntityToDtoWhenSourceFieldIsNull() throws Exception {
        // Arrange
        Pessoa pessoaSemCpf = PessoaFixture.buildPessoaSemCpf();

        // Act
        PessoaDTO pessoaDTO = transformator.transform(pessoaSemCpf);

        // Assert
        Assertions.assertAll("Verify DTO properties handling nulls",
                () -> Assertions.assertEquals(pessoaSemCpf.getNome(), pessoaDTO.getNome()),
                () -> Assertions.assertNull(pessoaDTO.getCpf())
        );
    }
}
