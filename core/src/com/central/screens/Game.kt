package com.central.screens

import com.badlogic.gdx.Gdx
import com.central.App
import com.central.AppObj
import ktx.app.KtxScreen
import com.badlogic.gdx.graphics.Color.*
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.*
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import ktx.actors.centerPosition
import ktx.actors.plusAssign
import ktx.graphics.*
import ktx.scene2d.*


class Game(val application: App) : KtxScreen {
    private val sr = AppObj.sr
    private var rotation = 0.0f
    private val width = Gdx.graphics.width.toFloat()
    private val height = Gdx.graphics.height.toFloat()
    private val textWindow = window("")
    private var label = Label("nothing", Scene2DSkin.defaultSkin)
    private var buttonChange = false

    init {
        AppObj.stg += textWindow

        AppObj.hudStg += AppObj.osc
        Gdx.input.inputProcessor = AppObj.hudStg

        textWindow.setSize(AppObj.hudStg.width / 4, AppObj.hudStg.height / 8)
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
                translate(width / 2, height / 2, 0f)
                rotate(0f, 0f, 1f, rotation)
                color = WHITE
                rect(0f - 75, 0f - 75, 150f, 150f)
            }

            use(Filled) {
                identity()
                color = RED
                circle(0f, 0f, 200f, 25)

                color = PINK
                circle(0f, height, 200f, 25)

                color = YELLOW
                circle(width, height, 200f, 25)

                color = BLUE
                circle(width, 0f, 200f, 25)
            }
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
}
