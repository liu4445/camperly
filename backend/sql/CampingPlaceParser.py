import requests
import pymysql

params = {
    'MobileOS': 'ETC',
    'MobileApp': 'ssafy',
    'serviceKey': 'kUlJQVcNEi7URCmfIj7Q6RA29j8la4QmbCuMjvgOLuSmpPDM+53iCXVycVcvvPDpkj34w47VdQfep2Gca+rEEQ==',
    '_type': 'json',
    'numOfRows': '3321',
    'pageNo': '1'
}
response = requests.get('https://apis.data.go.kr/B551011/GoCamping/basedList', params=params)

arr = response.json()['response']['body']['items']['item']
connect = pymysql.connect(host='localhost', user='root', password='', db='trip', charset='utf8')
cursor = connect.cursor()
for i in range(len(arr)):
    each = arr[i]

    sql = 'insert into trip.CAMPING_PLACE values(' + '%s,' * 45 + '%s' + ')'
    each['zipcode'] = each['zipcode'].replace('-', '')

    # 캠핑장 넣기
    cursor.execute(
        sql,(
        each['contentId'],
        each['doNm'],
        each['sigunguNm'],
        each['wtrplCo'],
        each['brazierCl'],
        each['caravAcmpnyAt'],
        each['toiletCo'],
        each['swrmCo'],
        each['featureNm'],
        each['induty'],
        each['zipcode'],
        each['addr1'],
        each['mapX'],
        each['mapY'],
        each['tel'],
        each['homepage'],
        each['resveUrl'],
        each['resveCl'],
        each['manageNmpr'],
        each['operPdCl'],
        each['operDeCl'],
        each['trlerAcmpnyAt'],
        each['intro'],
        each['allar'],
        each['manageSttus'],
        each['eqpmnLendCl'],
        each['animalCmgCl'],
        each['firstImageUrl'],
        each['facltNm'],
        each['lineIntro'],
        each['facltDivNm'],
        each['lctCl'],
        each['siteBottomCl1'],
        each['siteBottomCl2'],
        each['siteBottomCl3'],
        each['siteBottomCl4'],
        each['siteBottomCl5'],
        each['gnrlSiteCo'],
        each['autoSiteCo'],
        each['glampSiteCo'],
        each['caravSiteCo'],
        each['indvdlCaravSiteCo'],
        each['sbrsEtc'],
        each['caravInnerFclty'],
        each['glampInnerFclty'],
        each['exprnProgrm']
        )
    )
    cursor = connect.cursor()
    campingPlaceId = each['contentId']

    # 부대 시설 정보 넣기
    if each['sbrsCl']:
        subFacilities = each['sbrsCl'].split(',')
        for facilities in subFacilities:
            if facilities == '':
                continue
            cursor.execute('select sub_facilities_id from trip.SUB_FACILITIES where name = %s', facilities)
            subFacilitiesId = cursor.fetchall()[0]
            cursor.execute('insert into trip.CAMPING_PLACE_SUB_FACILITIES(CONTENT_ID, SUB_FACILITIES_ID) values(%s, %s)', (campingPlaceId, subFacilitiesId))

    # 운영 형태 정보 넣기
    if each['facltDivNm']:
        subFacilities = each['facltDivNm'].split(',')
        for facilities in subFacilities:
            if facilities == '':
                continue
            cursor.execute('select OPERATION_TYPE_ID from trip.OPERATION_TYPE where name = %s', facilities)
            subFacilitiesId = cursor.fetchall()[0]
            cursor.execute(
                'insert into trip.CAMPING_PLACE_OPERATION_TYPE(CONTENT_ID, OPERATION_TYPE_ID) values(%s, %s)',
                (campingPlaceId, subFacilitiesId))

    # 입지 구분 정보 넣기
    if each['lctCl']:
        subFacilities = each['lctCl'].split(',')
        for facilities in subFacilities:
            if facilities == '':
                continue
            cursor.execute('select LOCATION_TYPE_ID from trip.LOCATION_TYPE where name = %s', facilities)
            subFacilitiesId = cursor.fetchall()[0]
            cursor.execute(
                'insert into trip.CAMPING_PLACE_LOCATION_TYPE(CONTENT_ID, LOCATION_TYPE_ID) values(%s, %s)',
                (campingPlaceId, subFacilitiesId))

    # 캠핑장 테마 정보 넣기
    if each['themaEnvrnCl']:
        subFacilities = each['themaEnvrnCl'].split(',')
        for facilities in subFacilities:
            if facilities == '':
                continue
            cursor.execute('select THEME_ID from trip.THEME where name = %s', facilities)
            subFacilitiesId = cursor.fetchall()[0]
            cursor.execute(
                'insert into trip.CAMPING_PLACE_THEME(CONTENT_ID, THEME_ID) values(%s, %s)',
                (campingPlaceId, subFacilitiesId))

    main = set()
    if each['gnrlSiteCo'] != '0':
        main.add(('gnrlSiteCo', '일반야영장'))
    if each['autoSiteCo'] != '0':
        main.add(('autoSiteCo', '자동차야영장'))
    if each['glampSiteCo'] != '0':
        main.add(('glampSiteCo', '글램핑'))
    if each['caravSiteCo'] != '0':
        main.add(('caravSiteCo', '카라반'))

    for facilities in main:
        cursor.execute('select MAIN_FACILITIES_ID from trip.MAIN_FACILITIES where name = %s', facilities[1])
        subFacilitiesId = cursor.fetchall()[0]
        cursor.execute(
            'insert into trip.CAMPING_PLACE_MAIN_FACILITIES(CONTENT_ID, MAIN_FACILITIES_ID) values(%s, %s)',
            (campingPlaceId, subFacilitiesId))

connect.commit()