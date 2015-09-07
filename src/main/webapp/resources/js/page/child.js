$(document).ready(function() {

    bindChildProfileClick($(".childProfile"));
    bindChildDeleteClick($(".childDelete"));

    $("#childSave").click(function(){

        $.postJSON("child", {parentId:$("#userId").val(), childId:$(".radioChild:checked").parents("tr").attr("trChildIDOut")}, function(data) {
            if (data && !hasError(data)) {

                addChild(data);
                showNotification("Сохранение выполнено успешно", "success");

                return false;
            }
        });

    });

    $("#FindChild").click(function() {

        $.getJSON("childSearch", {name:$("#childName").val()}, function(data) {
                if (data && !hasError(data)) {
                    $(".bodyChild").html(function(){
                        var str = "";
                        for(var i = 0; i<data.value.length; i++) {
                            str += "<tr trChildIDOut = " + data.value[i].id + ">" +
                                "<td class = 'edit'>" +
                                "<input type='radio' name='radioChild' class='radioChild'>" +
                                "&nbsp" +
                                "<button type='button' class='btn btn-default childOutProfile'>" +
                                "<span class='glyphicon glyphicon glyphicon-user'>" + "</span>" +
                                "</button>" +
                                "</td>" +
                                "<td class='name'>" + data.value[i].name + "</td>" +
                                "<td class='email'>" + data.value[i].email + "</td>" +
                                "<td class='phone'>" + data.value[i].phone + "</td>" +
                                "<td class='description'>" + data.value[i].description + "</td>" +
                                "<td class='userType' userType=" + data.value[i].userType + ">" + data.value[i].userType + "</td>" +
                                "<td class='status' status=" + data.value[i].status + ">" + data.value[i].status + "</td>" +
                                "</tr>";
                        }
                        return str;
                    });
                    bindChildProfileClickOut( $(".childOutProfile"));
                    return false;
                }}
        )
    });

    $("#addChild").click(function(){

        $("#childName").val("");

        $("#addChild").hide();
        $("#findAndAddChild").show();

    });

});

function addChild(data){

    var strtable = "";

    strtable += "<tr trChildID = " + data.value.id + ">";
    strtable += "<td class = 'edit'>" +
        "<button type='button' class='btn btn-default childDelete'>" +
        "<span class='glyphicon glyphicon-trash'>" + "</span>" +
        "</button>"
        +
        "&nbsp"
        +
        "<button type='button' class='btn btn-default childProfile'>" +
        "<span class='glyphicon glyphicon glyphicon-user'>" + "</span>" +
        "</button>" +
        "</td>";
    strtable += "<td class = 'name'>" +  data.value.name + "</td>";
    strtable += "<td class = 'email'>" +  data.value.email + "</td>";
    strtable += "<td class = 'phone'>" +  data.value.name + "</td>";
    strtable += "<td class = 'description'>" +  data.value.description + "</td>";
    strtable += "<td class = 'userType' status = " + data.value.userType +">" + data.value.userType + "</td>";
    strtable += "<td class = 'status' status = " + data.value.status +">" + data.value.status + "</td>";
    strtable += "</tr>";

    $('#tblChild tr:last').after(strtable);

    bindChildProfileClick( $('#tblChild tr:last .childProfile') );
    bindChildDeleteClick( $('#tblChild tr:last .childDelete') );
}

function bindChildProfileClick(elements){
    elements.on("click", function() {
        window.location.href="user-profile?id=" + $(this).parents("tr").attr("trChildID");
    });
}

function bindChildProfileClickOut(elements){
    elements.on("click", function() {
        window.location.href="user-profile?id=" + $(this).parents("tr").attr("trChildIDOut");
    });
}

function bindChildDeleteClick(elements){
    elements.on("click", function() {
        var childId = $(this).parents("tr").attr("trChildID");
        $.deleteJSON("parentAndChilDelete", {childId:childId, parentId:$("#userId").val()}, function(data) {
            if (data && !hasError(data)) {

                $('[trChildID =' + childId + ']').remove();
                showNotification("Удаление выполнено", "warning");
                return false;
            }
        });
    });
}
