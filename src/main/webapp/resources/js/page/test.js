$(document).ready(function() {

	
		$.postJSON("test", function(data) {
            if (data) {
            	alert("test");
            }
        });

});
