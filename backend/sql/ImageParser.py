import requests
import pymysql
import json

connect = pymysql.connect(host='localhost', user='root', password='', db='trip', charset='utf8')
selectCursor = connect.cursor()
selectCursor.execute('select CONTENT_ID from trip.temp')
fetchall = selectCursor.fetchall()

params = {
    'MobileOS': 'ETC',
    'MobileApp': 'ssafy',
    'serviceKey': 'kUlJQVcNEi7URCmfIj7Q6RA29j8la4QmbCuMjvgOLuSmpPDM+53iCXVycVcvvPDpkj34w47VdQfep2Gca+rEEQ==',
    '_type': 'json',
    'numOfRows': '10000',
    'contentId': ''
}
cnt = 0
fetchall = sorted(fetchall, key=lambda x: x[0])
for fetch in fetchall:
    content_id = fetch[0]

    params['contentId'] = content_id
    response = requests.get('https://apis.data.go.kr/B551011/GoCamping/imageList', params=params)
    # print(response.json())
    connect = pymysql.connect(host='localhost', user='root', password='', db='trip', charset='utf8')
    cursor = connect.cursor()
    try:
        arr = json.loads(response.text)
        arr = arr['response']['body']['items']
        if not arr:
            cursor.execute('delete from trip.temp where CONTENT_ID = %s', content_id)
            connect.commit()
            print(content_id)
            continue
        arr = arr['item']
        if not arr:
            cursor.execute('delete from trip.temp where CONTENT_ID = %s', content_id)
            connect.commit()
            print(content_id)
            continue
    except Exception as e:
        print(content_id, response.text)
        print(e)
        exit()
    for i in range(len(arr)):
        each = arr[i]

        contentId = each['contentId']
        imageUrl = each['imageUrl']

        cursor.execute('insert into trip.CAMPING_PLACE_IMG(CONTENT_ID, IMG_URL) value (%s, %s)', (content_id, imageUrl))
    cnt += 1
    print(cnt)
    connect.commit()
    cursor.execute('delete from trip.temp where CONTENT_ID = %s', content_id)
    connect.commit()
    print(content_id)
