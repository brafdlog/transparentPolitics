import Ember from 'ember';

export default Ember.Component.extend({
	classNames: ['member', 'col-xs-6', 'col-md-3', 'col-lg-2'],
	classNameBindings: ['flipped'],
	
	inlineStyle: function(){
		return 'background-image: url(' + this.get('data.imageUrl') + ');';
	}.property('data.imageUrl'),
	memberLink: function() {
		return 'https://oknesset.org/member/' + this.get('data.id');
	}.property('data.id'),
	partyLink: function() {
		return 'https://oknesset.org/party/' + this.get('data.party.id');
	}.property('data.id'),
	
	actions: {
		toggleMemberState: function() {
			this.toggleProperty('flipped');
		}
	}
});
