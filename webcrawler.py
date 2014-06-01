#!/usr/bin/env python
# coding: utf-8
 
import urllib.request
import sys

parametros = sys.argv[1:]

def parser(profundidade,endereco):
	if profundidade != 0 :
		retorno = urllib.request.urlopen(endereco) 
		linha = retorno.readline().decode("utf-8") 
		
profundidade = int(parametros[0])
endereco = parametros[1]
parser(profundidade,endereco)