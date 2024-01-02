package org.carte.dbmsapp

import atlantafx.base.theme.PrimerDark
import atlantafx.base.theme.PrimerLight
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.text.Font
import javafx.stage.Stage


class JavaFxApplication : Application() {

    override fun start(stage: Stage) {
        setUserAgentStylesheet(PrimerDark().userAgentStylesheet)

        val fxmlLoader = FXMLLoader(javaClass.getResource("/login-view.fxml"))
        val scene = Scene(fxmlLoader.load())

        stage.title = "Postgresql RDBMS Client"
        stage.scene = scene
        stage.show()
    }
}

fun main() {



    Application.launch(JavaFxApplication::class.java)
}