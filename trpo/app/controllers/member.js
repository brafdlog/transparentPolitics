import Ember from 'ember';

export default Ember.ObjectController.extend({
	shareHREF: function() {
		return '//trapol.us/members/' + this.get('id');
	}.property('id'),

	chartOptions: {
		chart: {
			type: 'bar'
		},
		title: {
			text: 'ביצועים'
		},
		xAxis: {
			categories: ['ציון כללי', 'ממוצע נוכחות שבועי במליאה', 'ממוצע נוכחות חודשי בוועדות', 'הצעות חוק שהוצעו', 'הצעות חוק שהתקבלו']
		},
		yAxis: {
			title: {
				text: ''
			}
		}
	},

	chartData: function(){
		return [
			{
				name: this.get('name'),
				data: [this.get('grade'), this.get('averageWeeklyPresenceHours'), this.get('averageMonthlyCommitteePresence'), this.get('proposedBills'), this.get('approvedBills')]
			}, {
				name: 'ממוצע',
				data: [this.get('grade'), this.get('allMembersAverageWeeklyPresenceHours'), this.get('allMembersAverageMonthlyCommitteePresence'), this.get('allMembersAverageProposedBills'), this.get('allMembersAverageApprovedBills')]
			}
		];	
	}.property('grade'),

	largeImage: function() {
		return this.get('imageUrl').replace('-s.', '.');
	}.property('imageUrl')
});
