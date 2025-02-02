from flask import Blueprint, abort, jsonify, request, render_template, redirect,url_for, flash
import requests

routeCompra = Blueprint('routeCompra', __name__)

@routeCompra.route('/compraList')
def compra_list():
    r = requests.get('http://localhost:8099/myapp/compra/list')
    data =  r.json()
    return render_template('compra/compraList.html', lista = data["data"])

@routeCompra.route('/saveCompra', methods=['GET', 'POST'])
def save_compra():
    if request.method == 'POST':
        compra_data = {
            "producto": request.form['producto'],
            "cantidad": request.form['cantidad'],
            "totalCompra": request.form['totalCompra'],
            "id_proveedor": request.form['id_proveedor'],
        }
        
        response = requests.post('http://localhost:8099/myapp/compra/save', json=compra_data)
        if response.status_code == 200:
            return redirect(url_for('routeCompra.compra_list'))  
        else:
            return render_template('saveCompra.html', error=response.json().get("data", "Error desconocido"))
        
    r = requests.get('http://localhost:8099/myapp/provetor/list')
    data =  r.json()
    
    return render_template('compra/saveCompra.html', lista = data["data"])

@routeCompra.route('/updateCompra/<int:compra_id>', methods=['GET', 'POST'])
def update_compra(compra_id):
    if request.method == 'POST':
        compra_data = {
            "id": compra_id,
            "producto": request.form['producto'],
            "cantidad": request.form['cantidad'],
            "totalCompra": request.form['totalCompra'],
            "id_proveedor": request.form['id_proveedor'],
        }
        
        response = requests.post('http://localhost:8099/myapp/compra/update', json=compra_data)
        if response.status_code == 200:
            flash("Compra actualizado correctamente", "success")
            return redirect(url_for('routeCompra.compra_list'))  
        else:
            flash(response.json().get("data", "Error al actualizar la compra"), "danger")
    
    response = requests.get(f'http://localhost:8099/myapp/compra/get/{compra_id}')
    if response.status_code == 200:
        compra = response.json().get("data", {})
    else:
        flash("No se pudo encontrar la compra", "danger")
        return redirect(url_for('routeCompra.compra_list'))
    

    r = requests.get('http://localhost:8099/myapp/provetor/list')
    data =  r.json()
    return render_template('compra/updateCompra.html', compra=compra, lista = data["data"])


@routeCompra.route('/deleteCompra/<int:id>', methods=['POST'])
def delete_compra(id):
    
    response = requests.post(f'http://localhost:8099/myapp/compra/delete/{id}')
    
    if response.status_code == 200:
        flash('Compra eliminada correctamente', 'success')
        return redirect(url_for('router.home'))  
    else:
        flash(response.json().get("info", "Error al eliminar la compra"), 'danger')
        return redirect(url_for('router.home'))  