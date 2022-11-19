package br.edu.femass.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long codigo;
    private String titulo;
    
    public Livro(Long codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
    }

    public Livro() {

    }

    public Long getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Long getId() {
        return id;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
    
}
