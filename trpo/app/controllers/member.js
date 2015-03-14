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
		var grade = Math.round(this.get('grade') * 100) / 100,
			averageWeeklyPresenceHours = Math.round(this.get('averageWeeklyPresenceHours') * 100) / 100,
			averageMonthlyCommitteePresence = Math.round(this.get('averageMonthlyCommitteePresence') * 100) / 100,
			proposedBills = Math.round(this.get('proposedBills') * 100) / 100,
			approvedBills = Math.round(this.get('approvedBills') * 100) / 100,
			allMembersAverageGrade = Math.round(this.get('allMembersAverageGrade') * 100) / 100,
			allMembersAverageWeeklyPresenceHours = Math.round(this.get('allMembersAverageWeeklyPresenceHours') * 100) / 100,
			allMembersAverageMonthlyCommitteePresence = Math.round(this.get('allMembersAverageMonthlyCommitteePresence') * 100) / 100,
			allMembersAverageProposedBills = Math.round(this.get('allMembersAverageProposedBills') * 100) / 100,
			allMembersAverageApprovedBills = Math.round(this.get('allMembersAverageApprovedBills') * 100) / 100;

		return [
			{
				name: this.get('name'),
				data: [grade, averageWeeklyPresenceHours, averageMonthlyCommitteePresence, proposedBills, approvedBills]
			}, {
				name: 'ממוצע',
				data: [allMembersAverageGrade, allMembersAverageWeeklyPresenceHours, allMembersAverageMonthlyCommitteePresence, allMembersAverageProposedBills, allMembersAverageApprovedBills]
			}
		];	
	}.property('grade'),

	largeImage: function() {
		return this.get('imageUrl').replace('-s.', '.');
	}.property('imageUrl')
});
