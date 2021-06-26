<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import ="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style><%@include file="/WEB-INF/jsp/css/create-style.css"%></style>
</head>
<body>
<center>
    <h1>
        Endereço
    </h1>

    <table width="100%" border="4px">
        <tr>
            <th>
                Logradouro
            </th>
            <th>
                Bairro
            </th>
            <th>
                Localidade
            </th>
            <th>
                UF
            </th>
        </tr>
        <c:forEach var="usuario" items="${usuarioLista}">
            <tr>
                <td>
                    <c:out value="${usuario.endereco.logradouro}" />
                </td>
                <td>
                    <c:out value="${usuario.endereco.bairro}" />
                </td>
                <td>
                    <c:out value="${usuario.endereco.localidade}" />
                </td>
                <td>
                    <c:out value="${usuario.endereco.uf}" />
                </td>
            </tr>
        </c:forEach>
    </table>

    <br><br>
    <a href="<%=request.getContextPath()%>/usuario/create">Adicionar Usuário</a>
    <br><br>
    <a href="<%=request.getContextPath()%>/usuario/list">Listar Usuários</a>
    <br><br>
    <a href="<%=request.getContextPath()%>/login/logout">Sair</a>

</center>
</body>
</html>