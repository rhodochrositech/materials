from flask import Flask, render_template, request, url_for, redirect, send_file
import random, os
app = Flask(__name__)

homedir = os.getcwd()

def getplane():
	planedir = r'..\I211_flask\static\planes'
	planelist = os.listdir(planedir)
	#print(planelist)
	plane = random.choice(planelist)
	plane = 'static\\planes\\'+plane
	print(plane)
	return plane
greetings = ['how is mot?','where da lamp?','lay egg']
@app.route('/')
def index():
	return render_template('index.html')
def hello_world():
	return 'hi'
@app.route('/add-moth.html', methods = ['GET', 'POST'])
def add_moth():
	print('FIRST FLAG')
	if request.method == 'POST':
		slug = request.form['slug']
		name = request.form['name']
		description = request.form['description']
		cute = request.form['cute']
		emailer = request.form['emailer']
		print(slug,name,description,cute,emailer)
		return redirect(url_for('index'))
	else:
		return render_template('add-moth.html')
@app.route('/moth-quiz', methods = ['GET','POST'])
def moth_quiz():
	if request.method == 'POST':
		
		return redirect(url_for('index'))
	else:
		return render_template('moth-quiz.html')

@app.route('/planechase', methods = ['GET', 'POST'])
def planechase():
	image = getplane()
	if request.method == 'POST':
		return render_template('planechase.html',plane=image)
	else:
		return render_template('planechase.html',plane=image)
	
@app.route('/busapp', methods = ['GET','POST'])
def busapp():
	busrouteimages = {'Route X':'\\static\\busroutes\\Route X.png', 'Route A':'\\static\\busroutes\\Route A.png', 'Route W':'\\static\\busroutes\\Route W.png'}
	routesave = '\\static\\busroutes\\Route A.png'
	if request.method == 'POST':
		busroute = request.form['busroute']
		routesave = request.form['busroute']
		try:
			sel = busrouteimages[busroute]
		except:
			sel = busrouteimages['Route X']
		
		return render_template('busapp.html', routesave = routesave, busrouteimage = sel, dict = busrouteimages)
	else:
		return render_template('busapp.html', routesave = routesave, busrouteimage = busrouteimages['Route A'], dict = busrouteimages)