package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.model.Professor;
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


public class ProfessorController implements Initializable {
    
    @FXML
    private TextField txtNomeProfessor;

    @FXML
    private TextField txtEnderecoProfessor;

    @FXML
    private TextField txtTelefoneProfessor;

    @FXML
    private TextField txtDisciplina;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnIncluir;

    @FXML
    private Button btnRegistrar;

    @FXML
    private TableView<Professor> tabelaProfessor = new TableView<Professor>();

    @FXML
    private TableColumn<Professor,Long> colID = new TableColumn<>();

    @FXML
    private TableColumn<Professor,String> colNomeProfessor = new TableColumn<>();

    @FXML
    private TableColumn<Professor,String> colEndProfessor = new TableColumn<>();

    @FXML
    private TableColumn<Professor,String> colTelProfessor = new TableColumn<>();

    @FXML
    private TableColumn<Professor,String> colDisciplina = new TableColumn<>();

    DaoProfessor daoProfessor = new DaoProfessor();

    private Professor professor;
   
    private Boolean incluindo;

    //BOTOES

    @FXML
    private void registrar_click(ActionEvent event) {

        professor.setNome(txtNomeProfessor.getText());
        professor.setEndereco(txtEnderecoProfessor.getText());
        professor.setTelefone(txtTelefoneProfessor.getText());
        professor.setDisciplina(txtDisciplina.getText());

        if (incluindo) {
            daoProfessor.inserir(professor);
        }
        else {
            daoProfessor.inserir(professor);
        }

        preencherTabela();
        editar(false);
        
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);

        incluindo = true;

        professor = new Professor();
        txtNomeProfessor.setText("");
        txtEnderecoProfessor.setText("");
        txtTelefoneProfessor.setText("");
        txtDisciplina.setText("");
        txtNomeProfessor.requestFocus();
        
    }

    @FXML
    private void alterar_click(ActionEvent event) {
        editar(true);

        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        daoProfessor.apagar(professor);
        preencherTabela();
    }

    @FXML
    private void tblProfessor_KeyPressed(MouseEvent event) {
        exibirDados();
    }

    @FXML
    private void tblProfessor_MouseClicked(MouseEvent event) {
        exibirDados();
    }

    //CONFIGURAÇÃO

    private void editar(boolean habilitar) {
        tabelaProfessor.setDisable(habilitar);
        txtNomeProfessor.setDisable(!habilitar);
        txtEnderecoProfessor.setDisable(!habilitar);
        txtTelefoneProfessor.setDisable(!habilitar);
        txtDisciplina.setDisable(!habilitar);
        btnRegistrar.setDisable(!habilitar);
        btnAlterar.setDisable(habilitar);
        btnIncluir.setDisable(habilitar);
        btnExcluir.setDisable(habilitar);

    }

    private void exibirDados() {
        this.professor = tabelaProfessor.getSelectionModel().getSelectedItem();

        if (professor==null) return;

        txtNomeProfessor.setText(professor.getNome());
        txtEnderecoProfessor.setText(professor.getEndereco());
        txtTelefoneProfessor.setText(professor.getTelefone());
        txtDisciplina.setText(professor.getDisciplina());
        
    }


    private void preencherTabela() {
        List<Professor> professores = daoProfessor.buscarTodos();

        ObservableList<Professor> data = FXCollections.observableArrayList(professores);
        tabelaProfessor.setItems(data);
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colID.setCellValueFactory(new PropertyValueFactory<Professor, Long>("id"));
        colNomeProfessor.setCellValueFactory(new PropertyValueFactory<Professor,String>("Nome"));
        colEndProfessor.setCellValueFactory(new PropertyValueFactory<Professor,String>("Endereco"));
        colTelProfessor.setCellValueFactory(new PropertyValueFactory<Professor,String>("Telefone"));
        colDisciplina.setCellValueFactory(new PropertyValueFactory<Professor,String>("Disciplina"));
        
        preencherTabela();
    }    
}
