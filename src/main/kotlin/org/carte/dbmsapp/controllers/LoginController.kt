package org.carte.dbmsapp.controllers

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import org.carte.dbmsapp.database.ClientCredentials
import org.carte.dbmsapp.database.PostgresClient
import org.carte.dbmsapp.database.PostgresLoginException
import org.carte.dbmsapp.extensions.changeFxmlSceneRoot
import java.net.URL
import java.util.*

class LoginController : Initializable {

    @FXML
    lateinit var connectButton: Button

    @FXML
    lateinit var passwordField: PasswordField

    @FXML
    lateinit var usernameField: TextField

    @FXML
    lateinit var databaseNameField: TextField

    private lateinit var postgresClient: PostgresClient

    override fun initialize(p0: URL?, p1: ResourceBundle?) {

        if (javaClass.getResource("/login-info.json") != null) {





            attemptLogin(connectButton)
        }


        connectButton.setOnAction (::onConnectButtonAction)
    }

    private fun onConnectButtonAction(event: ActionEvent) {
        attemptLogin(event.source as Node)
    }
    private fun attemptLogin(node: Node) {
        postgresClient = PostgresClient(
            databaseNameField.text,
            ClientCredentials(
                usernameField.text,
                passwordField.text
            )
        );
        try {
            postgresClient.login();

            val loader = FXMLLoader();

            val controller = node.changeFxmlSceneRoot<MainController>("/main-view.fxml")

            controller.client = postgresClient;

        } catch (e: PostgresLoginException) {
            System.err.println("failed to login: ${e.message}")
        }
    }
}