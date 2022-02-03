create table coin
(
    id bigint not null auto_increment,
    csupply float,
    marker_cap_usd float,
    msupply float,
    name varchar(255),
    name_id varchar(255),
    percent_change_day float,
    percent_change_hour float,
    percent_change_week float,
    price_btc float,
    price_usd float(8,2),
    `rank` bigint,
    symbol varchar(255),
    tsupply float,
    volume float,
    volume_native float,
    primary key (id)
) engine=InnoDB;

create table note
(
    id bigint not null auto_increment,
    coin_id bigint,
    price float(8,2),
    username varchar(255),
    primary key (id)
) engine=InnoDB;