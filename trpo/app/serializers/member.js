import DS from 'ember-data';

export default DS.RESTSerializer.extend({
	attrs: {
		party: {embedded: 'always'}
	}
});
