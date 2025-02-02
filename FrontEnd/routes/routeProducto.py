from flask import Blueprint, render_template, request, redirect, url_for, flash
import requests
import json

# Crear un Blueprint para las rutas de productos
routeProducto = Blueprint('routeProducto', __name__)

@routeProducto.route('/product/list', methods=["GET"])
def list_producto():
    try:
        # Realizar la solicitud al API
        r = requests.get("http://localhost:8099/myapp/product/list")
        data = r.json()

        # Verificar si la respuesta del API es exitosa
        if r.status_code == 200:
            # Pasar los productos a la plantilla
            return render_template('producto/lista.html', lista=data.get("data", []))
        else:
            # Mostrar un mensaje de error si el API falla
            flash(f"Error al obtener los productos: {data.get('msg', 'Error desconocido')}", "error")
            return render_template('producto/lista.html', lista=[])
    except Exception as e:
        # Manejar errores de conexión o excepciones inesperadas
        flash(f"Error al conectar con el API: {str(e)}", "error")
        return render_template('producto/lista.html', lista=[])