# -*- coding: utf-8 -*-
import random

def vowel():
	r = random.random()
	if r < .9: return ['a','e','i','o','u','y'][int(random.random()*6)]
	else: return ['\xc3\xa6','\xc5\x93','au'][int(random.random()*3)]

def ccl():
	r = random.random()
	if r < .35: return '^'+cons()
	elif r < .45: return other()+stop()
	elif r < .55: return ['pt','pk','bd','bg','tk','dg','kt','gd'][int(random.random()*8)]
	elif r < .65: return other()+fric()
	elif r < .7: return stop()+other()
	elif r < .75: return fric()+other()
	elif r < .85: return ['ps','bz','p\xc5\xa1','b\xc5\xbe','ts','dz','t\xc5\xa1','d\xc5\xbe','ks','gz','k\xc5\xa1','g\xc5\xbe'][int(random.random()*12)]
	elif r < .90: return ['sp','zb','\xc5\xa1p','\xc5\xbeb','st','zd','\xc5\xa1t','\xc5\xbed','sk','zg','\xc5\xa1k','\xc5\xbeg'][int(random.random()*12)]
	else: return '"'

def cons():
	r = random.random()
	if r < 30: return stop()
	elif r < .70: return fric()
	elif r < .9: return other()
	else: return ''

def stop(): return ['p','b','k','g','t','d'][int(random.random()*6)]

def fric(): return ['f','v','\xc5\xa5','\xc4\x8f','s','z','\xc5\xa1','\xc5\xbe'][int(random.random()*8)]

def other(): return ['h','l','n'][int(random.random()*3)]

def replace(w):
	while ('^' in w) or ('"' in w):
		w = w.replace('a^','\xc3\xa2')
		w = w.replace('a"','\xc3\xa4')
		w = w.replace('e^','\xc3\xaa')
		w = w.replace('e"','\xc3\xab')
		w = w.replace('i^','\xc3\xae')
		w = w.replace('i"','\xc3\xaf')
		w = w.replace('o^','\xc3\xb4')
		w = w.replace('o"','\xc3\xb6')
		w = w.replace('u^','\xc3\xbb')
		w = w.replace('u"','\xc3\xbc')
		w = w.replace('y"','\xc3\xbf')
		w = w.replace('y^','\xc5\xb7')
		w = w.replace('\xc3\xa6^','\xc7\xbd')
		w = w.replace('\xc3\xa6"','\xc3\xa6')
		w = w.replace('\xc5\x93^','\xc5\x93')
		w = w.replace('\xc5\x93"','\xc5\x93')
	return w

def word(syl):
	w = ''
	w += cons()
	for i in range(syl):
		w += vowel()
		if i != syl-1: w += ccl()
	w += cons()
	w = replace(w)
	return w
