package edu.austral.dissis.chess

import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import edu.austral.ingsis.clientserver.netty.client.NettyClientBuilder
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.stage.Stage
import src.lan.client.GameClient

fun main() {
    val numberOfInstances = 3 // Change this to the desired number of instances

    repeat(numberOfInstances) {
        launch(App2::class.java)
    }
}


class App2  :  Application() {
    private val imageResolver = CachedImageResolver(DefaultImageResolver())
    private val root = GameView(imageResolver)
    private val builder = NettyClientBuilder.createDefault()
    private val client = GameClient(root, builder)

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Client"
        primaryStage.scene = Scene(root)
        primaryStage.show()
    }
}