from flask import Flask, render_template, request, url_for, redirect, send_file
import random, os, csv, pymysql, database,config,config_defaults, html
app = Flask(__name__)
from app import app
app.config.from_pyfile(app.root_path+'/config.py')

# LOGIN 

homedir = os.getcwd() #locate filepath of page
EVENT_KEYS =['slug','event_name','event_date','event_host','description']

EVENTS_PATH = app.root_path + '/events.csv'
def get_depreciated_events(): # returns all information from our csv
    with open(EVENTS_PATH, 'r') as csvfile:
            data = csv.DictReader(csvfile)
            eventstore = {}
            for event in data:
                eventstore[event['slug']] = event
    return eventstore
tempdict = {}
def get_events():
    sql = 'select * from events order by event_date'
    conn = get_conn()
    with conn:
        with conn.cursor() as cursor:
            cursor.execute(sql)
            global tempdict
            tempdict = cursor.fetchall()
    return(tempdict)

def get_attendees(e_id):
    sql = 'select * from attendees where e_id = %s;'
    conn = get_conn()
    with conn:
        with conn.cursor() as cursor:
            cursor.execute(sql,e_id)
            return(cursor.fetchall())
def get_attendee(a_id):
    sql = 'select * from attendees where a_id = %s;'
    conn = get_conn()
    with conn:
        with conn.cursor() as cursor:
            cursor.execute(sql,a_id)
            return(cursor.fetchall())
def del_attendees(e_id):
    sql = 'delete from attendees where e_id = %s;'
    conn = get_conn()
    with conn:
        with conn.cursor() as cursor:
            cursor.execute(sql,e_id)
            return(cursor.fetchall())

def del_attendee(a_id):
    sql = 'delete from attendees where a_id = %s;'
    conn = get_conn()
    with conn:
        with conn.cursor() as cursor:
            cursor.execute(sql,a_id)
            return(cursor.fetchall())


def get_conn():
    return pymysql.connect(
    host=app.config['DB_HOST'],
    user=app.config['DB_USER'],
    password=app.config['DB_PASS'],
    database=app.config['DB_DATABASE'],
    cursorclass=pymysql.cursors.DictCursor)

def set_events(eventstore): #Begins to csv dictionary write into our file to give it new info
    with open(EVENTS_PATH, mode ='w', newline='') as csv_file:
        writer = csv.DictWriter(csv_file, fieldnames=EVENT_KEYS)
        writer.writeheader()
        for event in eventstore.values():
            writer.writerow(event)

@app.route('/')
def index():
	return render_template('index.html') #return simple splash page

tempdict = {}  
@app.route('/events') 
def events():
    return render_template('events.html', events=get_events())
#@app.route('/events')
#def events():
#    return render_template('events.html', events = get_events()) #return events page and populate it with events

@app.route("/events/create", methods=['GET', 'POST'])
def create():
    # if POST request received (form submitted)
   if request.method == 'POST':
       sql = "insert into events (event_name, event_date, event_host, description) values (%s, %s, %s, %s)"
       conn = get_conn()
       with conn:
            with conn.cursor() as cursor:
                cursor.execute(sql, (html.escape(request.form['event_name']), html.escape(request.form['event_date']), html.escape(request.form['event_host']), html.escape(request.form['description'])))
            conn.commit()
       # add new dict to csv data
       # write csv data back out to csv file
       # since POST request, redirect after Submit (we want the display to change 
       return redirect(url_for('events'))
   # if GET request received (display form)
   else:
       return render_template('events_form.html')
tempdict = {}
@app.route('/events/<event_ID>', methods=['GET', 'POST'])
def event(event_ID=None): #through the event ID locate the referenced event
    sql = 'select * from events where e_id = %s'
    conn = get_conn()
    with conn:
        with conn.cursor() as cursor:
            cursor.execute(sql,(event_ID))
            global tempdict
            tempdict = cursor.fetchall()
            return render_template('event.html', events = tempdict[0], attendees = get_attendees(event_ID)) #returns single event page populated by single event
        
