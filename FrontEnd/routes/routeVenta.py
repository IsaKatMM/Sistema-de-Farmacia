from flask import Blueprint, render_template, request, redirect, url_for, flash

routeVenta = Blueprint('routeVenta', __name__)

@routeVenta.route('/venta')
def venta():
    return render_template('venta/venta.html')