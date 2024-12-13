from flask import Blueprint, abort, jsonify, request, render_template, redirect,url_for, flash
import requests
router = Blueprint('router', __name__)

@router.route('/')
def index():
    return render_template('home.html')


@router.route('/person/list')
def list_person():
    #r= requests.get("http://localhost:8099/myapp/person/list")
    #data= r.json()
    return render_template ('Persona/lista.html')