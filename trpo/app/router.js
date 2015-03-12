import Ember from 'ember';
import config from './config/environment';

var Router = Ember.Router.extend({
  location: config.locationType
});

Router.map(function() {
  this.route("members");
  this.route("parties");
  this.resource("member", { path: '/members/:member_id' });
  this.route("about");
});

export default Router;
