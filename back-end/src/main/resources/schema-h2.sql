create table auctions
(
    id              bigint not null auto_increment,
    is_cancel       bit,
    price           bigint,
    participants_id bigint,
    products_id     bigint,
    created_at      datetime,
    updated_at      datetime,
    primary key (id)
);

create table categories
(
    id         bigint not null auto_increment,
    created_at datetime,
    updated_at datetime,
    title      varchar(255),
    parent     bigint,
    primary key (id)
);

create table categories_products
(
    categories_id bigint not null,
    products_id   bigint not null
);

create table comments
(
    id          bigint not null auto_increment,
    created_at  datetime,
    updated_at  datetime,
    content     varchar(255),
    products_id bigint,
    writer_id   bigint,
    primary key (id)
);

create table files
(
    id         bigint not null auto_increment,
    created_at datetime,
    updated_at datetime,
    filename   varchar(255),
    path       varchar(255),
    products   bigint,
    primary key (id)
);

create table likes
(
    id         bigint not null auto_increment,
    created_at datetime,
    updated_at datetime,
    products   bigint,
    users      bigint,
    primary key (id)
);

create table messages
(
    id         bigint not null,
    created_at datetime,
    updated_at datetime,
    primary key (id)
);

create table products
(
    id              bigint not null auto_increment,
    content         varchar(255),
    end_date_time   datetime,
    start_date_time datetime,
    start_price     bigint,
    state           varchar(255),
    title           varchar(255),
    view_count      integer default 0,
    category_id   bigint,
    seller_id       bigint,
    created_at      datetime,
    updated_at      datetime,
    primary key (id)
);

create table tag
(
    id    bigint not null auto_increment,
    title varchar(255),
    primary key (id)
);
create table users
(
    id         bigint not null auto_increment,
    created_at datetime,
    updated_at datetime,
    email      varchar(255),
    kakao      varchar(255),
    name       varchar(255),
    naver      varchar(255),
    role       varchar(255),
    primary key (id)
);

alter table categories_products
    add constraint UK_rnlo43ssc3pd408u62he9udsb unique (products_id);
create
index i_likes on likes (users, products);
alter table likes
    add constraint UKf48y2u7vlh997cnlw0ryi4snf unique (products, users);
alter table users
    add constraint UKob8kqyqqgmefl0aco34akdtpe unique (email);
alter table auctions
    add constraint FK38r6eec52286v4d5khiuskxes foreign key (participants_id) references users (id);
alter table auctions
    add constraint FKjjwwa7ibcnwlov76mhniryqvr foreign key (products_id) references products (id);
alter table categories
    add constraint FKqxcgflvs2beh702toak6bni11 foreign key (parent) references categories (id);
alter table categories_products
    add constraint FKad19ea8ca86lh5f3wmgg83vmj foreign key (products_id) references products (id);
alter table categories_products
    add constraint FK86b8j2u9mcr7a46vgtxyo8xwx foreign key (categories_id) references categories (id);
alter table comments
    add constraint FK2of5vociu8y26rnp39s9kf27u foreign key (products_id) references products (id);
alter table comments
    add constraint FKrgw4imjcym2gmg1c4m67kfp8n foreign key (writer_id) references users (id);
alter table files
    add constraint FKqkivfc7eev9vobrp6ka3epu9c foreign key (products) references products (id);
alter table likes
    add constraint FKkx9597ge3gbusg88w1q87o7nl foreign key (products) references products (id);
alter table likes
    add constraint FKquybhgckk509oyupcjhpv9ut9 foreign key (users) references users (id);
alter table products
    add constraint FKgro094vh0dp0tly1225wk8u37 foreign key (category_id) references categories (id);
alter table products
    add constraint FKojbrber3i963iisre77lu4hub foreign key (seller_id) references users (id);
