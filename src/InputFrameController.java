import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

/**
 * The InputFrameController class.  It controls input from the users and validates it.
 * If validation is successful, the Adjacency game screen will pop up in a different window.
 *
 * @author Jedid Ahn
 *
 */
public class InputFrameController{

    public CheckBox isBotFirst;
    public CheckBox vsBot;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label playerNameLabel;

    @FXML
    private TextField player1;

    @FXML
    private Label botNameLabel;

    @FXML
    private TextField player2;

    @FXML
    private ComboBox<String> numberOfRounds;

    @FXML
    private ComboBox<String> botTypeO;

    @FXML
    private ComboBox<String> botTypeX;

    @FXML
    private Label botTypeXLabel;


    /**
     * Initialize the dropdown ComboBox with a list of items that are allowed to be selected.
     * Select the first item in the list as the default value of the dropdown.
     *
     */
    @FXML
    private void initialize(){
        ObservableList<String> numberOfRoundsDropdown = FXCollections.observableArrayList(
                "", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28");
        ObservableList<String> botTypes = FXCollections.observableArrayList("Local", "Minimax", "Genetic");

        Label pNameLabel = this.playerNameLabel;
        Label bNameLabel = this.botNameLabel;
        Label botTypeXLabel = this.botTypeXLabel;
        TextField pl1 = this.player1;
        TextField pl2 = this.player2;
        ComboBox<String> botTypeX = this.botTypeX;

        gridPane.getChildren().remove(this.botTypeXLabel);
        gridPane.getChildren().remove(this.botTypeX);

        this.vsBot.setOnAction(e -> {
            if (this.vsBot.isSelected()) {
                gridPane.getChildren().remove(this.playerNameLabel);
                gridPane.getChildren().remove(this.player1);
                gridPane.getChildren().remove(this.botNameLabel);
                gridPane.getChildren().remove(this.player2);
                gridPane.add(botTypeXLabel, 0, 6);
                gridPane.add(botTypeX, 1, 6);
            } else {
                gridPane.add(pNameLabel, 0, 1);
                gridPane.add(pl1, 1, 1);
                gridPane.add(bNameLabel, 0, 2);
                gridPane.add(pl2, 1, 2);
                gridPane.getChildren().remove(this.botTypeXLabel);
                gridPane.getChildren().remove(this.botTypeX);
            }
        });

        this.numberOfRounds.setItems(numberOfRoundsDropdown);
        this.botTypeO.setItems(botTypes);
        this.botTypeO.getSelectionModel().selectLast();
        this.botTypeX.setItems(botTypes);
        this.botTypeX.getSelectionModel().selectLast();

        this.numberOfRounds.getSelectionModel().select(0);
    }


    /**
     * Reset player1 and player2 text fields and reset numberOfRounds dropdown to default value
     * if reset button is clicked.
     *
     */
    @FXML
    private void reset(){
        this.player1.setText("");
        this.player2.setText("");
        this.numberOfRounds.getSelectionModel().select(0);
        this.botTypeO.getSelectionModel().selectLast();
        this.botTypeX.getSelectionModel().selectLast();
    }


    /**
     * Open OutputFrame controlled by OutputFrameController if play button is clicked and
     * all input have been successfully validated.
     *
     * @exception IOException To load the FXMLLoader to open the Adjacency game screen (output screen).
     *
     */
    @FXML
    private void play() throws IOException{
        if (this.isInputFieldValidated()){
            // Close primary stage/input frame.

            Stage primaryStage = (Stage) this.botTypeO.getScene().getWindow();
            primaryStage.close();


            FXMLLoader loader = new FXMLLoader(getClass().getResource("OutputFrame.fxml"));
            Parent root = loader.load();

            // Get controller of output frame and pass input including player names and number of rounds chosen.
            OutputFrameController outputFC = loader.getController();
            outputFC.getInput(this.player1.getText(), this.player2.getText(), this.numberOfRounds.getValue(), this.isBotFirst.isSelected(), this.botTypeX.getValue(), this.botTypeO.getValue(), this.vsBot.isSelected());

            // Open the new frame.
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle("Game Board Display");
            secondaryStage.setScene(new Scene(root));
            secondaryStage.setResizable(true);
            secondaryStage.show();
        }
    }


    /**
     * Return whether all input fields have been successfully validated or not.
     *
     * @return boolean
     *
     */
    private boolean isInputFieldValidated() {
        String playerX = this.player1.getText();
        String playerO = this.player2.getText();
        String roundNumber = this.numberOfRounds.getValue();

        if (this.vsBot.isSelected()) {
            playerX = this.botTypeX.getValue() + "X";
            playerO = this.botTypeO.getValue() + "O";
        }

        if (playerX.length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Player 1 name is blank.").showAndWait();
            return false;
        }

        if (playerO.length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Player 2 name is blank.").showAndWait();
            return false;
        }

        if (playerX.equals(playerO)){
            new Alert(Alert.AlertType.ERROR, "Player 1 and Player 2 cannot have the same name.").showAndWait();
            return false;
        }

        if (roundNumber.length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Number of rounds dropdown menu is blank.").showAndWait();
            return false;
        }

        return true;
    }
}