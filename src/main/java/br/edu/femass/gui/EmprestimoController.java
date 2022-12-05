package br.edu.femass.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Emprestimo;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Leitor;
import br.edu.femass.model.Livro;
import br.edu.femass.model.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;




public class EmprestimoController implements Initializable {
    
    @FXML
    private ComboBox<Aluno> cboAluno;

    @FXML
    private ComboBox<Professor> cboProfessor;

    @FXML
    private TableView<Exemplar> tabelaExemplar = new TableView<>();

    @FXML
    private TableColumn<Exemplar,Long> colID;

    @FXML
    private TableColumn<Livro,String> colExemplar;

    @FXML
    private TableColumn<Exemplar,LocalDate> colRegistro;

    @FXML
    private TableView<Emprestimo> tabelaEmprestimo = new TableView<>();

    @FXML
    private TableColumn<Emprestimo,Long> colIDEmprestimo;

    @FXML
    private TableColumn<Exemplar,String> colExemplarEmprestimo;

    @FXML
    private TableColumn<Leitor,String> colLeitor;

    @FXML
    private TableColumn<Emprestimo,LocalDate> colDataEmprestimo;

    DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
    DaoExemplar daoExemplar = new DaoExemplar();
    DaoAluno daoAluno = new DaoAluno();
    DaoProfessor daoProfessor = new DaoProfessor();

    private Exemplar exemplar;
    private Emprestimo emprestimo;
    private Aluno aluno; 
    private Professor professor;

    @FXML
    private void registrarAluno_click(ActionEvent event) {

        preencherCombo();

        exemplar = tabelaExemplar.getSelectionModel().getSelectedItem();
        aluno = cboAluno.getSelectionModel().getSelectedItem();

        emprestimo = new Emprestimo(exemplar,aluno);

        daoEmprestimo.inserir(emprestimo);

        preencherTabela();
    }

    @FXML
    private void registrarProfessor_click(ActionEvent event) {

        preencherCombo();

        exemplar = tabelaExemplar.getSelectionModel().getSelectedItem();
        professor = cboProfessor.getSelectionModel().getSelectedItem();

        emprestimo = new Emprestimo(exemplar,professor);

        daoEmprestimo.inserir(emprestimo);

        preencherTabela();
    }

    //CONFIGURAÇÃO

    private void preencherCombo() {
        //COMBO ALUNO
        List<Aluno> alunos = daoAluno.buscarTodos();

        ObservableList<Aluno> dataAluno = FXCollections.observableArrayList(alunos);
        cboAluno.setItems(dataAluno);

        //COMBO PROFESSOR
        List<Professor> professores = daoProfessor.buscarTodos();

        ObservableList<Professor> dataProfessor = FXCollections.observableArrayList(professores);
        cboProfessor.setItems(dataProfessor);
        

    }

    private void preencherTabela() {

        //TABELA EXEMPLAR
        List<Exemplar> exemplares = daoExemplar.buscarTodos();

        ObservableList<Exemplar> dataExemplar = FXCollections.observableArrayList(exemplares);
        tabelaExemplar.setItems(dataExemplar);

        //TABELA EMPRESTIMO
        List<Emprestimo> emprestimos = daoEmprestimo.buscarTodos();
        
        ObservableList<Emprestimo> dataEmprestimo = FXCollections.observableArrayList(emprestimos);
        tabelaEmprestimo.setItems(dataEmprestimo);
    }
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //EXEMPLAR
        colID.setCellValueFactory(new PropertyValueFactory<Exemplar,Long>("id"));
        colExemplar.setCellValueFactory(new PropertyValueFactory<Livro,String>("livro"));
        colRegistro.setCellValueFactory(new PropertyValueFactory<Exemplar,LocalDate>("dataAquisicao"));

        //EMPRESTIMO
        colIDEmprestimo.setCellValueFactory(new PropertyValueFactory<Emprestimo,Long>("id"));
        colExemplarEmprestimo.setCellValueFactory(new PropertyValueFactory<Exemplar,String>("exemplar"));
        colLeitor.setCellValueFactory(new PropertyValueFactory<Leitor,String>("leitor"));
        colDataEmprestimo.setCellValueFactory(new PropertyValueFactory<Emprestimo,LocalDate>("dataEmprestimo"));

        emprestimo = new Emprestimo();
        preencherTabela();
        preencherCombo();
    }    
}
