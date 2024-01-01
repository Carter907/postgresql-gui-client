package org.carte.dbmsapp.controllers

import javafx.event.ActionEvent
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import org.carte.dbmsapp.database.ClientCredentials
import org.carte.dbmsapp.database.PostgresClient
import org.carte.dbmsapp.database.PostgresLoginException
import java.net.URL
import java.util.*

class LoginController : Initializable {

    lateinit var connectButton: Button
    lateinit var passwordField: PasswordField
    lateinit var usernameField: TextField
    lateinit var databaseNameField: TextField

    private lateinit var postgresClient: PostgresClient

    override fun initialize(p0: URL?, p1: ResourceBundle?) {


        connectButton.setOnAction (::onConnectButtonAction)
    }

    private fun onConnectButtonAction(event: ActionEvent) {
        postgresClient = PostgresClient(
            databaseNameField.text,
            ClientCredentials(
                usernameField.text,
                passwordField.text
            )
        );
        try {
            postgresClient.login();
            (event.source as Node).scene.root = FXMLLoader.load(javaClass.getResource("/main-view.fxml"));
        } catch (e: PostgresLoginException) {
            System.err.println("failed to login: ${e.message}")
        }
    }
}