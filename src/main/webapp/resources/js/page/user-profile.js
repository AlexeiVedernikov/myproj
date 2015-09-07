$(document).ready(function() {

    $("#findAndAddParent").hide();
    $("#findAndAddChild").hide();

    $('#tabs a').click(function (e) {
        e.preventDefault();

        $(this).tab('show');
        $("#findAndAddParent").hide();
        $("#addParent").show();
        $("#findAndAddChild").hide();
        $("#adChild").show();
    })

    $(".userProfile").click(function() {
        window.location.href="user-profile?id=" + $(this).parents("tr").attr("trID");
    });

});