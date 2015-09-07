$(document).ready(function() {

    $(".changeTown").change(function() {
        $.getJSON("townAreaSearch", {townId:$(".changeTown option:selected").val()}, function(data) {
                if (data && !hasError(data)) {

                    $("#selTownArea").html(function(){

                        var str = "";
                        if (data.value.length > 1) {
                            str += "<option value=\"disabled\">--Выберите микрорайон--</option>";
                        } else if (data.value.length < 1) {
                            str += "<option value=\"disabled\">--Нет микрорайонов--</option>";
                        }

                        for(var i = 0; i<data.value.length; i++) {
                            str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>"
                        }

                        return str;
                    });
                    $('selTownArea').selectpicker('refresh');

                    return false;
                }}
        );
    });

    $(".changeDistrict").change(function() {
        $.getJSON("townSearch", {districtId:$(".changeDistrict option:selected").val()}, function(data) {
                if (data && !hasError(data)) {

                    $("#selTown").html(function(){

                        var str = "";
                        if (data.value.length > 1) {
                            str += "<option value=\"disabled\">--Выберите город--</option>";
                        } else if (data.value.length < 1) {
                            str += "<option value=\"disabled\">--Нет городов--</option>";
                        }

                        for(var i = 0; i<data.value.length; i++) {
                            str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>"
                        }

                        return str;
                    });

                    $("#selTownArea").html(function(){
                        var str = "<option value=\"disabled\">--Выберите город--</option>";
                        return str;
                    });

                    $('#selTown').selectpicker('refresh');
                    $('#selTownArea').selectpicker('refresh');

                    if (data.value.length == 1) {
                        $("#selTown").trigger("change");
                    }

                    return false;
                }}
        );
    });

    $(".changeRegion").change(function() {
        $.getJSON("districtSearch", {regionId:$(".changeRegion option:selected").val()}, function(data) {
                if (data && !hasError(data)) {

                    $("#selDistrict").html(function(){

                        var str = "";
                        if (data.value.length > 0) {
                            str += "<option value=\"disabled\">--Выберите район--</option>";
                        } else if (data.value.length < 1) {
                            str += "<option value=\"disabled\">--Нет районов--</option>";
                        }

                        for(var i = 0; i<data.value.length; i++) {
                            str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>";
                        }

                        return str;
                    });
                    $('#selDistrict').selectpicker('refresh');

                    return false;
                }}
        );

        $("#selTownArea").html(function(){
            var str = "<option value=\"disabled\">--Выберите район/город--</option>";
            return str;
        });
        $('#selTownArea').selectpicker('refresh');

        $.getJSON("townSearch", {regionId:$(".changeRegion option:selected").val()}, function(data) {
                if (data && !hasError(data)) {

                    $("#selTown").html(function(){

                        var str = "";
                        if (data.value.length > 1) {
                            str += "<option value=\"disabled\">--Выберите район/город--</option>";
                        } else if (data.value.length < 1) {
                            str += "<option value=\"disabled\">--Выберите район--</option>";
                            $("#selTownArea").html(function(){
                                var str = "<option value=\"disabled\">--Выберите район--</option>";
                                return str;
                            });
                        }

                        for(var i = 0; i<data.value.length; i++) {
                            str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>";
                        }

                        return str;
                    });

                    $('#selTown').selectpicker('refresh');
                    $('#selTownArea').selectpicker('refresh');

                    if (data.value.length == 1) {
                        $("#selTown").trigger("change");
                    }

                    return false;
                }}
        );

    });

    $(".changeCountry").change(function() {
        $.getJSON("regionSearch", {countryId:$(".changeCountry option:selected").val()}, function(data) {
                if (data && !hasError(data)) {

                    $("#selRegion").html(function(){

                        var str = "";
                        if (data.value.length > 1) {
                            str += "<option value=\"disabled\">--Выберите регион--</option>";
                        } else if (data.value.length < 1) {
                            str += "<option value=\"disabled\">--Нет регионов--</option>";
                        }

                        for(var i = 0; i<data.value.length; i++) {
                            str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>"
                        }

                        return str;
                    });

                    $("#selDistrict").html(function(){
                        var str = "<option value=\"disabled\">--Выберите регион--</option>";
                        return str;
                    });
                    $("#selTown").html(function(){
                        var str = "<option value=\"disabled\">--Выберите регион--</option>";
                        return str;
                    });
                    $("#selTownArea").html(function(){
                        var str = "<option value=\"disabled\">--Выберите регион--</option>";
                        return str;
                    });

                    $('#selRegion').selectpicker('refresh');
                    $('#selDistrict').selectpicker('refresh');
                    $('#selTown').selectpicker('refresh');
                    $('#selTownArea').selectpicker('refresh');

                    if (data.value.length == 1) {
                        $("#selRegion").trigger("change");
                    }

                    return false;
                }}
        );
    });
});

