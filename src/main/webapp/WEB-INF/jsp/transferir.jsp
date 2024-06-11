<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>Transferir</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<style>
body {
    background-color: #007bff;
    padding-top: 20px;
    
}



.container {
     padding: 20px 200px;
    justify-content: center;
    align-items: center;
    background-color: white;
    border-radius: 1rem;
}
 
</style>
</head>
<body>
      <div class="container">
        <h1>Transferir</h1>
         <form action="/transferir" method="post">
            <div class="mb-3">
                <label for="receiverUserId" class="form-label">ID del Destinatario:</label>
                <input type="number" id="receiverUserId" name="receiverUserId" class="form-control">
            </div>
            <div class="mb-3">
                <label for="valor" class="form-label">Monto:</label>
                <input type="number" id="valor" name="valor" class="form-control" required>
            </div>
            
     
             <script>
        // Obtener los mensajes de alerta 
        const alertaTitulo = '<%= (request.getAttribute("alertaTitulo") != null) ? request.getAttribute("alertaTitulo") : "" %>';
        const alertaMensaje = '<%= (request.getAttribute("alertaMensaje") != null) ? request.getAttribute("alertaMensaje") : "" %>';
        const alertaTipo = '<%= (request.getAttribute("alertaTipo") != null) ? request.getAttribute("alertaTipo") : "" %>';

        if (alertaTitulo && alertaMensaje && alertaTipo) {
            Swal.fire({
                title: alertaTitulo,
                text: alertaMensaje,
                icon: alertaTipo.toLowerCase() 
            });
        }
    </script>

            <button type="submit" class="btn btn-primary">Transferir</button>
             <a href="<c:url value='/home'/>" class="btn btn-secondary">Volver al Menú</a>
            
        </form>
    </div>
</body>
</html>

