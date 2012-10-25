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
	def __init__(self):
		ds.ShowBase.__init__(self)
		base.setBackgroundColor(0, 0, 0)
		base.disableMouse()
		camera.setPos(0,0,45)
		camera.setHpr(0,-90,0)

		#Initialize menu
		self.menu_main = gui.main(self)
		self.menu_options = gui.options(self)

		self.openmain()
	
	def hide_menus(self):
		self.menu_main.hide()
		self.menu_options.hide()
		
	def openmain(self):
		self.hide_menus()
		self.menu_main.show()

	def openoptions(self):
		self.hide_menus()
		self.menu_options.show()

	def new_game(self):
		pass #We will get to this... *much* later.
        
win=window()
win.run()
