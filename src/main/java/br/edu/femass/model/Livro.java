package br.edu.femass.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long codigo;
    private static Long proximoLivroCodigo = 1L;
    private String titulo;
    
    public Livro(Long codigo, String titulo) {
        this.codigo = proximoLivroCodigo;
        proximoLivroCodigo++;
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

    @Override
    public String toString() {
        return this.codigo + " " + this.titulo;
    }

    
    
}
