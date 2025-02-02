
from flask import Blueprint

routeProducto = Blueprint('routeProducto', __name__)

@routeProducto.route('/producto')
def producto():
    return 'Producto'