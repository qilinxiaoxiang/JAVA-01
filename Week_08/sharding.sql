create database trade0;
use trade0;
create table if not exists `trade0`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade1`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade2`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade3`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade4`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade5`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade6`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade7`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade8`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade9`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade10`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';


create table if not exists `trade11`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade12`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade13`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade14`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade15`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';


create database trade1;
use trade1;
create table if not exists `trade0`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade1`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade2`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade3`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade4`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade5`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade6`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade7`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade8`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade9`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade10`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';


create table if not exists `trade11`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade12`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade13`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade14`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade15`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';

create table if not exists `trade`(
    `id` int auto_increment comment '主键',
    `trade_id` bigint not null comment '订单唯一id',
    `uid` bigint not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';