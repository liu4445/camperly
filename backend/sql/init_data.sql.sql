use trip;

insert into OPERATION_TYPE (NAME)
values ('지자체'), ('국립공원'), ('자연휴양림'), ('국민여가'), ('민간');

insert into LOCATION_TYPE (NAME)
values ('해변'), ('섬'), ('산'), ('숲'), ('계곡'), ('강'), ('호수'), ('도심');

insert into MAIN_FACILITIES (NAME)
values ('일반야영장'), ('자동차야영장'), ('카라반'), ('글램핑');

insert into THEME (NAME)
values ('일출명소'), ('일몰명소'), ('수상레저'), ('항공레저'), ('스키'), ('낚시'), ('액티비티'), ('봄꽃여행'), ('여름물놀이'), ('가을단풍명소'), ('겨울눈꽃명소'), ('걷기길');

insert into SUB_FACILITIES (NAME)
values ('전기'), ('무선인터넷'), ('장작판매'), ('온수'), ('트렘폴린'), ('물놀이장'), ('놀이터'), ('산책로'), ('운동장'), ('운동시설'), ('마트.편의점');