#!/usr/bin/env python
# coding: utf-8
 
import urllib.request
import sys

parametros = sys.argv[1:]

def parser(profundidade,endereco):
	if profundidade != 0 :
		retorno = urllib.request.urlopen(endereco) 
		linha = retorno.readline().decode("utf-8") 
		while linha != '' :

				if '<a' in linha :
						index = linha.find('<a')
						linha = linha[index:]
						if '"' in linha :
								index = linha.find('"')
								linha = linha[index + 1:]
								if '"' in linha :
										index = linha.find('"')
										link = linha[:index]
										linha = linha[index:]
										print(link)
										if profundidade > 0:
											parser(profundidade-1,link)
                               
								else :
										linha = retorno.readline().decode("utf-8")
						else :
								linha = retorno.readline().decode("utf-8")     
				else :                 
						linha = retorno.readline().decode("utf-8")

profundidade = int(parametros[0])
endereco = parametros[1]
parser(profundidade,endereco)