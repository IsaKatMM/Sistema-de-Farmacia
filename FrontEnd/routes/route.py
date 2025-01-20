from flask import Blueprint, abort, jsonify, request, render_template, redirect,url_for, flash
import requests
import json

from flask import Blueprint

router = Blueprint('router', __name__)


@router.route('/login')
def index():
    return render_template('index.html')


@router.route('/')
def home():
    return render_template('home.html')