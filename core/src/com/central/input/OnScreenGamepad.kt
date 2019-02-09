package com.central.input

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.central.AppObj

class OnScreenGamepad: Table() {
    private val buttonWidth = 100f
    private val buttonHeight = 100f

    private val aImg = Image(Texture("input/ctrl_a.png"))
    private val bImg = Image(Texture("input/ctrl_b.png"))
    private val rightImg = Image(Texture("input/ctrl_r.png"))
    private val leftImg = Image(Texture("input/ctrl_l.png"))

    init {
        aImg.setSize(buttonWidth, buttonHeight)
        bImg.setSize(buttonWidth, buttonHeight)
        rightImg.setSize(buttonWidth, buttonHeight)
        leftImg.setSize(buttonWidth, buttonHeight)

        this.row().pad(5f, 5f, 5f, 5f)
        this.add(aImg).size(aImg.width, aImg.height)
        this.add(bImg).size(bImg.width, bImg.height)
        this.add(leftImg).size(leftImg.width, leftImg.height).padLeft(AppObj.hudStg.width - aImg.width - bImg.width - leftImg.width - rightImg.width - 40f)
        this.add(rightImg).size(rightImg.width, rightImg.height)
        this.bottom().left()

        aImg.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                AppObj.ic.aPressed = true
                return true
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                AppObj.ic.aPressed = false
            }
        })

        bImg.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                AppObj.ic.bPressed = true
                return true
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                AppObj.ic.bPressed = false
            }
        })

        rightImg.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                AppObj.ic.rPressed = true
                return true
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                AppObj.ic.rPressed = false
            }
        })

        leftImg.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                AppObj.ic.lPressed = true
                return true
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                AppObj.ic.lPressed = false
            }
        })
    }
}