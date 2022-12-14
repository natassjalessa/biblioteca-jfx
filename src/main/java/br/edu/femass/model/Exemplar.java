package br.edu.femass.model;

import java.time.Clock;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataAquisicao;
    

    @ManyToOne(cascade = CascadeType.ALL)
    private Livro livro;

    public Exemplar(LocalDate dataAquisicao, Livro livro) {
        this.livro = livro;
    }

    public Exemplar() {
        this.dataAquisicao = LocalDate.now();
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public Livro getLivro() {
        return livro;
    }

    public Long getId() {
        return id;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return this.getLivro().toString();
    }
}
