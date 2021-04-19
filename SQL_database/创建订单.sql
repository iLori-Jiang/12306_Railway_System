create function create_order(id character varying, travelid integer, depart_id integer, arrive_id integer, seat character varying) returns character varying
as
$$
declare
    orderid int;
    seat_remain int;
    price numeric(10,2);
begin
    seat_remain = (select seatremain from ticket_remain(travelid, seat, depart_id, arrive_id));
    price = (select totalprice from ticket_remain(travelid, seat, depart_id, arrive_id));

    if seat_remain >= 1 then
        insert into public.order(id_card, create_date, order_status) VALUES (id, current_timestamp, 'N');
        orderid = (select max(order_id) from public.order);
        insert into public.ticket(order_id, travel_id, depart_station_id, arrive_station_id, seat_type, ticket_price)
        VALUES (orderid, travelid, depart_id, arrive_id, seat, price);
        update public.order set order_status = 'Y' where order_id = orderid;
        return 'true';
    else return 'false';
    end if;
end;
$$;


