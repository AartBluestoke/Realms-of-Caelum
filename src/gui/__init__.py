# -*- coding: utf-8 -*-
'''This file contains all of the main-menu GUI classes. For in-game and special GUIs, please see respective files in this package.'''

import direct.gui.DirectGui as dg
import lang as lang_
from lang import lang

buttonsize = 0.1

class base:
	'''Basic GUI superclass. Inherit this!'''
	def __init__(self,win):
		self.win = win
		self.frame = dg.DirectFrame(frameSize = (-1,1,-1,1),
					color=(0,0,0,0))

	def hide(self):
		'''Called to hide the GUI.'''
		self.frame.hide()

	def show(self):
		'''Called to show the GUI.'''
		self.frame.show()

class main(base):

	def __init__(self,win):
		base.__init__(self,win)
		self.loadgame = dg.DirectButton(parent = self.frame,
						text = (lang('gui.button.story')),
						pos = (0,0,-0.2),
						scale = buttonsize,
						command = win.openstorymode)
		self.playonline = dg.DirectButton(parent = self.frame,
						text = (lang('gui.button.free')),
						pos = (0,0,-0.4),
						scale = buttonsize,
						command = win.openfreeplay)
		self.options = dg.DirectButton(parent = self.frame,
						text = (lang('gui.button.options')),
						pos = (0,0,-0.6),
						scale = buttonsize,
						command = win.openoptions)

class options(base):
	
	def __init__(self,win):
		base.__init__(self,win)
		self.musicVolume = dg.DirectSlider(parent = self.frame,
						pos = (0,0,-0.4),
						scale = 0.4,
						value = 0.6,
						command = win.setmusicvolume)
		self.back = dg.DirectButton(parent = self.frame,
						text = (lang('gui.button.back')),
						pos = (0,0,-0.7),
						scale = buttonsize,
						command = win.openmain)
		self.lang = dg.DirectOptionMenu(parent = self.frame,
						text = (lang('gui.options.lang')),
						pos = (0,0,-0.1),
						scale = buttonsize,
						items = ['Default']+lang_.supported.keys(),
						initialitem = 0,
						command = win.selectlang)

class storymode(base):

	def __init__(self,win):
		base.__init__(self,win)
		self.createNew = dg.DirectButton(parent = self.frame,
						text = (lang('gui.button.new')),
						pos = (-0.2,0,-0.7),
						scale = buttonsize,
						command = win.opennewworld)
		self.back = dg.DirectButton(parent = self.frame,
						text = (lang('gui.button.back')),
						pos = (0.15,0,-0.7),
						scale = buttonsize,
						command = win.openmain)

class freeplay(base):

	def __init__(self,win):
		base.__init__(self,win)
		#TODO Add more buttons.
		self.back = dg.DirectButton(parent = self.frame,
						text = (lang('gui.button.back')),
						pos = (0,0,-0.7),
						scale = buttonsize,
						command = win.openmain)

class makenewworld(base):

	def __init__(self,win):
		base.__init__(self,win)
		self.cancel = dg.DirectButton(parent = self.frame,
						text = (lang('gui.button.cancel')),
						pos = (0,0,-0.7),
						scale = buttonsize,
						command = win.openmain)
		self.worldname = dg.DirectEntry(parent = self.frame,
						text = (lang('gui.game.name')),
						pos = (0,0,0),
						width = 2,
						numLines = 1,
						command = win.makenewworld)
