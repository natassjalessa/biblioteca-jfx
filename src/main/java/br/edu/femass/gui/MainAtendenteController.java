package br.edu.femass.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainAtendenteController implements Initializable {
    
    @FXML
    private void cadastroAluno_click(ActionEvent event) {

        try { 
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Aluno.fxml"));
        
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");

            Stage stage = new Stage();
            stage.setTitle("Cadastro Aluno");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void cadastroProfessor_click(ActionEvent event) {

        try { 
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Professor.fxml"));
        
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");

            Stage stage = new Stage();
            stage.setTitle("Cadastro Professor");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

    }    
}
