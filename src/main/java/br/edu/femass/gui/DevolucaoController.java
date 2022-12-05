package br.edu.femass.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.border.EmptyBorder;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Emprestimo;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Leitor;
import br.edu.femass.model.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;



public class DevolucaoController implements Initializable {
    
    @FXML
    private TableColumn<Exemplar,String> colExemplarEmprestimo;

    @FXML
    private TableColumn<Emprestimo,LocalDate> colDevolucao;

    @FXML
    private TableColumn<Emprestimo,Long> colIDEmprestimo;

    @FXML
    private TableColumn<Emprestimo,LocalDate> colDataPrevistaDevolucao;

    @FXML
    private TableColumn<Leitor,String> colLeitor;

    @FXML
    private TableView<Emprestimo> tabelaEmprestimo;
    
    DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
    DaoExemplar daoExemplar = new DaoExemplar();
    DaoAluno daoAluno = new DaoAluno();
    DaoProfessor daoProfessor = new DaoProfessor();

    private Exemplar exemplar;
    private Emprestimo emprestimo;
    private Aluno aluno; 
    private Professor professor;

    @FXML
    private void registrarDevolucao_click(ActionEvent event) {

        preencherTabela();

        emprestimo = tabelaEmprestimo.getSelectionModel().getSelectedItem();
        emprestimo.setDataDevolucao(LocalDate.now());

        daoEmprestimo.alterar(emprestimo);
        preencherTabela();

        
    }

    //CONFIGURAÇÃO

    private void preencherTabela() { 
        List<Emprestimo> emprestimos = daoEmprestimo.buscarTodos();
        
        ObservableList<Emprestimo> dataEmprestimo = FXCollections.observableArrayList(emprestimos);
        tabelaEmprestimo.setItems(dataEmprestimo);
    }

     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colIDEmprestimo.setCellValueFactory(new PropertyValueFactory<Emprestimo,Long>("id"));
        colExemplarEmprestimo.setCellValueFactory(new PropertyValueFactory<Exemplar,String>("exemplar"));
        colLeitor.setCellValueFactory(new PropertyValueFactory<Leitor,String>("leitor"));
        colDataPrevistaDevolucao.setCellValueFactory(new PropertyValueFactory<Emprestimo,LocalDate>("dataPrevisaoDevolucao"));
        colDevolucao.setCellValueFactory(new PropertyValueFactory<Emprestimo,LocalDate>("dataDevolucao"));

        emprestimo = new Emprestimo();
        preencherTabela();
    }    
}
