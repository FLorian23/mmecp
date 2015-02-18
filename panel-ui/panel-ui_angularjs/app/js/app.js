'use strict';

/* 
Date: 11.02.2015
by: Lars Willrich (lwi) 
*/

var streetlife = angular.module('streetlife', ["ngSanitize", "openlayers-directive"]);
streetlife.controller('bodyController', [ '$scope', function($scope) {

}]).directive('header', function() {
  return {
    restrict: 'E',
    templateUrl: 'html/Header.html'
  }
}).directive('content', function() {
  return {
    restrict: 'E',
    templateUrl: 'html/Content.html'
  }
});