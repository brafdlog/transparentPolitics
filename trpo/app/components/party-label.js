import Ember from 'ember';

export default Ember.Component.extend({
	tagName: 'span',
	classNames: ['label', 'party-label', 'label-default'],
	classNameBindings: ['label-primary'],
	click: function() {
		this.toggleProperty('label-primary');
		this.sendAction('action', this.get('data'));
	}
});
