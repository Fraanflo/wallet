<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <meta charset="UTF-8">
    <title>Registro de usuario</title>
    <style>
        body {
            background-color: #007bff;
            color: #343a40;
            padding-top: 23px;
        }
        .container {
            background-color: #fff;
            border-radius: 9px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 40px;
        }
        label {
            font-weight: bold;
        }
        .form-control {
            margin-bottom: 1rem;
        }
        .btn {
            margin-top: 1rem;
        }
    </style>
</head>
<body>
    <div class="container">
        <c:if test="${creado}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                Usuario creado exitosamente
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
        <form action="/registro" method="post" class="row g-3 needs-validation" novalidate>
        <h1>Registro de Usuario</h1>
            <div class="col-md-6">
                <label for="validationTooltip01" class="form-label">Nombre completo</label>
                <input type="text" name="nombre" class="form-control" id="validationTooltip01" required>
                <div class="invalid-feedback">
                    Por favor ingresa tu nombre completo.
                </div>
            </div>
            <div class="col-md-6">
                <label for="validationTooltip02" class="form-label">Correo electrónico</label>
                <input type="email" name="correo" class="form-control" id="validationTooltip02" required>
                <div class="invalid-feedback">
                    Por favor ingresa un correo electrónico válido.
                </div>
            </div>
            <div class="col-md-6">
                <label for="validationTooltip03" class="form-label">Contraseña</label>
                <input type="password" name="clave" class="form-control" id="validationTooltip03" required>
                <div class="invalid-feedback">
                    Por favor ingresa tu contraseña.
                </div>
            </div>
            <%@ page import="java.util.Date" %>
            <%@ page import="java.text.SimpleDateFormat" %>

            <%
              // Obtener la fecha actual
              Date currentDate = new Date();

              SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

              String formattedDate = formatter.format(currentDate);
            %>

            <input type="hidden" id="fecha_de_creacion" name="fecha_de_creacion" value="<%= formattedDate %>" >
            <input type="hidden" id="saldo" name="saldo" value="0" >
            
            <div class="col-12">
                <button class="btn btn-primary" type="submit">Registrarse</button>
                <a href="/login" class="btn btn-link">Volver al login</a>
            </div>
        </form>
    </div>
</body>
</html>
