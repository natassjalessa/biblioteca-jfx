package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.model.Aluno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


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
    private Button btnAlterar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnIncluir;

    @FXML
    private Button btnRegistrar;

    @FXML
    private TableView<Aluno> tabelaAluno = new TableView<Aluno>();

    @FXML
    private TableColumn<Aluno,Long> colID = new TableColumn<>();

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

    //BOTOES

    @FXML
    private void registrar_click(ActionEvent event) {

        aluno.setNome(txtNomeAluno.getText());
        aluno.setEndereco(txtEnderecoAluno.getText());
        aluno.setTelefone(txtTelefoneAluno.getText());
        aluno.setMatricula(txtMatricula.getText());

        if (incluindo) {
            daoAluno.inserir(aluno);
        }
        else {
            daoAluno.alterar(aluno);
        }


        preencherTabela();
        editar(false);
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);

        incluindo = true;

        aluno = new Aluno();
        txtNomeAluno.setText("");
        txtEnderecoAluno.setText("");
        txtTelefoneAluno.setText("");
        txtMatricula.setText("");
        txtNomeAluno.requestFocus();
    }

    @FXML
    private void alterar_click(ActionEvent event) {
        editar(true);

        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        daoAluno.apagar(aluno);
        preencherTabela();
    }

    @FXML
    private void tblAluno_KeyPressed(MouseEvent event) {
        exibirDados();
    }

    @FXML
    private void tblAluno_MouseClicked(MouseEvent event) {
        exibirDados();
    }


    //CONFIGURAÇÃO

    private void editar(boolean habilitar) {
        tabelaAluno.setDisable(habilitar);
        txtNomeAluno.setDisable(!habilitar);
        txtEnderecoAluno.setDisable(!habilitar);
        txtTelefoneAluno.setDisable(!habilitar);
        txtMatricula.setDisable(!habilitar);
        btnRegistrar.setDisable(!habilitar);
        btnAlterar.setDisable(habilitar);
        btnIncluir.setDisable(habilitar);
        btnExcluir.setDisable(habilitar);


    }

    private void exibirDados() {

        this.aluno = tabelaAluno.getSelectionModel().getSelectedItem();

        if (aluno==null) return;

        txtNomeAluno.setText(aluno.getNome());
        txtEnderecoAluno.setText(aluno.getEndereco());
        txtTelefoneAluno.setText(aluno.getTelefone());
        txtMatricula.setText(aluno.getMatricula());
    }


    private void preencherTabela() {
        List<Aluno> alunos = daoAluno.buscarTodos();

        ObservableList<Aluno> data = FXCollections.observableArrayList(alunos);
        tabelaAluno.setItems(data);
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(new PropertyValueFactory<Aluno, Long>("id"));
        colNomeAluno.setCellValueFactory(new PropertyValueFactory<Aluno,String>("Nome"));
        colEndAluno.setCellValueFactory(new PropertyValueFactory<Aluno,String>("Endereco"));
        colTelAluno.setCellValueFactory(new PropertyValueFactory<Aluno,String>("Telefone"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<Aluno,String>("Matricula"));

        preencherTabela();
    }    
}
