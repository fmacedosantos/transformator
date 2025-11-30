package br.com.fmacedosantos;

import br.com.fmacedosantos.reflection.EntityMapper;

import java.lang.reflect.InvocationTargetException;

public class PessoaService {

    public PessoaDTO list(){
        Pessoa pessoa = new PessoaRepository().list();
        PessoaDTO pessoaDTO = null;

        try {
            pessoaDTO = new EntityMapper().transform(pessoa);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }

        return pessoaDTO;
    }
}
