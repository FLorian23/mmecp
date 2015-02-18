'use strict';

/* 
Date: 11.02.2015
by: Lars Willrich (lwi) 
*/
streetlife.controller('mapController', [ '$scope', function($scope) {
  angular.extend($scope, {
    Berlin: {
      lat: 52.52133641841789,
      lon: 13.387478027343747,
      zoom: 12
    },
    controls: [
    ]
  });

  $(window).resize(function(){
    $scope.$apply(function () {
      $scope.mapheight = ($( window  ).height() - $("#tabs").height() - $("#header").height()- 31) + "px";
    });

  });
  $( document ).ready(function() {
    $scope.mapheight = ($( window  ).height() - $("#tabs").height() - $("#header").height()- 31) + "px";
  });

}]).directive('leftMenu', function() {
      return {
        restrict: 'E',
        templateUrl: 'html/LeftMenu.html'
      }
    }).directive('rightMenu', function() {
      return {
        restrict: 'E',
        templateUrl: 'html/RightMenu.html'
      }
});