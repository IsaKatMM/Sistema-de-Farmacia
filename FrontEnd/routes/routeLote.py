from flask import Blueprint, render_template, request, redirect, url_for, flash
import requests
import json

routeLote = Blueprint('routeLote', __name__)

# Ruta para listar los lotes
@routeLote.route('/lote/list')
def list_lote():
    # Hacemos la solicitud GET a la API para obtener los lotes
    r = requests.get("http://localhost:8099/myapp/lote/list")
    data = r.json()
    return render_template('lote/lista.html', lista=data["data"])

# Ruta para buscar los lotes por nombre de producto
@routeLote.route('/lote/search/<criterio>/<texto>')
def search_lote(criterio, texto):
    # URL base para la búsqueda
    url = "http://localhost:8099/myapp/lote/byProduct/"
    
    if criterio == "producto":
        r = requests.get(url + texto)
    else:
        return "Criterio no válido", 400

    data1 = r.json()
    if r.status_code == 200:
        if type(data1["data"]) is dict:
            list = []
            list.append(data1["data"])
            return render_template('lote/lista.html', lista=list)
        else:
            return render_template('lote/lista.html', lista=data1["data"])
    else:
        return render_template('lote/lista.html', lista=[], message="No se encontró el producto")

# Ruta para ordenar los lotes (por atributo y tipo)
@routeLote.route('/lote/order/<atributo>/<tipo>')
def order_lote(atributo, tipo):
    url = f"http://localhost:8099/myapp/lote/list/order/{atributo}/{tipo}"
    r = requests.get(url)
    data1 = r.json()

    if r.status_code == 200:
        return render_template('lote/lista.html', lista=data1["data"])
    else:
        return render_template('lote/lista.html', lista=[], message="No se pudo ordenar")

# Ruta para mostrar el formulario de registro de un nuevo lote
@routeLote.route('/lote/register')
def view_register_lote():
    return render_template('lote/registro.html')

# Ruta para guardar el lote nuevo
@routeLote.route('/lote/save', methods=["POST"])
def save_lote():
    headers = {'Content-type': 'application/json'}
    form = request.form
    dataF = {
        "idLote": form["idLote"],
        "cantidad": form["cantidad"],
        "fechaEntrega": form["fechaEntrega"],
        "precioLote": form["precioLote"],
        "fechaCaducidad": form["fechaCaducidad"],
        "producto": form["producto"]
    }
    
    # Realizar la solicitud POST a la API para guardar el lote
    r = requests.post("http://localhost:8099/myapp/lote/save", data=json.dumps(dataF), headers=headers)
    dat = r.json()

    if r.status_code == 200:
        flash("Lote guardado correctamente", category='info')
        return redirect("/lote/list")
    else:
        flash(str(dat["data"]), category='error')
        return redirect("/lote/list")

# Ruta para editar un lote
@routeLote.route('/lote/edit/<id>')
def view_edit_lote(id):
    r = requests.get(f"http://localhost:8099/myapp/lote/get/{id}")
    data1 = r.json()
    if r.status_code == 200:
        return render_template('lote/editar.html', lote=data1["data"])
    else:
        flash(data1["data"], category='error')
        return redirect("/lote/list")

# Ruta para actualizar el lote
@routeLote.route('/lote/update', methods=["POST"])
def update_lote():
    headers = {'Content-type': 'application/json'}
    form = request.form
    dataF = {
        "idLote": form["idLote"],
        "cantidad": form["cantidad"],
        "fechaEntrega": form["fechaEntrega"],
        "precioLote": form["precioLote"],
        "fechaCaducidad": form["fechaCaducidad"],
        "producto": form["producto"]
    }
    
    # Realizar la solicitud POST a la API para actualizar el lote
    r = requests.post("http://localhost:8099/myapp/lote/update", data=json.dumps(dataF), headers=headers)
    dat = r.json()

    if r.status_code == 200:
        flash("Lote actualizado correctamente", category='info')
        return redirect("/lote/list")
    else:
        flash(str(dat["data"]), category='error')
        return redirect("/lote/list")

# Ruta para eliminar un lote
@routeLote.route('/lote/eliminar/<int:id>', methods=["DELETE"])
def delete_lote(id):
    try:
        r = requests.delete(f"http://localhost:8099/myapp/lote/delete/{id}")
        if r.status_code == 200:
            return {"message": "Lote eliminado", "id": id}, 200
        else:
            return {"error": "No se pudo eliminar el lote"}, 400
    except Exception as e:
        return {"error": str(e)}, 500
