package br.edu.femass.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;



public class ExemplarController implements Initializable {
    
    
    @FXML
    private ComboBox<Livro> cboLivro;

    @FXML
    private TableView<Exemplar> tabelaExemplar;

    @FXML
    private TableColumn<Livro,String> colExemplar;

    @FXML
    private TableColumn<Exemplar,Long> colID;

    @FXML
    private TableColumn<Exemplar,LocalDate> colRegistro;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnIncluir;

    DaoExemplar daoExemplar = new DaoExemplar();
    DaoLivro daoLivro = new DaoLivro();

    private Exemplar exemplar;
    private Livro livro;

    private Boolean incluindo;

    @FXML
    private void registrar_click(ActionEvent event) {

        exemplar.setLivro(cboLivro.getSelectionModel().getSelectedItem());

        if (incluindo) {
            daoExemplar.inserir(exemplar);
        } else {
            daoExemplar.alterar(exemplar);
        }

        preencherTabela();
        editar(false);
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        preencherCombo();

        incluindo = true;

        livro = new Livro();
        exemplar = new Exemplar();
        cboLivro.requestFocus();
    }

    @FXML
    private void alterar_click(ActionEvent event) {
        editar(true);
        preencherCombo();

        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        daoExemplar.apagar(exemplar);
        preencherTabela();
    }

    
    @FXML
    private void tblExemplar_KeyPressed(KeyEvent event) {
        exibirDados();
    }

    @FXML
    private void tblExemplar_MouseClicked(MouseEvent event) {
        exibirDados();
    }

    //CONFIGURAÇAO

    private void editar(boolean habilitar) {
        tabelaExemplar.setDisable(habilitar);
        cboLivro.setDisable(!habilitar);
        btnRegistrar.setDisable(!habilitar);
        btnAlterar.setDisable(habilitar);
        btnIncluir.setDisable(habilitar);
        btnExcluir.setDisable(habilitar);
    }

    private void exibirDados() {

        this.exemplar = tabelaExemplar.getSelectionModel().getSelectedItem();

        if (exemplar==null) return;

    }

    private void preencherCombo() {
        List<Livro> livros = daoLivro.buscarTodos();

        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        cboLivro.setItems(data);
    }

    private void preencherTabela() { 
        List<Exemplar> exemplares = daoExemplar.buscarTodos();

        ObservableList<Exemplar> data = FXCollections.observableArrayList(exemplares);
        tabelaExemplar.setItems(data);
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(new PropertyValueFactory<Exemplar,Long>("id"));
        colExemplar.setCellValueFactory(new PropertyValueFactory<Livro,String>("livro"));
        colRegistro.setCellValueFactory(new PropertyValueFactory<Exemplar,LocalDate>("dataAquisicao"));


        preencherTabela();

    }    
}
