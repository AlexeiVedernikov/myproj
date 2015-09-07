<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@include file="../../include/header.jsp"%>
		<title><fmt:message key="my3o.ui.organizations.title" /> - <fmt:message key="my3o.ui.title" /></title>

        <script src="${applicationScope.resourceServerUrl}js/page/organization.js"></script>
        <script src="${applicationScope.resourceServerUrl}js/page/organizationEdit.js"></script>
        <script src="${applicationScope.resourceServerUrl}js/page/selects.js"></script>
    
    </head>
    <body>
        <%@include file="../../include/header-body.jsp"%>
        <%@include file="../../include/menu-top.jsp"%>

        <div class="container min_H_400">
            <form class="form-horizontal">
                <input type="hidden" id="inpOrganizationIdDlg" value="${test.id }" />

                <h4 class="modal-title" id="headerEdit">Организация</h4>
                <hr>

                <div class="form-group">
                    <label for="organizationNameDlg" class="col-sm-2 control-label">Название</label>
                    <div class="col-sm-3">
                        <input type="text" id="organizationNameDlg" class="form-control" value="${test.name}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="organizationDescriptionDlg" class="col-sm-2 control-label">Примечание</label>
                    <div class="col-sm-3">
                        <input type="text" id="organizationDescriptionDlg" class="form-control" value="${test.description}"/>
                    </div>
                </div>

                <input id = "status" type="hidden" value="${test.status}"/>
                <div class="form-group">
                    <label for="organizationStatusDlg" class="col-sm-2 control-label">Статус</label>
                    <div class="col-sm-3">
                        <select id ="organizationStatusDlg" class="selectpicker" data-width="265px">
                            <option value="Active"> Активная</option>
                            <option value="Inactive"> Неактивная</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="organizationPhoneDlg" class="col-sm-2 control-label">Телефон</label>
                    <div class="col-sm-3">
                        <input type="text" id="organizationPhoneDlg" class="form-control" value="${test.phone}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="organizationAddressDlg" class="col-sm-2 control-label">Адрес</label>
                    <div class="col-sm-3">
                        <input type="text" id="organizationAddressDlg" class="form-control" value="${test.address}"/>
                    </div>
                </div>

                <input id = "country" type="hidden" value="${test.locationDto.countryDto.id}"/>
                <div class="form-group">
                    <label for="selCountry" class="col-sm-2 control-label">Страна</label>
                    <div class="col-sm-3">
                        <select id="selCountry" class="changeCountry selectpicker" data-width="265px">
                            <option value="disabled">--Выберите страну--</option>
                            <c:forEach var="item" items="${countryList}">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <input id = "region" type="hidden" value="${test.locationDto.regionDto.id}"/>
                <div class="form-group">
                    <label for="selRegion" class="col-sm-2 control-label">Регион</label>
                    <div class="col-sm-3">
                        <select id="selRegion" class="changeRegion selectpicker" data-width="265px">
                            <option value="disabled">--Выберите страну--</option>
                        </select>
                    </div>
                </div>

                <input id = "district" type="hidden" value="${test.locationDto.districtDto.id}"/>
                <div class="form-group">
                    <label for="selDistrict" class="col-sm-2 control-label"	>Район</label>
                    <div class="col-sm-3">
                        <select id="selDistrict" class="changeDistrict selectpicker" data-width="265px">
                            <option value="disabled">--Выберите страну--</option>
                        </select>
                    </div>
                </div>

                <input id = "town" type="hidden" value="${test.locationDto.townDto.id}"/>
                <div class="form-group">
                    <label for="selTown" class="col-sm-2 control-label">Город</label>
                    <div class="col-sm-3">
                        <select id="selTown" class="changeTown selectpicker" data-width="265px">
                            <option value="disabled">--Выберите страну--</option>
                        </select>
                    </div>
                </div>

                <input id = "townArea" type="hidden" value="${test.locationDto.townAreaDto.id}"/>
                <div class="form-group">
                    <label for="selTownArea" class="col-sm-2 control-label">Микрорайон</label>
                    <div class="col-sm-3">
                        <select id="selTownArea" class="selectpicker" data-width="265px">
                            <option value="disabled">--Выберите страну--</option>
                        </select>
                    </div>
                </div>

                <br>
                <div class="col-sm-3 control-label">
                    <input type="button" data-dismiss="modal" class="btn btn-primary" value="Сохранить" id ="btnSaveOrganizationDlg">
                    <input type="button" id="organizationCancelDlg"  class="btn btn-default" value="Отмена">
                </div>
            </form>
        </div> <!-- /container -->
        <br>
        <%@include file="../../include/menu-bottom.jsp"%>
    </body>
</html>
