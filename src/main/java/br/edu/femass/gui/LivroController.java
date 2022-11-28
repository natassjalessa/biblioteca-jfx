package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Autor;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class LivroController implements Initializable {
    
    @FXML
    private TextField txtTitulo;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnIncluir;

    @FXML
    private Button btnRegistrar;

    @FXML
    private TableView<Livro> tabelaLivro = new TableView<Livro>();

    @FXML
    private TableColumn<Livro,String> colTitulo = new TableColumn<>();

    @FXML
    private TableColumn<Livro,Long> colID = new TableColumn<>();

    @FXML
    private TableColumn<Autor,String> colAutor = new TableColumn<>();

    @FXML
    private ComboBox<Autor> cboAutor;

    DaoAutor daoAutor = new DaoAutor();

    DaoLivro dao = new DaoLivro();

    private Livro livro;
    private Autor autor;

    private Boolean incluindo;

    @FXML
    private void registrar_click(ActionEvent event) {
        
        livro.setTitulo(txtTitulo.getText());

        if (incluindo) {
            dao.inserir(livro);
        } else {
            dao.alterar(livro);
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
        autor = new Autor();
        txtTitulo.setText("");
        txtTitulo.requestFocus();
    }

    @FXML
    private void alterar_click(ActionEvent event) {
        editar(true);
        preencherCombo();

        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(livro);
        preencherTabela();
    }

    @FXML
    private void tblAutor_KeyPressed(KeyEvent event) {
        exibirDados();
    }

    @FXML
    private void cboAutor_KeyPressed(KeyEvent event) {
        exibirDados();
    }


    @FXML
    private void tblAutor_MouseClicked(MouseEvent event) {
        exibirDados();
    }

    @FXML
    private void cboAutor_MouseClicked(MouseEvent event) {
        exibirDados();
    }
    


    //CONFIGURAÇÃO

    private void editar(boolean habilitar) {
        tabelaLivro.setDisable(habilitar);
        txtTitulo.setDisable(!habilitar);
        cboAutor.setDisable(!habilitar);
        btnRegistrar.setDisable(!habilitar);
        btnAlterar.setDisable(habilitar);
        btnIncluir.setDisable(habilitar);
        btnExcluir.setDisable(habilitar);
    }


    private void exibirDados() {
    
        this.livro = tabelaLivro.getSelectionModel().getSelectedItem();

        if (livro==null) return;

        txtTitulo.setText(livro.getTitulo());
    }

    private void preencherCombo() {
        List<Autor> autores = daoAutor.buscarTodos();

        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        cboAutor.setItems(data);

    }

    private void preencherTabela() {
        List<Livro> livros = dao.buscarTodos();

        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        tabelaLivro.setItems(data);
    }
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colTitulo.setCellValueFactory(new PropertyValueFactory<Livro, String>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<Autor, String>("autor"));
        colID.setCellValueFactory(new PropertyValueFactory<Livro, Long>("id"));

        preencherTabela();

    }    
}
