$(document).ready(function() {
	document.getElementById("displaySummary").innerHTML = "Click on the book to see it's summary";
	$.ajax({
		url: "../filterLibrary",
		method: "GET",
		data: { operation: 'category' },
		success: function(data, textStatus, jqXHR) {
			console.log(data);
			let obj = $.parseJSON(data);
			$.each(obj, function(key, value) {
				$('#category').append('<option value="'+value.category+'">'+value.category+'</option>')
			});
		},
		error: function(jqXHR, textStatus, errorThrown) {
			$('#category').append('<option>Category Unavailable</option>');
		},
		cache: false
	});

	$('#category').change(function() {
		$('#default').remove();
		$('.books').find('.ab').remove();
		$('.allBooks').find('.ab').remove();
		$('.displaySummary').replaceWith("Click on the book to see it's summary");
		let cid = $('#category').val();
		let data = {
			operation: "books",
			id: cid
		};

		$.ajax({
			url: "../filterLibrary",
			method: "GET",
			data: data,
			success: function(data, textStatus, jqXHR) {

				let obj = $.parseJSON(data);
				console.log(obj)
				console.log(obj.books);
				$.each(obj.books, function(key, value) { 
					$('.books').append('<div class="ab col-lg-2 col-md-4 d-flex flex-column text-dark"> <button class= "book-link text-light" value="'+value.bookId+'" > <img style="height: 180px; width: 120px" src="../img/'+value.picture+'"></button><a href="../addWishlist?'+value.bookId+'" class="btn btn-warning mt-2 text-white pl-2"><i class="fas fa-plus"></i> Wishlist </a></div > ');
                    
				});
				var page=obj.filterPageNo;
			   var showPrev=(page==0)?"disabled":"";
			   var showNext=(page+1 == obj.noOfPage) ? "disabled" : "";
				   document.getElementById("filter").innerHTML = '<span style="float: left"> <a href="../filterLibrary?pageNo='+(page-1)+'" class="btn btn-dark text-warning '+showPrev+'"><i class="fa-solid fa-caret-left"></i> Previous</a></span> <span style="float: right"><a href="../userLibrary?filterPageNo=(page+1)" class="btn btn-dark text-warning '+showNext+'">Next <i class="fa-solid fa-caret-right"></i> </a></span>';
              
			},
			error: function(jqXHR, textStatus, errorThrown) {
				$('.book-imgs').html('<img style="height: 180px;width: 120px" src="Image not found">');
			},
			cache: false
		});
	});
});

$(document).ready(function() {
	//                                  to show summary
	$(document).on('click', '.book-link', function(e) {
		e.preventDefault()
		console.log($(this).attr('value'))
		$.ajax({
			url: "../loadSummary",
			method: "GET",
			data: { id: $(this).attr('value') },
			success: function(data, textStatus, jqXHR) {
				let obj = $.parseJSON(data);
				document.getElementById("displaySummary").innerHTML = obj;

			},
			error: function(jqXHR, textStatus, errorThrown) {
				document.getElementById("displaySummary").innerHTML = "Couldn't fetch data."
			},
			cache: false
		});


	});
});

$(document).ready(function() {
	$('#searchBooks').click(function(e) {
		e.preventDefault();
		var newQuery = $('#newQuery').val();
		console.log($(this).attr('value'))
		console.log(newQuery);
		$.ajax({
			url: "../userSearch",  
			method: "GET",
			data: newQuery,
			success: function(data, textStatus, jqXHR) {
				let obj = $.parseJSON(data);
				console.log(data);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(errorThrown);
			},
			cache: false
		});


	});
});
