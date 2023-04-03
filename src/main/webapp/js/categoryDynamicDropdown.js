$(document).ready(function(){
		$.ajax({
			url:"../GetCatServlet",
			method:"GET",
			data:{operation:'category'},
			success: function(data,textStatus,jqXHR){
				console.log(data);
				let obj=$.parseJSON(data);
				$.each(obj,function(key,value){
					$('#category').append('<option value="'+value.category+'">'+value.category+'</option>') 
					});
			},
			error: function (jqXHR, textStatus, errorThrown) {
                $('#category').append('<option>Category Unavailable</option>');
            },
            cache: false
        });
		
		  $('#category').change(function () {
              $('#books').find('option').remove();
              $('#books').append('<option>Select Book</option>'); 

              let cid = $('#category').val();
              let data = {
                  operation: "books",
                  id: cid
              };

              $.ajax({
                  url: "../GetCatServlet",
                  method: "GET",
                  data: data,
                  success: function (data, textStatus, jqXHR) {
                      console.log(data);
                      let obj = $.parseJSON(data);
                      $.each(obj, function (key, value) {
                          $('#books').append('<option value="' + value.bookId + '">' + value.bookName + '</option>')
                      });
                 
                  },
                  error: function (jqXHR, textStatus, errorThrown) {
                      $('#books').append('No results');
                  },
                  cache: false
              });
          });	
		});
