<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>Historial de Transacciones</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<style>
body {
    background-color: #007bff;
    padding-top: 20px;
    
}



.container {
    padding-bottom: 20px;
    justify-content: center;
    align-items: center;
    background-color: white;
    border-radius: 1rem;
}
 
</style>
</head>
<body>
    <div class="container">
        <h1 class="text-center">Historial de Transacciones</h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID Transacción</th>
                    <th>ID Usuario Emisor</th>
                    <th>ID Usuario Receptor</th>
                    <th>Valor</th>
                    <th>Fecha de Transacción</th>
                    <th>ID Moneda</th>
                </tr>
            </thead>
            <tbody>
               <c:forEach var="transaccion" items="${historial}">
    <tr>
        <td>${transaccion.transaction_Id}</td>
        <td>${transaccion.sender_User_Id}</td>
        <td>${transaccion.receiver_User_Id}</td>
        <td>${transaccion.valor}</td>
        <td>${transaccion.transaction_Date}</td>
        <td>${transaccion.currency_Id}</td>
    </tr>
</c:forEach>
 

            </tbody>
        </table>
        <div class="text-center">
            <a href="<c:url value='/home'/>" class="btn btn-secondary">Volver al Menú</a>
        </div>
    </div>
</body>
</html>