@app.route('/events/<event_ID>/edit', methods=['GET', 'POST'])
def edit(event_ID=None):
    if request.method == 'POST': #If the POST or submit button is hit,
        editEvent={}
    # add form data to new dict
        editEvent['event_name'] = html.escape(request.form['event_name'])
        editEvent['event_date'] = html.escape(request.form['event_date'])
        editEvent['event_host'] = html.escape(request.form['event_host'])
        editEvent['description'] = html.escape(request.form['description'])
        sql = "update events set event_name = %s, event_date = %s, event_host = %s, description = %s where e_id = "+str(event_ID)
        conn = get_conn()
        with conn:
            with conn.cursor() as cursor:
                cursor.execute(sql, (editEvent['event_name'], str(editEvent['event_date']), editEvent['event_host'], editEvent['description']))
            conn.commit()
        return redirect(url_for('events'))
    else: #If the page has just been rendered
        events = get_events()
        for event in events:
            if event['e_id'] == int(event_ID):
                return render_template('events_edit.html',events=event)
        else:
            return render_template('events.html')


        



@app.route('/events/<event_ID>/delete/', methods=['GET', 'POST']) #needs form methods get and post
def delete(event_ID=None):
    if request.method == 'POST': #If confirmed to delete, delete)
        events = get_events()
        for event in events:
            if event['e_id'] == int(event_ID):
                delevent = 'delete from events where e_id = %s'
                delattend = 'delete from attendees where e_id = %s'
                conn = get_conn()
                with conn:
                    with conn.cursor() as cursor:
                        cursor.execute(delattend,(event_ID))
                        cursor.execute(delevent,(event_ID))
                    conn.commit()
        return redirect(url_for('events'))
    else: #If the page is just being rendered, render and display the selected event
        for event in get_events():
            if event['e_id'] == int(event_ID):
                return render_template('delete_form.html',events = get_events(), event=event)
        return redirect(url_for('events'))

@app.route('/events/<event_ID>/attendees/add', methods=['GET', 'POST'])
def add_attendee(event_ID = None):
    if request.method == 'POST':
        sql = 'insert into attendees (e_id,name,email,comments) values (%s,%s,%s,%s)'
        conn = get_conn()
        with conn:
            with conn.cursor() as cursor:
                cursor.execute(sql,(event_ID,html.escape(request.form['name']),html.escape(request.form['email']),html.escape(request.form['comment'])))
            conn.commit()
        #database.add_attendee(1,newEvent['name'],newEvent['email'],newEvent['comment'])
        return redirect(url_for('events'))
    else:
        return render_template('attendees_form.html')


@app.route('/events/<event_ID>/attendees/<attendee_ID>/edit', methods=['GET', 'POST'])
def edit_attendee(event_ID = None, attendee_ID = None):
    if request.method == 'POST':
        sql = 'update attendees set name = %s, email = %s, comments = %s where a_id = %s;'
        conn = get_conn()
        with conn:
            with conn.cursor() as cursor:
                cursor.execute(sql,(html.escape(request.form['name']),html.escape(request.form['email']),html.escape(request.form['comment']),attendee_ID))
            conn.commit()
        return redirect(url_for('event', event_ID = event_ID))
        #database.add_attendee(1,newEvent['name'],newEvent['email'],newEvent['comment'])   
    else:
        return render_template('attendees_edit.html', attendee = get_attendee(attendee_ID)[0])

@app.route('/events/<event_ID>/attendees/<attendee_ID>/delete')
def delete_attendee(event_ID = None, attendee_ID = None):
    del_attendee(attendee_ID)
    return redirect(url_for('event',event_ID = event_ID))


tempdict = {}  
@app.route('/adfdsfdsf') 
def sqlconnect():
    sql = 'select * from events'
    conn = get_conn()
    with conn:
        with conn.cursor() as cursor:
            cursor.execute(sql)
            global tempdict
            tempdict = cursor.fetchall()
    print(tempdict)
    return render_template('sqlrender.html', events=tempdict)

@app.route('/sqltest', methods=['GET', 'POST'])
def sqltester():
    if request.method == 'POST':
        sql = 'insert into attendees (e_id,name,email,comments) values (%s,%s,%s,%s)'
        conn = get_conn()
        with conn:
            with conn.cursor() as cursor:
                cursor.execute(sql,(1,html.escape(request.form['name']),html.escape(request.form['email']),html.escape(request.form['comment'])))
            conn.commit()
        #database.add_attendee(1,newEvent['name'],newEvent['email'],newEvent['comment'])
        return render_template('events.html')
    else:
        return render_template('attendees_form.html')


@app.route('/sqltypetest')
def sqlfulltest():
    sql = "select * from events"
    conn = get_conn()
    with conn:
        with conn.cursor() as cursor:
            cursor.execute(sql)
            return cursor.fetchall()
