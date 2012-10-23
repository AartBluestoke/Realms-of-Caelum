
'''
Created on Oct 22, 2012

@author: Elusivehawk and ObsequiousNewt
'''

import direct.gui.DirectGui as dg
import panda3d.os as p3
from direct.showbase.ShowBase import ShowBase

class main(ShowBase):
 
    def __init__(self):
        ShowBase.__init__(self)
        self.title=dg.OnscreenText(text = "Realms of C\xc3\x86lum",
                                   style = 1,
                                   fg = (1, 1, 1, 1),
                                   pos = (0.8,-0.95),
                                   scale = .07)

app = main()
app.run()
