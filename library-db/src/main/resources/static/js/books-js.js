
$('document').ready(function() {
	
	$('.table #editButton').on('click',function(event) {
		event.preventDefault();
		
		var href= $(this).attr('href');
		
		$.get(href, function(book, status){
			$('#bookidEdit').val(book.bookid);
			$('#titleEdit').val(book.title);
			$('#authorEdit').val(book.author);
			$('#genreEdit').val(book.genre);
			$('#lengthEdit').val(book.length);
			$('#publisherEdit').val(book.publisher);
		});
		
		$('#editModal').modal();
		
	});
	
	$('table #deleteButton').on('click',function(event) {
	
		event.preventDefault();
		var href = $(this).attr('href');
		$('#deleteModal #delRef').attr('href',href);
		$('#deleteModal').modal();
		
	});
	
	
});
