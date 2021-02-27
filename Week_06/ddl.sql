create table if not exists `user`(
    `id` int auto_increment comment '主键',
    `login_name` varchar(50) not null comment '用户登录名',
    `phone` varchar(20) not null comment '手机号',
    `uid` varchar(20) not null comment '用户唯一id',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`uid`),
	key `idx_phone` (`phone`)
) default charset=utf8mb4 comment '用户表';


create table if not exists `trade`(
    `id` int auto_increment comment '主键',
    `trade_id` varchar(30) not null comment '订单唯一id',
    `uid` varchar(20) not null comment '用户唯一id',
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

create table if not exists `goods`(
    `id` int auto_increment comment '主键',
    `goods_id` varchar(20) not null comment '商品唯一id',
    `name` varchar(20) not null comment '商品名称',
    `picture_url` varchar(50) not null comment '商品图片url',
    `price` decimal(10,2) not null comment '商品售价',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_goods_id` (`goods_id`),
	key `idx_modify_time` (`modify_time`)
) default charset=utf8mb4 comment '商品表';