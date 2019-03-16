# Libktx Template With On Screen Gamepad and Physical Gamepad Support

This is the same as a different template project I have, but it has an input 'module' (not really a module, but it's a 
package called input that can easily be dropped into a project). It relies on some pngs (the images for the buttons), which 
are in the android/assets/input folder.

![gampad.gif](.github/gamepad.gif?raw=true)

Being a little more complex than the ktx_template project, there's a few structural things that are implemented here to 
support a background/ foreground.

- the AppObj class contains instances of most of what supports the game and those instances are used like static/singleton 
members 

- there is stage and a 'hud stage' which each have their own spritebatch, camera, and view. The stage is only there for the 
notification box that appears when a button is pressed - the 'hud stage' is the top layer and in the Game screen's init block 
the Gdx.input.InputProcessor is bound to the 'hud stage'

- note that if you connect a gamepad it'll 'just work'. Also note that in AppObj there's an unused instance of GamepadCtl. 
That is the class that makes it work and there being an instance of it alone makes it listen for gamepad input - see 
[https://github.com/libgdx/libgdx/wiki/Controllers](https://github.com/libgdx/libgdx/wiki/Controllers)

- instead of binding actions to actual input events (ie Keys.SPACE -> player.jump), the keys and events are all mapped to 
button states in the InputCtl class. You can poll the states of the buttons (whether they were keyboard, gamepad, or onscreen 
presses) by using AppObj.ic.aButton

I've re-used this input package so many times that I wanted to put it in an example so it's accessible to others - hope it 
helps you out!
