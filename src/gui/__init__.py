'''This file contains all of the main-menu GUI classes. For in-game and special GUIs, please see respective files in this package.'''

import direct.gui.DirectGui as dg

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
						text = ('Story'), #TODO! Let's add support for languages early, so we don't have to deal with that later when it'll be harder.
						pos = (0,0,-0.2),
						scale = buttonsize,
						command = win.openstorymode)
		self.playonline = dg.DirectButton(parent = self.frame,
						text = ('Free Play'),
						pos = (0,0,-0.4),
						scale = buttonsize,
						command = win.openfreeplay)
		self.options = dg.DirectButton(parent = self.frame,
						text = ('Options'),
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
						text = ('Back'),
						pos = (0,0,-0.7),
						scale = buttonsize,
						command = win.openmain)

class storymode(base):

	def __init__(self,win):
		base.__init__(self,win)
		self.createNew = dg.DirectButton(parent = self.frame,
						text = ('New'),
						pos = (-0.2,0,-0.7),
						scale = buttonsize,
						command = win.opennewworld)
		self.back = dg.DirectButton(parent = self.frame,
						text = ('Back'),
						pos = (0.15,0,-0.7),
						scale = buttonsize,
						command = win.openmain)

class freeplay(base):

	def __init__(self,win):
		base.__init__(self,win)
		#TODO Add more buttons.
		self.back = dg.DirectButton(parent = self.frame,
						text = ('Back'),
						pos = (0,0,-0.7),
						scale = buttonsize,
						command = win.openmain)

class makenewworld(base):

	def __init__(self,win):
		base.__init__(self,win)
		self.cancel = dg.DirectButton(parent = self.frame,
						text = ('Cancel'),
						pos = (0,0,-0.7),
						scale = buttonsize,
						command = win.openmain)
		self.worldname = dg.DirectEntry(parent = self.frame,
						text = ("World Name"),
						pos = (0,0,0),
						width = 2,
						numLines = 1,
						command = win.makenewworld)
