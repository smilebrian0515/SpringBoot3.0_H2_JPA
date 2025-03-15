create table SysUser
(
    id    BIGINT auto_increment PRIMARY KEY,
    name  CHARACTER VARYING(10) not null,
    email CHARACTER VARYING(50) not null
);

comment on table SysUser is '系統使用者';
comment on column SysUser.id is '系統流水號';
comment on column SysUser.name is '名稱';
comment on column SysUser.email is '信箱';



create table Currency
(
    id INT auto_increment primary key ,
    code varchar2(10) not null,
    symbol nvarchar2(10) not null,
    rate nvarchar2(20) not null,
    rate_float decimal(15,6) not null,
    description nvarchar2(50) null,
    createTime datetime not null,
    updateTime datetime not null
);

comment on table Currency is '貨幣/幣別';
comment on column Currency.id is '系統流水號';
comment on column Currency.code is '貨幣代碼';
comment on column Currency.symbol is '識別符號';
comment on column Currency.rate is '匯率';
comment on column Currency.rate_float is '匯率浮點數形式';
comment on column Currency.description is '描述';
comment on column Currency.createTime is '建立時間';
comment on column Currency.updateTime is '異動時間';
