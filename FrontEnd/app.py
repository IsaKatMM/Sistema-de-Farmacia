from flask import Flask
from routes.route import router
from routes.routeProducto import routeProducto
from routes.routeVenta import routeVenta
from routes.routeProveedor import routeProveedor
from routes.routeCompra import routeCompra

def create_app():
    app = Flask(__name__, instance_relative_config=False)
    app.secret_key = 'hola'

    # Registra los blueprints
    # app.register_blueprint(router)
    # app.register_blueprint(routeProducto)
    # app.register_blueprint(routeVenta)
    # app.register_blueprint(routeProveedor)
    # app.register_blueprint(routeCompra)
    
    app.register_blueprint(router, url_prefix='/')
    app.register_blueprint(routeProducto, url_prefix='/productos')
    app.register_blueprint(routeVenta, url_prefix='/ventas')
    app.register_blueprint(routeProveedor, url_prefix='/proveedores')
    app.register_blueprint(routeCompra, url_prefix='/compras')

    return app
