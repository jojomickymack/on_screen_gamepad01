package com.central

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.central.input.GamepadCtl
import com.central.input.InputCtl
import com.central.input.OnScreenGamepad
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import ktx.scene2d.Scene2DSkin

object AppObj {

    var width = Gdx.graphics.height.toFloat()
    var height = Gdx.graphics.width.toFloat()

    val sb = SpriteBatch()
    val cam = OrthographicCamera(width, height)
    val view = StretchViewport(480f, 360f, cam)
    val stg = Stage(view, sb)

    val hudSb = SpriteBatch()
    val hudCam = OrthographicCamera(width, height)
    val hudView = StretchViewport(480f, 360f, hudCam)
    val hudStg = Stage(hudView , hudSb)

    val sr = ShapeRenderer()

    val skin = Skin(Gdx.files.internal("custom/skin/skinui.json"))

    val ic = InputCtl()
    val gpc = GamepadCtl()
    val osc = OnScreenGamepad()

    lateinit var app: App

    init {
        Scene2DSkin.defaultSkin = this.skin
    }

    fun dispose() {
        this.stg.dispose()
        this.hudSb.dispose()
        this.hudStg.dispose()
        this.sr.dispose()
        this.skin.dispose()
        this.osc.dispose()
    }
}