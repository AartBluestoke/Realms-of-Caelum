'''
Created on Oct 22, 2012

@author: zeb
'''

import direct.directbase.DirectStart
import direct.gui.DirectGui as dg
import panda3d.core as p3

class window:
	def __init__(self):
		self.title=dg.OnscreenText(text = "REALMS of C\xc3\x86",
								   style = 1,
								   fg = (1, 1, 1, 1),
								   pos = (0.8,-0.95),
								   scale = .07)
		base.setBackgroundColor(0, 0, 0)
		base.disableMouse()
		camera.setPos(0,0,45)
		camera.setHpr(0,-90,0)
		
win=window()

run()
