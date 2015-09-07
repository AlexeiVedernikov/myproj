/**
 * Created by anton on 04.09.14.
 */

$(document).ready(function() {

    $('#tabs a').click(function (e) {
        e.preventDefault();

        $(this).tab('show');
    })

    $('#tblBillUserMore').dataTable();
    $('#tblBillUser').dataTable();
});
