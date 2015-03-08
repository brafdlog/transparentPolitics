import Ember from 'ember';

export default Ember.ArrayController.extend({
	sortAscending: false,
	sortProperties: ['grade', 'name'],
	activeParties: Ember.A(),
	searchTerm: '',
	
	filterdMembers: function() {
		return this.get('arrangedContent').filter(function(member) {
			var activeParties = this.get('activeParties'),
				isName = member.get('name').indexOf(this.searchTerm) > -1,
				isInPartFilter = activeParties.length === 0 || activeParties.indexOf(member.get('party').get('name')) > -1;

			return isName && isInPartFilter;
		}.bind(this));
	}.property('searchTerm', 'activeParties.@each', 'arrangedContent.[]'),
	parties: function() {
		return this.store.find('party');
	}.property('this'),
	
	actions: {
		filterParties: function(data) {
			var activeParties = this.get('activeParties'),
				partyName = data.get('name'),
				hasParty = activeParties.contains(partyName);
			
			if(hasParty) {
				activeParties.removeObject(partyName);
			} else {
				activeParties.pushObject(partyName);
			}

			console.log(activeParties);
		}
	}
});
