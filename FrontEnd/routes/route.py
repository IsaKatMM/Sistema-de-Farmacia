from flask import Blueprint, abort, jsonify, request, render_template, redirect,url_for, flash
import json
import requests
router = Blueprint('router', __name__)


@router.route('/proveedor/save', methods=["POST"])
def save_person():
    headers = {'Content-type': 'application/json'}
    form = request.form
    
    # Crear el objeto de datos para enviar al API
    dataF = {
        "cantidad": int(form["cantidad"]),
        "fechaEntrega": form["fechaEntrega"],  # Formato ISO-8601 esperado
        "precioLote": float(form["precioLote"]),
        "fechaCaducidad": form["fechaCaducidad"],  # Formato ISO-8601 esperado
        "precioVenta": float(form["precioVenta"]),
        "precioCompra": float(form["precioCompra"]),
        "codigoLote": form["codigoLote"],
    }

    try:
        # Realizar la solicitud POST al API Java
        r = requests.post("http://localhost:8099/myapp/lote/save", data=json.dumps(dataF), headers=headers)
        response_data = r.json()

        if r.status_code == 200:
            flash(response_data.get("data", "Se ha guardado correctamente"), category='info')
            return redirect("/proveedor/list")
        else:
            flash(response_data.get("data", "Ocurrió un error al guardar"), category='error')
    except requests.RequestException as e:
        flash(f"Error al comunicarse con el servidor: {str(e)}", category='error')
    except Exception as e:
        flash(f"Error inesperado: {str(e)}", category='error')

    return redirect("/proveedor/list")
@router.route('/')
def home():
    return render_template('home.html')
