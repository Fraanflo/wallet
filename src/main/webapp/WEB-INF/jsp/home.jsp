<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
   <style>
        body {
            background-color: #007bff;
            color: #343a40;
            padding-top: 23px;
        }
        .container {
            background-color: #fff;
            border-radius: 20px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            padding: 40px;
        }
        h1 {
            color: #007bff;
        }
        h2 {
            background-color: #007bff;
            border-radius: 9px;
            margin-left: auto;
            margin-right: auto;
            padding: 10px 20px;
            color: #fff;
            max-width: fit-content;
            margin-bottom: 20px;
        }
        footer {
            background-color: #007bff;
            color: #fff;
            padding: 10px 10px;
            border-radius: 5px;
            margin-left: auto;
            margin-right: auto;
            max-width: fit-content;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container text-center">
        <header>
            <h1>Menú</h1>
            <h1 class="text-center">¡Bienvenido, <c:out value="${usuario.nombre}" />!</h1>
             <h2 class="text-center">Saldo Actual: <c:out value="${usuario.saldo}" /></h2>
            
            <div class="text-center">
                <h3 class="text-center">¿Qué deseas hacer?</h3>
            </div>
        </header>
       
        <form action="menu-usuario" method="post">
           
            <div class="d-grid gap-2 col-6 mx-auto">
                <button class="btn btn-primary" type="submit" name="accion" value="depositar">Depositar</button>
                <button class="btn btn-primary" type="submit" name="accion" value="retirar">Retirar</button>
                <button class="btn btn-primary" type="submit" name="accion" value="transferir">Transferir</button>
                <div class="text-center">
                        <a href="<c:url value='/logout'/>" class="btn btn-secondary">Cerrar Sesión</a>
                    </div>
            </div>
        </form>
    </div>

    <footer>
        <p>&copy; 2024, Billetera Digital. Todos los derechos reservados.</p>
    </footer>
</body>
</html>