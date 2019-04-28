package com.central

import com.badlogic.gdx.Screen
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.central.input.GamepadCtl
import com.central.input.InputCtl
import com.central.input.OnScreenGamepad
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import ktx.scene2d.Scene2DSkin
import ktx.app.KtxGame
import com.central.screens.Game


class App : KtxGame<Screen>() {

    var width = 0f
    var height = 0f

    lateinit var sb: SpriteBatch
    lateinit var cam: OrthographicCamera
    lateinit var view: StretchViewport
    lateinit var stg: Stage

    lateinit var hudSb: SpriteBatch
    lateinit var hudCam: OrthographicCamera
    lateinit var hudView: StretchViewport
    lateinit var hudStg: Stage

    lateinit var skin: Skin

    lateinit var ic: InputCtl
    lateinit var osc: OnScreenGamepad

    /**
     * this class makes it so the game listens for physical gamepad input
     * it works like magic, just instantiating results in gamepad events getting processed
     * see ControllerListener
     * https://github.com/libgdx/libgdx/wiki/Controllers
     */
    lateinit var gpc: GamepadCtl

    override fun create() {
        this.width = Gdx.graphics.width.toFloat()
        this.height = Gdx.graphics.height.toFloat()

        this.sb = SpriteBatch()
        this.cam = OrthographicCamera(this.width, this.height)
        this.view = StretchViewport(480f, 360f, this.cam)
        this.stg = Stage(this.view, this.sb)

        this.hudSb = SpriteBatch()
        this.hudCam = OrthographicCamera(this.width, this.height)
        this.hudView = StretchViewport(480f, 360f, this.hudCam)
        this.hudStg = Stage(this.hudView , this.hudSb)

        this.ic = InputCtl(this)
        this.osc = OnScreenGamepad(this)
        this.gpc = GamepadCtl(this)

        skin = Skin(Gdx.files.internal("custom/skin/skinui.json"))
        Scene2DSkin.defaultSkin = this.skin

        val game = Game(this)

        addScreen(game)
        setScreen<Game>()
    }

    override fun dispose() {
        this.stg.dispose()
        this.hudSb.dispose()
        this.hudStg.dispose()
        this.skin.dispose()
        this.osc.dispose()
    }
}
