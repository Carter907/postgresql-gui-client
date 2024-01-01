package org.carte.dbmsapp

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.text.Font
import javafx.stage.Stage

class JavaFxApplication : Application() {

    override fun start(stage: Stage) {
        Font.loadFont(javaClass.getResourceAsStream("/fonts/JetBrainsMono-Light.ttf"), 14.0)

        val fxmlLoader = FXMLLoader(javaClass.getResource("/login-view.fxml"))
        val scene = Scene(fxmlLoader.load())
        scene.stylesheets.add(javaClass.getResource("/style.css")!!.toExternalForm())

        stage.title = "Postgresql RDBMS Client"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(JavaFxApplication::class.java)
}