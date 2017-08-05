create table asset_purchased (
    id varchar(255) not null auto_increment,
    asset_name varchar(255),
    asset_type integer,
    date_created datetime,
    down_payment double precision,
    ewi integer,
    no_of_emw integer,
    purchase_amount double precision,
    partner_id varchar(255),
    primary key (id)
);