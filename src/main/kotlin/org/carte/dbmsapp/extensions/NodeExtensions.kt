package org.carte.dbmsapp.extensions

import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Node
import javafx.scene.Parent

fun Node.changeSceneRoot(newRoot: Parent) {
    scene.root = newRoot;
}
fun <T: Initializable>Node.changeFxmlSceneRoot(fxmlLocation: String): T {
    val fxmlLoader: FXMLLoader = FXMLLoader()

    scene.root = fxmlLoader.load(javaClass.getResourceAsStream(fxmlLocation))

    return fxmlLoader.getController()
}