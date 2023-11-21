import json
import pymysql

with open('data.json') as file:
    data = json.load(file)
    for city in data:
        connect = pymysql.connect(host='localhost', user='root', password='', db='trip', charset='utf8')
        cursor = connect.cursor()
        for month in data[city]:
            cursor.execute('insert into trip.weather(city_name, weather_month, avg_temperature, min_temperature, max_temperature) '
                           'values(%s, %s, %s, %s, %s)',
                           (
                               city,
                               int(month),
                               float(data[city][month]['temperature']),
                               float(data[city][month]['maxTemperature']),
                               float(data[city][month]['minTemperature']))
                           )
        connect.commit()
