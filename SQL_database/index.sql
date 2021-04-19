create index order_index
    on "order" (create_date);

create index passing_station_index
    on passing_station (travel_id, arrive_time);

create index seat_remain_index
    on seat_remain (travel_id, price);

create index station_index
    on station (station_id);

create index travel_id_index
    on travel (travel_id);