delete from reports;
delete from indicators;
delete from link_reports_indicators;
delete from indicator_types;

insert into reports (id, display_name, created_date, reviewed)
    values (1,'badGuys.txt', '2018-11-01', true),
    (2,'Domains.txt', '2019-01-02', false ),
    (3,'AdamsStuff.pdf', '2019-01-02', true),
    (4,'KevinsStuff.txt', '2019-02-04', true),
    (5,'KatsIPs.pdf', '2019-03-13', false);


insert into indicators (id, value, ind_type)
values
    (11, 'badguy.com',1),
    (12, 'google.com',1),
    (13, 'yahoo.com',1),
    (14, '127.0.0.1',2),
    (15, '128.0.0.1',2),
    (16, 'bing.com',1);

insert into indicator_types (id, name)
    values
    (1,'domain'),
    (2,'ip');

insert into link_reports_indicators (id, report, indicator) values
    (21,1,11),
    (22,2,12),
    (23,2,13),
    (24,4,14),
    (25,4,15),
    (26,4,16),
    (27,5,14),
    (28,5,15);