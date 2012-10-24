'''
Created on Oct 22, 2012

@author: ObsequiousNewt
'''

import direct.showbase as ds
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
        
win=window()
win.run()
