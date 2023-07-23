<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/bootstrap.min.css" rel="stylesheet" />
        <link type="text/css" href="css/style.css" rel="stylesheet" />
        <link type="text/css" href="css/jquery-ui/jquery-ui.min.css" rel="stylesheet" />
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>
        <title>Ajouter une nouvelle transaction</title>
    </head>
    <body>
        <script>
            $(function () {
                $('input[name=date_transaction]').datepicker({ dateFormat: 'dd/mm/yy' }).val();
            });
        </script>

        <div class="container">
            <h2><%= request.getAttribute("title")%> User</h2>
            <form method="POST" action='DepenseController' name="frmAddDepense">
                <input type="hidden" name="token" value="<%= session.getAttribute("token")%>" />
                <input type="hidden" readonly="readonly" name="depenseid" value="<c:out value="${depense.depenseid}" />" />
                <div class="form-group">
                    <label>Montant</label>
                    <input type="text" name="depense" value="<c:out value="${depense.depense}" />" />
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <input type="text" name="description" value="<c:out value="${depense.description}" />" />
                </div>
                <div class="form-group">
                    <label>Date de transaction</label>
                    <input type="text" name="date_transaction" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${depense.date_transaction}" />" autocomplete="off" />
                </div>
                <input type="button" onclick="location.href='index.jsp'" class="btn btn-outline-secondary" value="Retour">
                <input type="submit" value="Envoyer" class="btn btn-success" />
            </form>
        </div>
    </body>
</html>