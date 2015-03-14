import Ember from 'ember';
import config from './config/environment';

var Router = Ember.Router.extend({
  location: config.locationType,
  analyticsAccount: config.analyticsAccount
});

Ember.Router.reopen({
  notifyGoogleAnalytics: function() {
    ga('create', this.get('analyticsAccount'), 'auto');
    return ga('send', 'pageview');
  }.on('didTransition')
});

Router.map(function() {
  this.route("members");
  this.route("parties");
  this.resource("member", { path: '/members/:member_id' });
  this.route("about");
});

export default Router;
