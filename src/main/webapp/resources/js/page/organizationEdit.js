$(document).ready(function() {

    fillOrganizationEdit();

});

function fillOrganizationEdit() {
    if ($("#inpOrganizationIdDlg").val()) {
        setSelectedItemInSelectObj($("#organizationStatusDlg"), $("#status").val());
        setSelectedItemInSelectObj($("#selCountry"), $("#country").val());

        $.getJSON("regionSearch", {countryId: $("#country").val()}, function (data) {
                if (data && !hasError(data)) {
                    $("#selRegion").html(function () {
                        var str = "";
                        if (data.value.length >= 1) {
                            str += "<option value=\"disabled\">--Выберите регион--</option>";
                        }
                        for (var i = 0; i < data.value.length; i++) {
                            str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>"
                        }
                        return str;
                    });
                    setSelectedItemInSelectObj($("#selRegion"), $("#region").val());
                    return false;
                }
            }
        );

        $.getJSON("districtSearch", {regionId: $("#region").val()}, function (data) {
                if (data && !hasError(data)) {
                    $("#selDistrict").html(function () {
                        var str = "";
                        if (data.value.length >= 1) {
                            str += "<option value=\"disabled\">--Выберите район--</option>";
                        } else if (data.value.length < 1) {
                            str += "<option value=\"disabled\">--Нет районов--</option>";
                        }
                        for (var i = 0; i < data.value.length; i++) {
                            str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>";
                        }
                        return str;
                    });
                    if ($("#district").val()) {
                        setSelectedItemInSelectObj($("#selDistrict"), $("#district").val());
                    } else {
                        setSelectedItemInSelectObj($("#selDistrict"), "disabled");
                    }
                    return false;
                }
            }
        );

        if ($("#district").val()) {
            $.getJSON("townSearch", {districtId: $("#district").val()}, function (data) {
                    if (data && !hasError(data)) {
                        $("#selTown").html(function () {
                            var str = "";
                            if (data.value.length >= 1) {
                                str += "<option value=\"disabled\">--Выберите город--</option>";
                            }
                            for (var i = 0; i < data.value.length; i++) {
                                str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>"
                            }
                            return str;
                        });
                        if ($("#town").val()) {
                            setSelectedItemInSelectObj($("#selTown"), $("#town").val());
                        } else {
                            setSelectedItemInSelectObj($("#selTown"), "disabled");
                        }
                        return false;
                    }
                }
            );
        } else {
            $.getJSON("townSearch", {regionId: $("#region").val()}, function (data) {
                    if (data && !hasError(data)) {
                        $("#selTown").html(function () {
                            var str = "";
                            if (data.value.length >= 1) {
                                str += "<option value=\"disabled\">--Выберите район/город--</option>";
                            }
                            for (var i = 0; i < data.value.length; i++) {
                                str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>";
                            }
                            return str;
                        });
                        if ($("#town").val()) {
                            setSelectedItemInSelectObj($("#selTown"), $("#town").val());
                        } else {
                            setSelectedItemInSelectObj($("#selTown"), "disabled");
                        }
                        return false;
                    }
                }
            );
        }

        $.getJSON("townAreaSearch", {townId: $("#town").val()}, function (data) {
                if (data && !hasError(data)) {
                    $("#selTownArea").html(function () {
                        var str = "";
                        if (data.value.length >= 1) {
                            str += "<option value=\"disabled\">--Выберите микрорайон--</option>";
                        } else if (data.value.length < 1) {
                            str += "<option value=\"disabled\">--Нет микрорайонов--</option>";
                        }
                        for (var i = 0; i < data.value.length; i++) {
                            str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>";
                        }
                        return str;
                    });

                    if ($("#townArea").val()) {
                        setSelectedItemInSelectObj($("#selTownArea"), $("#townArea").val());
                    } else {
                        setSelectedItemInSelectObj($("#selTownArea"), "disabled");
                    }
                    return false;
                }
            }
        );
    }
    else {
        setSelectedItemInSelectObj( $("#organizationStatusDlg"),"Active");
        setSelectedItemInSelectObj( $("#selCountry"), "disabled");
        $("#selTownArea").html("<option value=\"disabled\">--Выберите страну--</option>");
        $("#selTown").html("<option value=\"disabled\">--Выберите страну--</option>");
        $("#selRegion").html("<option value=\"disabled\">--Выберите страну--</option>");
        $("#selDistrict").html("<option value=\"disabled\">--Выберите страну--</option>");
        setSelectedItemInSelectObj( $("#selTownArea"), "disabled");
        setSelectedItemInSelectObj( $("#selTown"), "disabled");
        setSelectedItemInSelectObj( $("#selRegion"), "disabled");
        setSelectedItemInSelectObj( $("#selDistrict"), "disabled");
    }
}