package br.edu.femass.dao;

import java.util.List;

import br.edu.femass.model.Professor;

public class DaoProfessor extends Dao<Professor> {

    public List<Professor> buscarTodos() {
        
        return em.createQuery("select p from Professor p order by p.nome").getResultList();
    }
    
}
