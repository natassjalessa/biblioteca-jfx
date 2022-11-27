package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.model.Autor;
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

public class AutorController implements Initializable {
    
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSobrenome;

    @FXML
    private TextField txtNacionalidade;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnIncluir;

    @FXML
    private Button btnRegistrar;

    @FXML
    private TableView<Autor> tabelaAutor = new TableView<Autor>();

    @FXML
    private TableColumn<Autor,Long> colID = new TableColumn<>();

    @FXML
    private TableColumn<Autor,String> colNome = new TableColumn<>();

    @FXML
    private TableColumn<Autor,String> colSobrenome = new TableColumn<>();

    @FXML
    private TableColumn<Autor,String> colNacionalidade = new TableColumn<>();
    
    DaoAutor dao = new DaoAutor();

    private Autor autor;

    private Boolean incluindo;
    
    @FXML
    private void registrar_click(ActionEvent event) {
        
        autor.setNome(txtNome.getText());
        autor.setSobrenome(txtSobrenome.getText());
        autor.setNacionalidade(txtNacionalidade.getText());

        if (incluindo) {
            dao.inserir(autor);
        } else {
            dao.alterar(autor);
        }

        preencherTabela();
        editar(false);
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);

        incluindo = true;

        autor = new Autor();
        txtNome.setText("");
        txtSobrenome.setText("");
        txtNacionalidade.setText("");
        txtNome.requestFocus();
    }

    @FXML
    private void alterar_click(ActionEvent event) {
        editar(true);

        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(autor);
        preencherTabela();
    }

    @FXML
    private void tblAutor_KeyPressed(MouseEvent event) {
        exibirDados();
    }

    @FXML
    private void tblAutor_MouseClicked(MouseEvent event) {
        exibirDados();
    }

    private void editar(boolean habilitar) {
        tabelaAutor.setDisable(habilitar);
        txtNome.setDisable(!habilitar);
        txtSobrenome.setDisable(!habilitar);
        txtNacionalidade.setDisable(!habilitar);
        btnRegistrar.setDisable(!habilitar);
        btnAlterar.setDisable(habilitar);
        btnIncluir.setDisable(habilitar);
        btnExcluir.setDisable(habilitar);


    }


    private void exibirDados() {
    
        this.autor = tabelaAutor.getSelectionModel().getSelectedItem();

        if (autor==null) return;

        txtNome.setText(autor.getNome());
        txtSobrenome.setText(autor.getSobrenome());
        txtNacionalidade.setText(autor.getNacionalidade());
    }

    private void preencherTabela() {
        List<Autor> autores = dao.buscarTodos();

        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        tabelaAutor.setItems(data);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colID.setCellValueFactory(new PropertyValueFactory<Autor,Long>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<Autor,String>("Nome"));
        colSobrenome.setCellValueFactory(new PropertyValueFactory<Autor,String>("Sobrenome"));
        colNacionalidade.setCellValueFactory(new PropertyValueFactory<Autor,String>("Nacionalidade"));


        preencherTabela();

    }    
}
