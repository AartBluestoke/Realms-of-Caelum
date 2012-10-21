'''
Created on Oct 21, 2012

@author: zeb
'''
import PyQt4.Qt as q

def start():
    app=q.QApplication([])
    form=q.QWidget()
    form.setWindowTitle("Codename FSG")
    form.setWindowIcon(q.QIcon('icon.gif'))
    form.resize(300, 400)
    form.show()
    app.exec_()