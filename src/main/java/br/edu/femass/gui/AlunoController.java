package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Leitor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class AlunoController implements Initializable {
    
   
    @FXML
    private TextField txtNomeAluno;

    @FXML
    private TextField txtEnderecoAluno;

    @FXML
    private TextField txtTelefoneAluno;

    @FXML
    private TextField txtMatricula;

    @FXML
    private TableView<Aluno> tabelaAluno = new TableView<Aluno>();

    @FXML
    private TableColumn<Leitor,Long> colID = new TableColumn<>();

    @FXML
    private TableColumn<Aluno,String> colNomeAluno = new TableColumn<>();

    @FXML
    private TableColumn<Aluno,String> colEndAluno = new TableColumn<>();

    @FXML
    private TableColumn<Aluno,String> colTelAluno = new TableColumn<>();

    @FXML
    private TableColumn<Aluno,String> colMatricula = new TableColumn<>();

    DaoAluno daoAluno = new DaoAluno();

    private Aluno aluno;

    private Boolean incluindo;

    @FXML
    private void registrar_click(ActionEvent event) {

        aluno = new Aluno();
        aluno.setNome(txtNomeAluno.getText());
        aluno.setEndereco(txtEnderecoAluno.getText());
        aluno.setTelefone(txtTelefoneAluno.getText());
        aluno.setMatricula(txtMatricula.getText());

        daoAluno.inserir(aluno);


        preencherTabela();
    }


    private void preencherTabela() {
        List<Aluno> alunos = daoAluno.buscarTodos();

        ObservableList<Aluno> data = FXCollections.observableArrayList(alunos);
        tabelaAluno.setItems(data);
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(new PropertyValueFactory<Leitor,Long>("ID"));
        colNomeAluno.setCellValueFactory(new PropertyValueFactory<Aluno,String>("Nome"));
        colEndAluno.setCellValueFactory(new PropertyValueFactory<Aluno,String>("Endereco"));
        colTelAluno.setCellValueFactory(new PropertyValueFactory<Aluno,String>("Telefone"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<Aluno,String>("Matricula"));

        preencherTabela();
    }    
}
