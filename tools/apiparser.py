#!/usr/bin/python3
from urllib import request
from jinja2 import Environment, PackageLoader
from termcolor import colored
import json

class KnessetAPIParser():
	def __init__(self):
		env = Environment(loader=PackageLoader('apiparser', '.'))
		env.filters['firstcase'] = KnessetAPIParser.firstCase
		self.__template__ = env.get_template('class.java.tmpl')

		self.__typeMap__ = {
			'boolean': "Boolean",
			'integer': "Integer",
			'int': "Integer",
			'float': "Float",
			'string': "String",
			'related': "List<String>",
			'datetime': "java.util.Date"
		}

	
	def firstCase(value):
		return value[0:1].upper() + value[1:]

	def parseSchema(self, resource):
		response = request.urlopen('https://oknesset.org/api/v2/doc/schema/' + resource).read().decode('utf-8')
		j = json.loads(response)

		for key in j['models']:
			self.parseModel(key, j['models'][key])

	def parseModel(self, modelName, modelMap):
		members = []

		properties = modelMap['properties']
		for key in properties:
			m = {'name': key, 'type': self.parseModelType(properties[key])}
			members.append(m)

		print(self.__template__.render(class_name=modelName, class_members=members))

	def parseModelType(self, model):
		mtype = model['type']
		if mtype.lower() == 'list':
			if 'items' in model:
				return "List<" + KnessetAPIParser.firstCase(model['items']['$ref']) + ">"
			else:
				return "List<String>"
		elif mtype in self.__typeMap__:
			return self.__typeMap__[mtype]

		return KnessetAPIParser.firstCase(mtype)

if __name__ == '__main__':
	parser = KnessetAPIParser();
	parser.parseSchema('member')