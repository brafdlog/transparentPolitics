import Ember from 'ember';

export default Ember.Component.extend({
	classNames: ['fb-share-button'],
	didInsertElement: function() {
		if(typeof FB !== "undefined") {
			FB.XFBML.parse();
		}		
	}
});
