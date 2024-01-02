package org.carte.dbmsapp.controllers

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.TextArea
import org.carte.dbmsapp.database.PostgresClient
import java.net.URL
import java.util.*

class MainController : Initializable {

    lateinit var client: PostgresClient;
    @FXML
    private lateinit var runButton: Button;

    @FXML
    private lateinit var queryArea: TextArea;

    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        runButton.setOnAction(::onRunButtonPressed)
    }

    private fun onRunButtonPressed(event: ActionEvent) {
        client.runQuery(queryArea.text)
    }

}