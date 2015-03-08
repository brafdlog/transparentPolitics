import Ember from 'ember';

export function numberIncrementor(input) {
  return input + 1;
}

export default Ember.Handlebars.makeBoundHelper(numberIncrementor);
