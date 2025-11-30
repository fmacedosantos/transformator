package br.com.fmacedosantos.reflection;

import br.com.fmacedosantos.Pessoa;
import br.com.fmacedosantos.PessoaFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JsonSerializerTest {

    JsonSerializer jsonSerializer;

    @BeforeEach
    public void setUp() {
        jsonSerializer = new JsonSerializer();
    }

    @Test
    @DisplayName("Should serialize object to JSON string containing correct field names and values")
    public void shouldSerializeObjectToJsonContainingFieldValues() {
        // Arrange
        Pessoa pessoa = PessoaFixture.buildPessoa();

        // Act
        String jsonResult = jsonSerializer.serialize(pessoa);

        // Assert
        Assertions.assertAll("Verify JSON structure and content",
                () -> Assertions.assertNotNull(jsonResult),
                () -> Assertions.assertTrue(jsonResult.contains("\"nome\""), "JSON should contain 'nome' key"),
                () -> Assertions.assertTrue(jsonResult.contains("\"cpf\""), "JSON should contain 'cpf' key"),
                () -> Assertions.assertTrue(jsonResult.contains(pessoa.getNome()), "JSON should contain person name"),
                () -> Assertions.assertTrue(jsonResult.contains(pessoa.getCpf()), "JSON should contain person cpf")
        );
    }
}