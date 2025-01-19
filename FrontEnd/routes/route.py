from flask import Blueprint, abort, jsonify, request, render_template, redirect,url_for, flash
import requests
router = Blueprint('router', __name__)


@router.route('/')
def home():
    return render_template('home.html')