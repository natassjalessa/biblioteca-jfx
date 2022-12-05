package br.edu.femass.dao;

import java.util.List;

import br.edu.femass.model.Emprestimo;
import br.edu.femass.model.Exemplar;

public class DaoEmprestimo extends Dao<Emprestimo> {

    public List<Emprestimo> buscarTodos() {

        return em.createQuery("select e from Emprestimo e order by e.dataEmprestimo").getResultList();
    }
    
}
