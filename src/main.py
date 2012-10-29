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
		print 'Booting up game...',
		ds.ShowBase.__init__(self)
		base.setBackgroundColor(0, 0, 0)
		base.disableMouse()
		camera.setPos(0,0,45)
		camera.setHpr(0,-90,0)
		#Initialize menu
		self.menu_main = gui.main(self)
		self.menu_loadgame = gui.loadgame(self)
		self.menu_playonline = gui.playonline(self)
		self.menu_options = gui.options(self)
		self.menu_newworld = gui.makenewworld(self)
		self.bgmusic = base.loader.loadSfx('resources/sounds/Climactic cave.mp3')
		self.title = dg.OnscreenText(text = u'Realms of C\xe6lum',
					     style = 3,
					     fg = (1, 1, 1, 1),
					     pos = (0,0.75,0),
					     scale = 0.3)
                self.copyright = dg.OnscreenText(text = u'\xa9 Copyright 2012 Elusivehawk, LLC. and Pentachoron Labs, All Rights Reserved',
						 style = 3,
						 fg = (1, 1, 1, 1),
						 pos = (-0.66,-0.95,0),
						 scale = 0.05)
		print 'Done!'
		self.openmain()
		self.bgmusic.setLoop(True)
		self.bgmusic.play()

	def hide_menus(self):
		'''Hides any active menus. Called before switching menus.'''
		self.menu_main.hide()
		self.menu_loadgame.hide()
		self.menu_playonline.hide()
		self.menu_options.hide()
		self.menu_newworld.hide()

	def openmain(self):
		'''Opens the main menu.'''
		self.hide_menus()
		self.menu_main.show()

	def openoptions(self):
		'''Opens the options menu.'''
		self.hide_menus()
		self.menu_options.show()

	def openloadgame(self):
		'''Opens the load game menu.'''
		self.hide_menus()
		self.menu_loadgame.show()

	def openplayonline(self):
		'''Opens the play-online menu.'''
		self.hide_menus()
		self.menu_playonline.show()

	def setmusicvolume(self):
		'''Sets the music volume.'''
		self.bgmusic.setVolume(self.menu_options.musicVolume['value'])

	def opennewworld(self):
		'''Opens the new world menu.'''
		self.hide_menus()
		self.menu_newworld.show()

	def makenewworld(self,name):
		'''Creates a new world.'''
		#TODO Get ta work!
		pass

win=window()
win.run()