function fillEdit(tr) {
    if (tr) {
        setSelectedItemInSelectObj($("#selCountry"), tr.children("td.country").attr("countryId"));

        $.getJSON("regionSearch", {countryId:tr.children("td.country").attr("countryId")}, function(data) {
                if (data && !hasError(data)) {
                    $("#selRegion").html(function() {
                        var str = "";
                        if (data.value.length > 1) {
                            str += "<option value=\"disabled\">--Выберите регион--</option>";
                        }
                        for(var i = 0; i<data.value.length; i++) {
                            str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>"
                        }
                        return str;
                    });
                    setSelectedItemInSelectObj($("#selRegion"), tr.children("td.region").attr("regionId"));
                    return false;
                }}
        );

        $.getJSON("districtSearch", {regionId:tr.children("td.region").attr("regionId")}, function(data) {
                if (data && !hasError(data)) {
                    $("#selDistrict").html(function(){
                        var str = "";
                        if (data.value.length >= 1) {
                            str += "<option value=\"disabled\">--Выберите район--</option>";
                        } else if (data.value.length < 1) {
                            str += "<option value=\"disabled\">--Нет районов--</option>";
                        }
                        for(var i = 0; i<data.value.length; i++) {
                            str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>";
                        }
                        return str;
                    });
                    if (tr.children("td.district").attr("districtId")) {
                        setSelectedItemInSelectObj($("#selDistrict"), tr.children("td.district").attr("districtId"));
                    } else {
                        setSelectedItemInSelectObj( $("#selDistrict"), "disabled");
                    }
                    return false;
                }}
        );

        if (tr.children("td.district").attr("districtId")) {
            $.getJSON("townSearch", {districtId:tr.children("td.district").attr("districtId")}, function(data) {
                    if (data && !hasError(data)) {
                        $("#selTown").html(function(){
                            var str = "";
                            if (data.value.length > 1) {
                                str += "<option value=\"disabled\">--Выберите город--</option>";
                            }
                            for(var i = 0; i<data.value.length; i++) {
                                str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>"
                            }
                            return str;
                        });
                        setSelectedItemInSelectObj($("#selTown"), tr.children("td.town").attr("townId"));
                        return false;
                    }}
            );
        } else {
            $.getJSON("townSearch", {regionId:tr.children("td.region").attr("regionId")}, function(data) {
                    if (data && !hasError(data)) {
                        $("#selTown").html(function(){
                            var str = "";
                            if (data.value.length > 1) {
                                str += "<option value=\"disabled\">--Выберите район/город--</option>";
                            }
                            for(var i = 0; i<data.value.length; i++) {
                                str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>";
                            }
                            return str;
                        });
                        setSelectedItemInSelectObj($("#selTown"), tr.children("td.town").attr("townId"));
                        return false;
                    }}
            );
        }
    } else {
        setSelectedItemInSelectObj( $("#selCountry"), "disabled");
        $("#selTown").html("<option value=\"disabled\">--Выберите страну--</option>");
        $("#selRegion").html("<option value=\"disabled\">--Выберите страну--</option>");
        $("#selDistrict").html("<option value=\"disabled\">--Выберите страну--</option>");
        setSelectedItemInSelectObj( $("#selTown"), "disabled");
        setSelectedItemInSelectObj( $("#selRegion"), "disabled");
        setSelectedItemInSelectObj( $("#selDistrict"), "disabled");
    }
}