# -*- coding: utf-8 -*-
import random

def vowel():
	r = random.random()
	if r < .9: return ['a','e','i','o','u','y'][int(random.random()*6)]
	else: return ['\xc3\xa6','\xc5\x93','a\xc3\xb9'][int(random.random()*3)]

def cons():
	r = random.random()
	if r < .2: return stop()
	elif r < .45: return fric()
	elif r < .5: return ['h','l','n'][int(random.random()*3)]
	elif r < .6: return other()+stop()
	elif r < .7: return ['pt','pk','bd','bg','tk','dg','kt','gd'][int(random.random()*8)]
	elif 
##	cons = ['p','b','t','d','k','g','f','v','\xc5\xa5','\xc4\x8f','s','z','\xc5\xa1','\xc5\xbe','h','n','l']
##		'pt',
##		'bb','bd','bf','bs','bh','bn','bl',
##		'kt','ks',
##		'gn',
##		'sk','ss',
##		'mp','mb',
##		'nt','nd','ns',
##		'\xc5\x8bk','\xc5\x8bg',
##		'll']
	return cons[int(random.random()*len(cons))]

def word(syl):
	w = ''
	if random.random() < .6:
		w += cons()
		if random.random() < .3: w += cons()
	for i in range(syl):
		w += vowel()
		if random.random() < .6:
			w += cons()
			if random.random() < .3: w += cons()
	return w
