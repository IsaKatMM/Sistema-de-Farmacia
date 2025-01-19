from flask import Flask, request, jsonify

def create_app():
    app = Flask(__name__, instance_relative_config=False)
    app.secret_key = 'hola'
    with app.app_context():
        from routes.route import router
        from routes.routeProducto import routeProducto
        from routes.routeVenta import routeVenta
        from routes.routeProveedor import routeProveedor
        from routes.routeCompra import routeCompra
        app.register_blueprint(router)
        app.register_blueprint(routeProducto)
        app.register_blueprint(routeVenta)
        app.register_blueprint(routeProveedor)
        app.register_blueprint(routeCompra)


    return app