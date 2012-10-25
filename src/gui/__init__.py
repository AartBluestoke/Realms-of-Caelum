'''This file contains all of the main-menu GUI classes. For in-game and special GUIs, please see respective files in this package.'''

import direct.gui.DirectGui as dg

class base:

	def __init__(self,win):
		self.win = win
		self.frame = dg.DirectFrame(frameSize = (-1.2,-0.6,0.9,-0.2))

	def hide(self):
		self.frame.hide()

	def show(self):
		self.frame.show()

class main(base):

	def __init__(self,win):
		base.__init__(self,win)
		self.new = dg.DirectButton(parent = self.frame,
					   text = ("New Game"), #TODO! Let's add support for languages early, so we don't have to deal with that later when it'll be harder.
					   pos = (-1,0,0.8),
					   scale = .05,
					   command = win.new_game)
		self.options = dg.DirectButton(parent = self.frame,
					       text = ("Options"),
					       pos = (-1,0,0.6),
					       scale = .05,
					       command = win.openoptions
					       )

class options(base):

	def __init__(self,win):
		base.__init__(self,win)
