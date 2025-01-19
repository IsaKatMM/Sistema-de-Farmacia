from flask import Blueprint, abort, jsonify, request, render_template, redirect,url_for, flash
import requests
import json
router = Blueprint('router', __name__)

@router.route('/login')
def index():
    return render_template('index.html')




@router.route('/proveedor/list')
def list_provetor():
    r= requests.get("http://localhost:8099/myapp/provetor/list")
    #print(type(r.json()))
    #print(r.json())
    data= r.json()
    return render_template ('proveedor/lista.html', lista= data ["data"])


@router.route('/proveedor/register')
def view_register_proveedor():
    r= requests.get("http://localhost:8099/myapp/provetor/list")
    data = r.json()
    print(r.json())
    return render_template ('proveedor/registro.html', lista= data ["data"])

# @router.route('/proveedor/save', methods=["POST"])
# def save_person():
#     headers = {'Content-type': 'application/json'}
#     form = request.form
#     dataF = {
#         "nombre": form["nom"],
#         "apellido": form["ape"],
#         "telefono": form["telef"],
#         "nombreEmpresa": form["nombreEmpresa"],  
#     }
    
    
#     # Realizar la solicitud POST a la API
#     r = requests.post("http://localhost:8099/myapp/provetor/save", data=json.dumps(dataF), headers=headers)
#     print(r.status_code)
#     print(r.json()) 
#     dat = r.json()
#     # Manejo de la respuesta
#     if r.status_code == 200:
#         flash("Se ha guardado correctamente", category='info')
#         return redirect("/proveedor/list")
#     else:
#         flash(str(dat["data"]), category='error')
#     return redirect("/proveedor/list")


#"tipoProducto": form["tipoProducto"],
     #   "pedidos": form["pedidos"],
      #  "productosDisponibes": form["productosDisponibes"],

@router.route('/proveedor/save', methods=["POST"])
def save_person():
    headers = {'Content-type': 'application/json'}
    form = request.form
    
    # Crear el diccionario con los campos obligatorios
    dataF = {
        "nombre": form["nom"],
        "apellido": form["ape"],
        "telefono": form["telef"],
        "nombreEmpresa": form["nombreEmpresa"],
    }
    
    # Agregar campos opcionales si están presentes en el formulario
    if "tipoProducto" in form:
        dataF["tipoProducto"] = form["tipoProducto"]
    
    if "pedidos" in form:
        dataF["pedidos"] = form["pedidos"]
    
    if "productosDisponibles" in form:
        dataF["productosDisponibles"] = form["productosDisponibles"]
    
    # Realizar la solicitud POST a la API
    try:
        r = requests.post("http://localhost:8099/myapp/provetor/save", data=json.dumps(dataF), headers=headers)
        dat = r.json()
        
        # Manejo de la respuesta
        if r.status_code == 200:
            flash("Se ha guardado correctamente", category='info')
            return redirect("/proveedor/list")
        else:
            flash(str(dat["data"]), category='error')
    except requests.exceptions.RequestException as e:
        flash(f"Error al hacer la solicitud: {str(e)}", category='error')
    
    return redirect("/proveedor/list")
