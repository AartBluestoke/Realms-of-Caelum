'''
This is *not* the documentation you're looking for.

Look in the Documentation folder for documentation.

Move along!
'''

import direct.showbase.ShowBase as ds
import direct.gui.DirectGui as dg
import panda3d.core as p3

import gui

class window(ds.ShowBase):
	'''Main window class, instantiated upon start of game. This class
	starts the menu.'''
	def __init__(self):
                print "Booting up game..."
		ds.ShowBase.__init__(self)
		base.setBackgroundColor(0, 0, 0)
		base.disableMouse()
		camera.setPos(0,0,45)
		camera.setHpr(0,-90,0)
                
		#Initialize menu
		self.menu_main = gui.main(self)
		self.menu_options = gui.options(self)
		self.menu_newgame = gui.newgame(self)
                self.bgmusic = base.loader.loadSfx("resources/sounds/Climactic cave.mp3")
		self.title = dg.OnscreenText(text = "Realms of Caelum",
                                             style = 3,
                                             fg = (1, 1, 1, 1),
                                             pos = (0,0.75,0),
                                             scale = 0.3)
                
                print "Done!"
		
		self.openmain()
		self.bgmusic.play()

	def hide_menus(self):
		'''Hides any active menus. Called before switching menus.'''
		self.menu_main.hide()
		self.menu_options.hide()
		self.menu_newgame.hide()

	def openmain(self):
		'''Opens the main menu.'''
		self.hide_menus()
		self.menu_main.show()

	def openoptions(self):
		'''Opens the options menu.'''
		self.hide_menus()
		self.menu_options.show()

	def opennewgame(self):
		'''Opens the new-game menu.'''
		self.hide_menus()
		self.menu_newgame.show()
		#TODO Finish Eventually(TM).

	def setmusicvolume(self):
                self.bgmusic.setVolume(self.menu_options.musicVolume['value'])

        def createworld(self):
                print "This button will be activated Soon(TM)."

win=window()
win.run()
