<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Retirar</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
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
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</head>
<body>
    <div class="container">
        <h1>Retirar</h1>
        <form action="/retirar" method="post">
            <label for="monto">Valor:</label>
            <input type="number" id="monto" name="monto" required>
            <button type="submit" class="btn btn-primary">Retirar</button>
              <a href="<c:url value='/home'/>" class="btn btn-secondary">Volver al Menú</a>
        </form>
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
    </div>
</body>
</html>