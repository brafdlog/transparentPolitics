import Ember from 'ember';

export default Ember.Component.extend({
	classNames: ['member', 'col-xs-12', 'col-md-4', 'col-lg-3'],
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
