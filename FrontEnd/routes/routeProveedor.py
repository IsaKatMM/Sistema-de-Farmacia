from flask import Blueprint, render_template, request, redirect, url_for, flash
import requests
import json

routeProveedor = Blueprint('routeProveedor', __name__)

@routeProveedor.route('/proveedor/list')
def list_provetor():
    r = requests.get("http://localhost:8099/myapp/provetor/list")
    data = r.json()
    return render_template('proveedor/lista.html', lista=data["data"])

@routeProveedor.route('/proveedor/register')
def view_register_proveedor():
    r = requests.get("http://localhost:8099/myapp/provetor/listType")
    data = r.json()
    return render_template('proveedor/registro.html', lista=data["data"])

@routeProveedor.route('/proveedor/save', methods=["POST"])
def save_provetor():
    headers = {'Content-type': 'application/json'}
    form = request.form
    dataF = {
        "nombre": form["nom"],
        "apellido": form["ape"],
        "telefono": form["telef"],
        "nombreEmpresa": form["nombreEmpresa"],
        "tipoProducto": form["tipoProducto"],
        "pedidos": form["pedidos"],
        "productosDisponibles": form["productosDisponibles"],
    }
    # Realizar la solicitud POST a la API
    r = requests.post("http://localhost:8099/myapp/provetor/save", data=json.dumps(dataF), headers=headers)
    dat = r.json()
    if r.status_code == 200:
        flash("Se ha guardado correctamente", category='info')
        return redirect("/proveedores/proveedor/list")
    else:
        flash(str(dat["data"]), category='error')
    return redirect("/proveedores/proveedor/list")
