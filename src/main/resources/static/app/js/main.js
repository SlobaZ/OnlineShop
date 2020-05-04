var onlineshopApp = angular.module("onlineshopApp",["ngRoute"]);

onlineshopApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html'
		})
		.when('/korisnici', {
			templateUrl : '/app/html/korisnik.html'
		})
		.when('/korisnici/add', {
			templateUrl : '/app/html/korisnik-add.html'
		})
		.when('/korisnici/edit/:id', {
			templateUrl : '/app/html/korisnik-edit.html'
		})
		.when('/proizvodi', {
			templateUrl : '/app/html/proizvod.html'
		})
		.when('/proizvodi/add', {
			templateUrl : '/app/html/proizvod-add.html'
		})
		.when('/proizvodi/edit/:id', {
			templateUrl : '/app/html/proizvod-edit.html'
		})
		.when('/kupovine', {
			templateUrl : '/app/html/kupovine.html'
		})
		.when('/kupovine/add', {
			templateUrl : '/app/html/kupovina-add.html'
		})
		.when('/stavke/:id', {
			templateUrl : '/app/html/stavke.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);




//  KORISNICI

onlineshopApp.controller("KorisniciCtrl", function($scope, $http, $location, $window){
	
	var url = "/korisnici";
	
	$scope.korisnici = [];
	
	$scope.search = {};
	$scope.search.naziv = "";
	$scope.search.mesto = "";
		
	$scope.pageNum=0;
	$scope.totalPages = 1;
	
	
	var getKorisnici = function(){
		
		var config = {params:{}};
		
		if($scope.search.naziv != ""){
			config.params.naziv = $scope.search.naziv;
		}
		if($scope.search.mesto != ""){
			config.params.mesto = $scope.search.mesto;
		}

		config.params.pageNum = $scope.pageNum;
		
		var promise = $http.get(url, config);
		promise.then(
			function success(res){
				$scope.korisnici = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(){
				alert("Couldn't fetch Klijenti");
			}
		);
	}
	
	getKorisnici();
	
	
	$scope.goToAdd = function(){
		$location.path("/korisnici/add" );
	}
	
		
	$scope.goToEdit = function(id){
		$location.path("/korisnici/edit/" + id);
	}
	
	
	$scope.doDelete = function(id){
		var promise = $http.delete(url + "/" + id);
		promise.then(
			function success(){
				getKorisnici(); 
				alert("Succes!");	
			},
			function error(){
				alert("Couldn't delete the Korisnik")
			}
		);
		
	}
		
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getKorisnici();
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getKorisnici();
	}
	
	
	
});


onlineshopApp.controller("EditKorisnikCtrl", function($scope, $http, $routeParams, $location , $log , $window ){
	
	var korisniciUrl = "/korisnici/" + $routeParams.id;
		
	$scope.korisnik = {};
	$scope.korisnik.naziv = "";
	$scope.korisnik.jmbg = "";	
	$scope.korisnik.mesto = "";
	$scope.korisnik.adresa = "";
	$scope.korisnik.telefon = "";
	$scope.korisnik.brojracuna = "";
	$scope.korisnik.stanje = "";
	
	var getKorisnici = function(){
		$http.get(korisniciUrl).then(
			function success(res){
				$scope.korisnik = res.data;
			},
			function error(){
				alert("Couldn't fetch Korisnik.");
			}
		);
	}
	
	getKorisnici();
	
	
	$scope.doEdit = function(){
		$http.put(korisniciUrl, $scope.korisnik ).then(
			function success(){
				$location.path("/korisnici");
			},
			function error(console){
				alert("Niste dobro popunili podatke!"); 

			}
		);
	}
	
	
	
});



onlineshopApp.controller("AddKorisnikCtrl", function($scope, $http, $routeParams, $location){
	
	var korisniciUrl = "/korisnici";
	
	$scope.newKorisnik = {};
	$scope.newKorisnik.naziv = "";
	$scope.newKorisnik.mesto = "";
	$scope.newKorisnik.adresa = "";
	$scope.newKorisnik.jmbg = "";	
	$scope.newKorisnik.telefon = "";
	$scope.newKorisnik.mobilni = "";
	$scope.newKorisnik.brojracuna = "";
	$scope.newKorisnik.stanje = "";
	
	var getKorisnici = function(){
		$http.get(korisniciUrl).then(
			function success(res){
				$scope.korisnik = res.data;
			},
			function error(){
				alert("Couldn't fetch Klijent.");
			}
		);
	}
	
	getKorisnici();
	
	$scope.doAdd = function(){
		$http.post(korisniciUrl, $scope.newKorisnik).then(
			function success(res){
				$location.path("/korisnici");
			},
			function error(){
				alert("Niste dobro popunili podatke!");
			}
		);
	}
	
	
});












// PROIZVODI

onlineshopApp.controller("ProizvodiCtrl", function($scope, $http, $location, $window){
	
	var url = "/proizvodi";
	
	$scope.proizvodi = [];
	
	$scope.search = {};
	$scope.search.naziv = "";
	$scope.search.kolicina = "";
	$scope.search.cena = "";
		
	$scope.pageNum=0;
	$scope.totalPages = 1;
	
	
	var getProizvodi = function(){
		
		var config = {params:{}};
		
		if($scope.search.naziv != ""){
			config.params.naziv = $scope.search.naziv;
		}
		if($scope.search.kolicina != ""){
			config.params.kolicina = $scope.search.kolicina;
		}
		if($scope.search.cena != ""){
			config.params.cena = $scope.search.cena;
		}
		config.params.pageNum = $scope.pageNum;
		
		var promise = $http.get(url, config);
		promise.then(
			function success(res){
				$scope.proizvodi = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(){
				alert("Couldn't fetch Proizvodi");
			}
		);
	}
	
	getProizvodi();
	
	
	$scope.goToAdd = function(){
		$location.path("/proizvodi/add" );
	}
	
		
	$scope.goToEdit = function(id){
		$location.path("/proizvodi/edit/" + id);
	}
	
	
	$scope.doDelete = function(id){
		var promise = $http.delete(url + "/" + id);
		promise.then(
			function success(){
				getProizvodi(); 
				alert("Succes!");	
			},
			function error(){
				alert("Couldn't delete the Proizvod")
			}
		);
		
	}
		
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getProizvodi();
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getProizvodi();
	}
	
	
	
});


onlineshopApp.controller("EditProizvodCtrl", function($scope, $http, $routeParams, $location , $log , $window ){
	
	var proizvodiUrl = "/proizvodi/" + $routeParams.id;
		
	$scope.proizvod = {};
	$scope.proizvod.naziv = "";
	$scope.proizvod.kolicina = "";	
	$scope.proizvod.cena = "";
	
	var getProizvodi = function(){
		$http.get(proizvodiUrl).then(
			function success(res){
				$scope.proizvod = res.data;
			},
			function error(){
				alert("Couldn't fetch Proizvod.");
			}
		);
	}
	
	getProizvodi();
	
	
	$scope.doEdit = function(){
		$http.put(proizvodiUrl, $scope.proizvod ).then(
			function success(){
				$location.path("/proizvodi");
			},
			function error(console){
				alert("Niste dobro popunili podatke!"); 

			}
		);
	}
	
	
	
});



onlineshopApp.controller("AddProizvodCtrl", function($scope, $http, $routeParams, $location){
	
	var proizvodiUrl = "/proizvodi";
	
	$scope.newProizvod = {};
	$scope.newProizvod.naziv = "";
	$scope.newProizvod.kolicina = "";
	$scope.newProizvod.cena = "";
	
	var getProizvodi = function(){
		$http.get(proizvodiUrl).then(
			function success(res){
				$scope.korisnik = res.data;
			},
			function error(){
				alert("Couldn't fetch Proizvodi.");
			}
		);
	}
	
	getProizvodi();
	
	$scope.doAdd = function(){
		$http.post(proizvodiUrl, $scope.newProizvod).then(
			function success(res){
				$location.path("/proizvodi");
			},
			function error(){
				alert("Niste dobro popunili podatke!");
			}
		);
	}
	
	
});












//  KUPOVINE


onlineshopApp.controller("KupovineCtrl", function($scope, $http, $location){
	
	var url = "/kupovine";
	var proizvodiUrl = "/proizvodi";
	var korisniciUrl = "/korisnici";
	
	$scope.idKupovine = "";
	$scope.kolicinaStavke = "";
	
	$scope.kupovine = [];
	$scope.proizvodi = [];
	$scope.korisnici = [];
	$scope.stavke = [];
	
	
	$scope.search = {};
	$scope.search.korisnikId = "";
	$scope.search.sifra = "";
	$scope.search.ukupnaCena = "";	
	$scope.search.datumvremePocetak = "";	
	$scope.search.datumvremeKraj = "";	
	
	$scope.pageNum=0;
	$scope.totalPages = 1;
	
	var getKupovine = function(){
		var config = {params:{}};
		if($scope.search.korisnikId != ""){
			config.params.korisnikId = $scope.search.korisnikId;
		}
		if($scope.search.sifra != ""){
			config.params.sifra = $scope.search.sifra;
		}
		if($scope.search.ukupnaCena != ""){
			config.params.ukupnaCena = $scope.search.ukupnaCena; 
		}
		if($scope.search.datumvremePocetak != ""){
			config.params.datumvremePocetak = $scope.search.datumvremePocetak; 
		}
		if($scope.search.datumvremeKraj != ""){
			config.params.datumvremeKraj = $scope.search.datumvremeKraj; 
		}
		config.params.pageNum = $scope.pageNum;
		var promise = $http.get(url, config);
		promise.then(
			function success(response){
				$scope.kupovine = response.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(){
				alert("Couldn't fetch kupovine");
			}
		);
	}
	
	getKupovine();


	
	$scope.goToAdd = function(){
		$location.path("/kupovine/add" );
	}

	$scope.doOdaberiKupovinu = function(id){
		$location.path("/stavke/" + id);
	}
	
	

	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getKupovine();
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getKupovine();
	}
	
	
	
});


onlineshopApp.controller("AddKupovinaCtrl", function($scope, $http, $routeParams, $location){
	
	var kupovineUrl = "/kupovine";
	var korisniciUrl = "/korisnici";
	
	$scope.newKupovina = {};
	$scope.newKupovina.sifra = "";
	$scope.newKupovina.korisnikId = "";
	
	var getKorisnici = function(){
		return $http.get(korisniciUrl + "/sve").then(
			function success(res){
				$scope.korisnici = res.data;
				getKupovine();
			},
			function error(){
				alert("Couldn't fetch korisnik.");
			}
		);
	}
	
	getKorisnici();
	
	$scope.doAdd = function(){
		$http.post(kupovineUrl, $scope.newKupovina).then(
			function success(res){
				$location.path("/kupovine");
			},
			function error(){
				alert("Niste dobro popunili podatke!");
			}
		);
	}
	
	
});




onlineshopApp.controller("StavkeCtrl", function($scope, $http, $routeParams, $location , $log , $window ){
	
	var url = "/kupovine/" + $routeParams.id + "/stavke";
	var kupovinaUrl = "/kupovine";
		
	$scope.stavke = [];
	
	var getStavke = function(){
		return $http.get(url).then(
			function success(res){
				$scope.stavke = res.data;
			},
			function error(){
				alert("Couldn't fetch stavke.");
			}
		);
	}
	
	getStavke();
	
	
	
	$scope.doKupiStavku = function(id,kolicinaStavke){
		$http.post(url + "/" + id + "/" + kolicinaStavke + "/kupiStavku").then(
				function success(res){
					getStavke();
					alert("Succes!");
				},
				function error(){
					alert("Ne mogu izvrsiti odabranu Stavku!");
				}	
		);
	}
	
	
	$scope.doResetujStavku = function(id){
		$http.post(url + "/" + id +  "/resetujStavku").then(
				function success(res){
					getStavke();
					alert("Succes!");
				},
				function error(){
					alert("Ne mogu izvrsiti odabranu Stavku!");
				}	
		);
	}
	
	
	
	$scope.doKupi = function(){
		$http.post(kupovinaUrl + "/" +  $routeParams.id + "/kupi" ).then(
				function success(res){
					$location.path("/kupovine");
					alert("Succes!");
				},
				function error(){
					alert("Ne mogu izvrsiti odabranu Kupovinu!");
				}	
		);
	}
	
	
	
});








