var app=angular.module('myapp',[]);
app.controller('UserController',['$scope','$http',function($scope,$http){
	this.vald=false;
	this.tab=false;
	this.show;
	this.j;
	this.url="http://localhost:8080/solr/collection1/select?q=";
	this.createUser=function(){
		this.tab=true;
		//alert("dsd");
	};
	this.urlCreate=function(query){
		this.url="http://localhost:8080/solr/collection1/select?q=";
		this.url=this.url+query+"&fl=id+url+category+location+topics+date+latitude+longitude&wt=json&indent=true";
		$http({
			method: 'GET',
			url:this.url,
			header:{'Content-Type':'application/json'}
			}).success(function(data){
			this.j=data;
			$http({
				method: 'POST',
				url:'http://localhost:8080/myapp/user',
				header:{'Content-Type':'application/json'},
				data:this.j
			}).success(function(data){
				$scope.bubble=data.result[0];
				$scope.calendar=data.result[1];
				$scope.circlePacking=data.result[2];
				$scope.dataMaps=data.result[3];
				$scope.dendogram=data.result[4];				
				$scope.wordTopics=data.result[5];
				$scope.wordCategories=data.result[6];
				$scope.wordLocations=data.result[7];
				
				this.show=true;
			});
		});
	};	

}]);