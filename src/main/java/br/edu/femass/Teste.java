package br.edu.femass;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.TimeZone;

import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.model.Autor;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Livro;

public class Teste {

    public static void main(String[] args) {
        Autor autor = new Autor("TESTE", "TESTE", "TESTE");
        Livro livro = new Livro("TEST3", autor);
        Exemplar exemplar = new Exemplar(LocalDate.now(), livro);

        new DaoExemplar().inserir(exemplar);

    }
    
}
