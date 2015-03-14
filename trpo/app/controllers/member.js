import Ember from 'ember';

export default Ember.ObjectController.extend({
	shareHREF: function() {
		return '//trapol.us/members/' + this.get('id')
	}.property('id'),

	chartOptions: {
		chart: {
			type: 'bar'
		},
		title: {
			text: 'ביצועים'
		},
		xAxis: {
			categories: ['ציון כללי', 'ממוצע נוכחות שבועי במליאה', 'ממוצע נוכחות חודשי בוועדות', 'עצות חוק שהתקבלו', 'הצעות חוק שהוצעו']
		},
		yAxis: {
			title: {
				text: ''
			}
		}
	},

	chartData: [
		{
			name: 'דב חנין',
			data: [9, 0, 4, 7, 10]
		}, {
			name: 'ממוצע',
			data: [4, 7, 3, 4, 8]
		}
	]
});
