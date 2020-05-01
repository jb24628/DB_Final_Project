
$('document').ready(function() {
	
	$('.table #editButton').on('click',function(event) {
		event.preventDefault();
		
		var href= $(this).attr('href');
		
		$.get(href, function(user, status){
			$('#idEdit').val(user.id);
			$('#lnameEdit').val(user.lname);
			$('#fnameEdit').val(user.fname);
			$('#sexEdit').val(user.sex);
			$('#addressEdit').val(user.address);
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
