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
		self.new = dg.DirectButton(parent = self.frame,
					   text = ("New Game"), #TODO! Let's add support for languages early, so we don't have to deal with that later when it'll be harder.
					   pos = (0,0,0.8),
					   scale = buttonsize,
					   command = win.new_game)
		self.options = dg.DirectButton(parent = self.frame,
					       text = ("Options"),
					       pos = (0,0,0.6),
					       scale = buttonsize,
					       command = win.openoptions)

class options(base):

	def __init__(self,win):
		base.__init__(self,win)
		self.back = dg.DirectButton(parent = self.frame,
                                            text = ("Back"),
                                            pos = (0,0,-0.8),
                                            scale = buttonsize,
                                            command = win.openmain)
