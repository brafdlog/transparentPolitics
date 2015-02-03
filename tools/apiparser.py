#!/usr/bin/python3
from urllib import request
from jinja2 import Environment, PackageLoader
from termcolor import colored
import json
import sys
import textwrap

class KnessetAPIParser():
	def __init__(self):
		env = Environment(loader=PackageLoader('apiparser', '.'))
		env.filters['firstcase'] = KnessetAPIParser.firstCase
		env.filters['modeltype'] = self.translateType
		self.__template__ = env.get_template('class.java.tmpl')

		self.__typeMap__ = {
			'boolean': "Boolean",
			'integer': "Integer",
			'int': "Integer",
			'float': "Float",
			'string': "String",
			'related': "List<String>",
			'list': "List<String>",
			'datetime': "java.util.Date"
		}

	def firstCase(value):
		return value[:1].upper() + value[1:]

	def translateType(self, value):
		if value in self.__typeMap__:
			return self.__typeMap__[value]

		return KnessetAPIParser.firstCase(value)

	def discoverNewType(self, modelName, model):
		mtype = model['type']
		if mtype.lower() == 'list':
			if 'items' in model:
				newType = KnessetAPIParser.firstCase(model['items']['$ref'])
				self.__typeMap__[modelName] = "List<" + newType + ">"
				return True
		return False

	def parseSchema(self, resource):
		try:
			response = request.urlopen('https://oknesset.org/api/v2/doc/schema/' + resource).read().decode('utf-8')
			j = json.loads(response)

			models = []
			for key in j['models']:
				newModel = self.parseModel(key, j['models'][key])
				if (newModel):
					models.append(newModel)

			for m in models:
				filename = KnessetAPIParser.firstCase(m['class_name'] + ".java");
				with open(filename, "w") as f:
					f.write(self.__template__.render(**m))
		except Exception as e:
			print("Error: " + str(e.reason))

	def parseModel(self, modelName, modelMap):
		members = []

		properties = modelMap['properties']
		for key in properties:
			if (not self.discoverNewType(modelName, properties[key])):
				m = {'name': key, 'type': properties[key]['type']}
				members.append(m)

		if (members):
			return { 'class_name' : modelName, 'class_members' : members }
		else:
			return None

if __name__ == '__main__':
	# retrieve argument mapping
	args = {k: v[0] for (k,*v) in [(arg + "=").split('=') for arg in sys.argv[1:]]}
	
	if '--schema' in args:
		parser = KnessetAPIParser();
		parser.parseSchema(args['--schema'])
	else:
		print(textwrap.dedent("""\
			Usage: {0} [--schema=SCHEMA]

			- schema : The SCHEMA you'd like to generate JSON POJOs for
			""").format(sys.argv[0]))