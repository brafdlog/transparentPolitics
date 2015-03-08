import DS from 'ember-data';

export default DS.Model.extend({
  name: DS.attr('string'),  
  imageUrl: DS.attr('string'),
  grade: DS.attr('number'),
  averageWeeklyPresenceHours: DS.attr('number'),
  averageMonthlyCommitteePresence: DS.attr('number'),
  tromitBills: DS.attr('number'),
  proposedBills: DS.attr('number'),
  approvedBills: DS.attr('number'),
  party: DS.belongsTo('party', { async: true })
});
