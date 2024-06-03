<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Formulario de Venta</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h1>Formulario de Venta</h1>
            <form action="VentaControlador" method="post">
                <input type="hidden" name="id" value="${venta.id}">
                <div class="form-group">
                    <label for="cliente" class="form-label">Cliente</label>
                    <select name="cliente_id" class="form-control" id="cliente">
                        <option value="">--Seleccione--</option>
                        <c:forEach var="item" items="${lista_Clientes}">
                            <option value="${item.id}" 
                                <c:if test="${venta.cliente_id == item.id}">
                                    selected
                                </c:if>
                            >${item.nombre}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="producto" class="form-label">Producto</label>
                    <select name="producto_id" class="form-control" id="producto">
                        <option value="">--Seleccione--</option>
                        <c:forEach var="item" items="${lista_productos}">
                            <option value="${item.id}" 
                                <c:if test="${venta.producto_id == item.id}">
                                    selected
                                </c:if>
                            >${item.nombre}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="fecha" class="form-label">Fecha</label>
                    <input type="text" class="form-control" id="fecha" name="fecha" value="${venta.fecha}" placeholder="Escriba la fecha">
                </div>
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
