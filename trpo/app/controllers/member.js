import Ember from 'ember';

export default Ember.ObjectController.extend({
	/*chartMode: 'StockChart', // Available options: a falsy value, 'StockChart', 'Map'.
                           // If `mode` is not provided or is a falsy value, the chart is initialized in Charts mode.
                           // If `mode` is a string, it is passed to Highcharts as the first argument.
                           // When Highcharts introduces a new mode, you will be able to use it here right away.*/

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
			name: 'מומצע',
			data: [4, 7, 3, 4, 8]
		}
	]
});
