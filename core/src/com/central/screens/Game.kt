package com.central.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.*
import com.badlogic.gdx.graphics.Color.*
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Window
import com.badlogic.gdx.utils.Align
import ktx.actors.centerPosition
import ktx.actors.plusAssign
import ktx.graphics.*
import ktx.scene2d.*
import ktx.app.KtxScreen
import com.central.App


class Game(val app: App) : KtxScreen {
    private val sr = ShapeRenderer()
    private var rotation = 0.0f
    private var textWindow: Window
    private lateinit var label: Label

    private var buttonChange = false

    init {
        textWindow = window("") {
            label = label("nothing")
        }

        app.stg += textWindow

        app.hudStg += app.osc
        Gdx.input.inputProcessor = app.hudStg

        textWindow.setSize(app.hudStg.width / 4, app.hudStg.height / 8)
        textWindow.isVisible = false

        textWindow += label
        label.setPosition(textWindow.width / 2 - label.width / 2, textWindow.height / 2 - label.height / 2)
        label.setAlignment(Align.center)
        textWindow.centerPosition()
    }

    override fun render(delta: Float) {
        rotation += 1f

        with(sr) {
            use(Line) {
                translate(app.width / 2, app.height / 2, 0f)
                rotate(0f, 0f, 1f, rotation)
                color = WHITE
                rect(0f - 75, 0f - 75, 150f, 150f)
            }

            use(Filled) {
                identity()
                color = RED
                circle(0f, 0f, 200f, 25)

                color = PINK
                circle(0f, app.height, 200f, 25)

                color = YELLOW
                circle(app.width, app.height, 200f, 25)

                color = BLUE
                circle(app.width, 0f, 200f, 25)
            }
        }

        app.stg.act(delta)
        app.stg.draw()

        app.hudStg.act(delta)
        app.hudStg.draw()

        with(app.ic) {
            if (!lPressed && !rPressed && !aPressed && !bPressed) {
                buttonChange = false
                textWindow.isVisible = false
            } else {
                if (!buttonChange) {
                    buttonChange = true
                    with(label) {
                        if (lPressed) setText("left")
                        if (rPressed) setText("right")
                        if (aPressed) setText("a")
                        if (bPressed) setText("b")
                    }
                    textWindow.isVisible = true
                }
            }
        }
    }

    override fun dispose() {
        sr.dispose()
        super.dispose()
    }
}
