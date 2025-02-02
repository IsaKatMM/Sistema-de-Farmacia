
from flask import Blueprint, Flask, render_template, redirect, url_for, flash
import json
import requests

routeProducto = Blueprint('routeProducto', __name__)

API_BASE_URL = 'http://localhost:8081/myapp/product'

@routeProducto.route('/')
def inicio():
    return render_template('inicio.html')

@routeProducto.route('/producto/save', methods=['GET'])
def save_producto_form():
    # Obtener los tipos de producto desde la API
    response = requests.get(f'{API_BASE_URL}/listType')
    tipos = response.json().get('data', [])
    return render_template('fragmento/Producto/registro.html', tipos=tipos)

@routeProducto.route('/producto/save', methods=['POST'])
def save_producto():
    headers = {'Content-Type': 'application/json'}
    form = requests.form

    data = {
        "NombreProducto": form["NombreProducto"],
        "Laboratorio": form["Laboratorio"],
        "RequiereReceta": form.get("RequiereReceta", "false") == "true",
        "Categoria": form["Categoria"],
        "Peso": int(form["Peso"]),
        "Marca": form["Marca"],
        "TipoProducto": form["TipoProducto"]
    }

    response = requests.post(f'{API_BASE_URL}/save', data=json.dumps(data), headers=headers)
    if response.status_code == 200:
        flash("Producto guardado correctamente", 'success')
    else:
        flash("Error al guardar el producto", 'error')
    return redirect(url_for('inicio'))

@routeProducto.route('/producto/list', methods=['GET'])
def list_producto():
    response = requests.get(f'{API_BASE_URL}/list')
    productos = response.json().get('data', [])
    return render_template('fragmento/Producto/lista.html', productos=productos)

@routeProducto.route('/producto/update/<int:id>', methods=['GET'])
def update_producto_form(id):
    response = requests.get(f'{API_BASE_URL}/get/{id}')
    producto = response.json().get('data', {})
    tipos_response = requests.get(f'{API_BASE_URL}/listType')
    tipos = tipos_response.json().get('data', [])
    return render_template('fragmento/Producto/actualizar.html', producto=producto, tipos=tipos)

@routeProducto.route('/producto/update', methods=['POST'])
def update_producto():
    headers = {'Content-Type': 'application/json'}
    form = requests.form

    data = {
        "IdProducto": int(form["IdProducto"]),
        "NombreProducto": form["NombreProducto"],
        "Laboratorio": form["Laboratorio"],
        "RequiereReceta": form.get("RequiereReceta", "false") == "true",
        "Categoria": form["Categoria"],
        "Peso": int(form["Peso"]),
        "Marca": form["Marca"],
        "TipoProducto": form["TipoProducto"]
    }

    response = requests.post(f'{API_BASE_URL}/update', data=json.dumps(data), headers=headers)
    if response.status_code == 200:
        flash("Producto actualizado correctamente", 'success')
    else:
        flash("Error al actualizar el producto", 'error')
    return redirect(url_for('list_producto'))

@routeProducto.route('/producto/search', methods=['GET'])
def search_producto_form():
    tipos_response = requests.get(f'{API_BASE_URL}/listType')
    tipos = tipos_response.json().get('data', [])
    return render_template('fragmento/Producto/buscar.html', tipos=tipos)

@routeProducto.route('/producto/search', methods=['POST'])
def search_producto():
    headers = {'Content-Type': 'application/json'}
    form = requests.form

    data = {
        "NombreProducto": form.get("NombreProducto", ""),
        "TipoProducto": form.get("TipoProducto", "")
    }

    response = requests.post(f'{API_BASE_URL}/search', data=json.dumps(data), headers=headers)
    productos = response.json().get('data', [])
    return render_template('fragmento/Producto/lista.html', productos=productos)
