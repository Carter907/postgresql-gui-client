package org.carte.dbmsapp.controllers

import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.TextArea
import javafx.scene.text.Font
import javafx.scene.text.Text
import org.carte.dbmsapp.database.PostgresClient
import java.io.BufferedReader
import java.io.InputStream
import java.io.PrintStream
import java.net.URL
import java.util.*

class MainController : Initializable {

    lateinit var client: PostgresClient;

    @FXML
    private lateinit var runButton: Button;

    @FXML
    private lateinit var queryArea: TextArea;

    @FXML
    private lateinit var consoleArea: TextArea;

    override fun initialize(p0: URL?, p1: ResourceBundle?) {


        runButton.setOnAction(::onRunButtonPressed)

    }

    private fun onRunButtonPressed(event: ActionEvent) {

        consoleArea.text = client.runSQL(queryArea.text)
    }

}