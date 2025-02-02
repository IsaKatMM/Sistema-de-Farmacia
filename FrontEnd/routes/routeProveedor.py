from flask import Blueprint, render_template, request, redirect, url_for, flash

routeProveedor = Blueprint('routeProveedor', __name__)

@routeProveedor.route('/proveedor')
def proveedor():
    return render_template('proveedor/proveedor.html')