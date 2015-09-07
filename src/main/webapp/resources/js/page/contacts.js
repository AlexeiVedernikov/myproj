$(document).ready(function() {

    $("#contactSendMessage").click(function () {
        var wm = new ContactWebModel(
            $("#contactSubject").val() + " : " + $("#contactName").val() + " : " + $("#contactEmail").val(),
            $("#contactMessage").val()
        );

        $.postJSON("contacts", wm, function (data) {
            if (data && !hasError(data)) {
                alert("Success");
                return false;
            }
        })
    })
});

function ContactWebModel(email, message) {
    this.email = email;
    this.message = message;
}