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
        <title>Historique des dépenses</title>
    </head>
    <body>
        <div class="container">
            <h2>Historique des dépenses</h2>
            <p><a href="DepenseController?action=insert" class="btn btn-success" role="button">Ajouter une transaction</a></p>
            <c:choose>
                <c:when test = "${depenses.size() == 0}">
                    <div class="alert alert-info">Aucune transaction n'a été enregistrée.</div>
                </c:when>    
                <c:otherwise>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>Dépense ID</th>
                                <th>Dépense</th>
                                <th>Description</th>
                                <th>Date de la transaction</th>
                                <th colspan=2>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${depenses}" var="depense">
                                <tr>
                                    <td><c:out value="${depense.depenseid}" /></td>
                                    <td><c:out value="${depense.depense}" /></td>
                                    <td><c:out value="${depense.description}" /></td>
                                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${depense.date_depense}" /></td>
                                    <td><a href="DepenseController?action=update&depenseid=<c:out value="${depense.depenseid}"/>" class="btn btn-primary">Modifier</a></td>
                                    <td><a href="DepenseController?action=delete&depenseid=<c:out value="${depense.depenseid}"/>" class="btn btn-danger" onclick="return confirm('Etes-vous sûr de vouloir supprimer cette transaction ?')">Supprimer</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>