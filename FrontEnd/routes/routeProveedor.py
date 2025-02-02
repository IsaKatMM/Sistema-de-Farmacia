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
        "cedula": form["ced"],
        "nombre": form["nom"],
        "apellido": form["ape"],
        "telefono": form["telef"],
        "nombreEmpresa": form["nombreEmpresa"],
        "tipoProductos": form["tipoProductos"],
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



@routeProveedor.route('/proveedor/edit/<id>')
def view_edit_proveedor(id):
    r = requests.get("http://localhost:8099/myapp/provetor/listType")
    data = r.json()
    r1 = requests.get("http://localhost:8099/myapp/provetor/get/"+id)
    data1 = r1.json()
    if(r1.status_code == 200):
        return render_template('proveedor/editar.html', lista = data["data"], provetor = data1["data"])
    else:
        flash(data1["data"], category='error')
        return redirect("/proveedor/list")
    

@routeProveedor.route('/proveedor/update', methods=["POST"])
def update_provetor():
    headers = {'Content-type': 'application/json'}
    form = request.form
    dataF = {
        "id": form["id"],
        "cedula": form["ced"],
        "nombre": form["nom"],
        "apellido": form["ape"],
        "telefono": form["telef"],
        "nombreEmpresa": form["nombreEmpresa"],
        "tipoProductos": form["tipoProductos"],
        "pedidos": form["pedidos"],
        "productosDisponibles": form["productosDisponibles"],
    }
    # Realizar la solicitud POST a la API
    r = requests.post("http://localhost:8099/myapp/provetor/update", data=json.dumps(dataF), headers=headers)
    dat = r.json()
    if r.status_code == 200:
        flash("Se ha actualizado correctamente", category='info')
        return redirect("/proveedores/proveedor/list")
    else:
        flash(str(dat["data"]), category='error')
    return redirect("/proveedores/proveedor/list")

############################################################################################################
#Metodo de buscar y ordenar

@routeProveedor.route('/proveedor/search/<criterio>/<texto>')
def view_buscar_proveedor(criterio, texto):
    url = "http://localhost:8099/myapp/provetor/list/search/"
    if criterio == "apellido":
        r = requests.get(url + texto)
    elif criterio == "telefono":
        r = requests.get(url + "telefono/" + texto)
        
    elif criterio == "cedula":
        r = requests.get(url + "cedula/" + texto)
    else:
        return "Criterio no válido", 400

    data1 = r.json()
    if r.status_code == 200:
        if type(data1["data"]) is dict:
            list = []
            list.append(data1["data"])
            return render_template('proveedor/lista.html', lista=list)
        else:
            return render_template('proveedor/lista.html', lista = data1["data"])
    else:
        return render_template('proveedor/lista.html', lista=[], message="No existe el elemento")


    
@routeProveedor.route('/proveedor/order/<attributo>/<tipo>')
def view_order_proveedor(attributo, tipo):
        url= "http://localhost:8099/myapp/provetor/list/order/"+attributo+"/"+tipo    
        r= requests.get(url)

        data1 = r.json()
        if r.status_code == 200:
            return render_template('proveedor/lista.html', lista = data1["data"])
        else:
            return render_template('proveedor/lista.html', lista = [], message = "No existe el elemento")


###DELETE
@routeProveedor.route('/proveedor/eliminar/<int:id>', methods=["DELETE"])
def delete_supplier(id):
    try:
        r = requests.delete(f"http://localhost:8099/myapp/provetor/delete/{id}")
        if r.status_code == 200:
            return {"message": "Proveedor eliminado", "id": id}, 200
        else:
            return {"error": "No se pudo eliminar el proveedor"}, 400
    except Exception as e:
        return {"error": str(e)}, 500


