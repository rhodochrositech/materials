from flask import app
import app
import pymysql

def get_connection():
    return pymysql.connect(host=app.config['DB_HOST'],
                           user=app.config['DB_USER'],
                           password=app.config['DB_PASS'],
                           database=app.config['DB_DATABASE'],
                           cursorclass=pymysql.cursors.DictCursor)

def get_dinos():
    sql = "select * from dinosaur order by name"
    conn = get_connection()
    with conn:
        with conn.cursor() as cursor:
            cursor.execute(sql)
            return cursor.fetchall()

def insert_dino(slug, name, desc, image, img_cred, s_url, s_credit):
    sql = "insert into dinosaur (slug, name, description, image, image_credit, source_url, source_credit) values (%s, %s, %s, %s, %s, %s, %s)"
    conn = get_connection()
    with conn:
        with conn.cursor() as cursor:
            cursor.execute(sql, (slug, name, desc, image, img_cred, s_url, s_credit))
        conn.commit()
def prime_attendant():
    sql = 'insert into '

def get_dino(dino_id):
    sql = "select * from dinosaur where id = %s"
    conn = get_connection()
    with conn:
        with conn.cursor() as cursor:
            cursor.execute(sql, (dino_id))
            return cursor.fetchone()

def add_attendee(e_id,name,email,comments):
    sql = 'insert into attendees (e_id,name,email,comments) values (%s,%s,%s,%s)'
    conn = get_connection()
    with conn:
        with conn.cursor() as cursor:
            cursor.execute(sql,(e_id,name,email,comments))
        conn.commit()