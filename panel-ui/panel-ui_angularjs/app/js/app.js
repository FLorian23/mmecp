'use strict';

/* 
Date: 11.02.2015
by: Lars Willrich (lwi) 
*/

var streetlifedemo = angular.module('streetlife', ["ngSanitize", "openlayers-directive"]);
streetlifedemo.controller('bodyController', [ '$scope', function($scope) {
  angular.extend($scope, {
    Berlin: {
      lat: 52.52133641841789,
      lon: 13.387478027343747,
      zoom: 12
    }
  });

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