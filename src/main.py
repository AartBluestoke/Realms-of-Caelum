# -*- coding: utf-8 -*-
'''
This is *not* the documentation you're looking for.

Look in the Documentation folder for documentation.

Move along!
'''

import direct.showbase.ShowBase as ds
import direct.gui.DirectGui as dg
import panda3d.core as p3

import gui
import lang as lang_
from lang import lang

class window(ds.ShowBase):
	'''Main window class, instantiated upon start of game. This class
	starts the menu.'''
	def __init__(self):
		print lang('stdout.boot')
		ds.ShowBase.__init__(self)
		base.setBackgroundColor(0, 0, 0)
		base.disableMouse()
		camera.setPos(0,0,45)
		camera.setHpr(0,-90,0)
		#Initialize menu
		self.createmenus()
		#Starts up title music.
		self.bgmusic = base.loader.loadSfx('resources/sounds/Climactic cave.mp3')
		print lang('stdout.bootdone')
		self.openmain()
		self.bgmusic.setLoop(True)
		self.bgmusic.play()

	def hide_menus(self):
		'''Hides any active menus. Called before switching menus.'''
		self.menu_main.hide()
		self.menu_story.hide()
		self.menu_freeplay.hide()
		self.menu_options.hide()
		self.menu_newworld.hide()

	def recreatemenus(self):
		'''Destroys and recreates all menus.'''
		self.menu_main.frame.destroy()
		self.menu_story.frame.destroy()
		self.menu_freeplay.frame.destroy()
		self.menu_options.frame.destroy()
		self.menu_newworld.frame.destroy()
		self.createmenus()

	def createmenus(self):
		'''Creates menus.'''
		self.menu_main = gui.main(self)
		self.menu_story = gui.storymode(self)
		self.menu_freeplay = gui.freeplay(self)
		self.menu_options = gui.options(self)
		self.menu_newworld = gui.makenewworld(self)
		self.hide_menus()

	def openmain(self):
		'''Opens the main menu.'''
		self.hide_menus()
		self.menu_main.show()

	def openoptions(self):
		'''Opens the options menu.'''
		self.hide_menus()
		self.menu_options.show()

	def openstorymode(self):
		'''Opens the load game menu.'''
		self.hide_menus()
		self.menu_story.show()

	def openfreeplay(self):
		'''Opens the play-online menu.'''
		self.hide_menus()
		self.menu_freeplay.show()

	def setmusicvolume(self):
		'''Sets the music volume.'''
		self.bgmusic.setVolume(self.menu_options.musicVolume['value'])

	def opennewworld(self):
		'''Opens the new world menu.'''
		self.hide_menus()
		self.menu_newworld.show()

	def makenewworld(self):
		'''Creates a new world.'''
		#TODO Get ta work!
		pass

	def selectlang(self,obj):
		'''Selects a language.'''
		if obj == 'Default': return
		lang_.curr = lang_.supported[obj]
		self.recreatemenus()
		self.openoptions()

win=window()
win.run()
