(function(){
	jQuery(init);
	var addMovieBtn;
	var imdbFld;
	var titleFld;
	var plotFld;
	var posterFld;
	function init(){
		addMovieBtn=$("#addMovieBtn");
		addMovieBtn.click(addMovie);
		function findAllMovies(){
			var url="http://localhost:8080/api/movie";
			jQuery.ajax({
				url: url,
				success: renderMovies
			});
		}
		findAllMovies();
		function addMovie(){
			imdbFld=$("#imdbFld");
			titleFld=$("#titleFld");
			plotFld=$("#plotFld");
			posterFld=$("#posterFld");
			
			var imdb=imdbFld.val();
			var title=titleFld.val();
			var plot=plotFld.val();
			var poster=posterFld.val();
			var movie={
					"imdbId":imdb,
					"title":title,
					"plot":plot,
					"poster":poster
			}
			$.ajax({
				url:"/api/movie",
				type:"post",
				data:JSON.stringify(movie),
				contentType:"application/json",
				dataType:'json',
				success:findAllMovies()
			});
		}
		function renderMovies(movies){
			var tbody=$("tbody");
			tbody.empty();
			for(var m in movies){
				var movie=movies[m];
				var tr=$("<tr>");
				var td=$("<td>");
				td.append(movie.imdbId);
				tr.append(td);
				
				td=$("<td>");
				td.append(movie.title);
				tr.append(td);
				
				td=$("<td>");
				td.append(movie.plot);
				tr.append(td);
				
				td=$('<td>');
				td.append(movie.poster);
				tr.append(td);
				
				td=$('<td><span class="glyphicon glyphicon-remove"></span></td>');
				td.attr("id",movie.imdbId);
				td.click(deleteMovie);
				tr.append(td);
				
				tbody.append(tr);
			}
		}
		function deleteMovie(event){
			var td=$(event.currentTarget);
			var imdbId=td.attr("id");
			$.ajax({
				url:"/api/movie/"+imdbId,
				type:'delete',
				success:findAllMovies()
			});
		}
	}
})();