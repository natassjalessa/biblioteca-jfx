package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private ListView<Livro> lstLivro;

    DaoLivro dao = new DaoLivro();

    private Livro livro;

    private Boolean incluindo;

    @FXML
    private void registrar_click(ActionEvent event) {
        
        livro.setTitulo(txtTitulo.getText());

        if (incluindo) {
            dao.inserir(livro);
        } else {
            dao.alterar(livro);
        }

        preencherLista();
        preencherTabela();
        editar(false);
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);

        incluindo = true;

        livro = new Livro();
        txtTitulo.setText("");
        txtTitulo.requestFocus();
    }

    @FXML
    private void alterar_click(ActionEvent event) {
        editar(true);

        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(livro);
        preencherLista();
    }

    @FXML
    private void lstAutor_KeyPressed(MouseEvent event) {
        exibirDados();
    }

    @FXML
    private void lstAutor_MouseClicked(MouseEvent event) {
        exibirDados();
    }

    private void editar(boolean habilitar) {
        lstLivro.setDisable(habilitar);
        txtTitulo.setDisable(!habilitar);
        btnRegistrar.setDisable(!habilitar);
        btnAlterar.setDisable(habilitar);
        btnIncluir.setDisable(habilitar);
        btnExcluir.setDisable(habilitar);
    }


    private void exibirDados() {
    
        this.livro = lstLivro.getSelectionModel().getSelectedItem();

        if (livro==null) return;

        txtTitulo.setText(livro.getTitulo());
    }

    private void preencherLista() {
        List<Livro> livros = dao.buscarTodos();

        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        lstLivro.setItems(data);
    }

    private void preencherTabela() {
        List<Livro> livros = dao.buscarTodos();

        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        tabelaLivro.setItems(data);
    }
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colTitulo.setCellValueFactory(new PropertyValueFactory<Livro, String>("titulo"));
        colID.setCellValueFactory(new PropertyValueFactory<Livro, Long>("id"));

        preencherLista();
        preencherTabela();

    }    
}
