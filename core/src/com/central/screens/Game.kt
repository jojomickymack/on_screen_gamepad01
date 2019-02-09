package com.central.screens

import com.badlogic.gdx.Gdx
import com.central.App
import com.central.AppObj
import ktx.app.KtxScreen
import com.badlogic.gdx.graphics.Color.*
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.*
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Window

class Game(val application: App) : KtxScreen {
    private val sr = AppObj.sr
    private var rotation = 0.0f
    private val width = Gdx.graphics.width.toFloat()
    private val height = Gdx.graphics.height.toFloat()
    private val textWindow = Window("", AppObj.skin)
    private var label = Label("nothing", AppObj.skin)
    private var buttonChange = false

    init {
        AppObj.stg.addActor(textWindow)

        AppObj.hudStg.addActor(AppObj.osc)
        Gdx.input.inputProcessor = AppObj.hudStg

        textWindow.setSize(AppObj.hudStg.width / 4, AppObj.hudStg.height / 8)
        textWindow.setPosition(AppObj.hudStg.width / 2 - textWindow.width / 2, AppObj.hudStg.height / 2 - textWindow.height / 2)
        textWindow.isVisible = false
        textWindow.add(label)
    }

    override fun render(delta: Float) {
        rotation += 1f

        with(sr) {
            begin(Line)
            translate(width / 2, height / 2, 0f)
            rotate(0f, 0f, 1f, rotation)
            color = WHITE
            rect(0f - 75, 0f - 75, 150f, 150f)
            end()

            begin(Filled)
            identity()
            color = RED
            circle(0f, 0f, 200f, 25)

            color = PINK
            circle(0f, height, 200f, 25)

            color = YELLOW
            circle(width, height, 200f, 25)

            color = BLUE
            circle(width, 0f, 200f, 25)
            end()
        }

        AppObj.stg.act(delta)
        AppObj.stg.draw()

        AppObj.hudStg.act(delta)
        AppObj.hudStg.draw()

        with(AppObj.ic) {
            if (!lPressed && !rPressed && !aPressed && !bPressed) {
                buttonChange = false
                textWindow.isVisible = false
            } else {
                if (!buttonChange) {
                    buttonChange = true
                    if (lPressed) label.setText("left")
                    if (rPressed) label.setText("right")
                    if (aPressed) label.setText("a")
                    if (bPressed) label.setText("b")
                    textWindow.isVisible = true
                }
            }
        }
    }
}
