package br.edu.femass.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Autor> autores;
    
    public Livro(String titulo) {
        this.titulo = titulo;

    }

    public Livro() {

    }


    public String getTitulo() {
        return titulo;
    }

    public Long getId() {
        return id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void adicionarAutor(String nome, String sobrenome, String nacionalidade) {
        if (autores==null) autores = new ArrayList();
        autores.add(new Autor(nome, sobrenome, nacionalidade));
    }

    @Override
    public String toString() {
        return this.titulo;
    }

    
    
}
