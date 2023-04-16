create database dbms_assignment;
use dbms_assignment;
set dateformat dmy;

create table role(
	id bigint primary key IDENTITY(1,1),
	name nvarchar(255) not null
);

create table users(
	id bigint primary key IDENTITY(1,1),
	first_name nvarchar(255) not null,
	last_name nvarchar(255) not null,
	username nvarchar(255) not null unique,
	password nvarchar(255) not null,
	status nvarchar(255) not null,
	role_id bigint not null
);

create table category(
	id bigint primary key IDENTITY(1,1),
	name nvarchar(255) not null
);

create table product(
	id bigint primary key IDENTITY(1,1),
	name nvarchar(255) not null,
	price real not null,
	image text not null,
	quantity bigint not null,
	category_id bigint not null
);

alter table users
add constraint fk_users_role foreign key(role_id) references role(id);

alter table product
add constraint fk_product_category foreign key(category_id) references category(id);

insert into role(name)
values ('Admin'), ('Customer');

insert into users(first_name, last_name, password, status, username, role_id)
values
(N'An', N'Nguyễn Trường', '5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5',
'Active', N'ntan', '1'),
(N'An', N'Nguyễn Thiên', '5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5',
'Active', N'thienan', '2'),
(N'Anh', N'Nguyễn Thế', '5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5',
'Active', N'thean', '2'),
(N'Bảo', N'Nguyễn Thái', '5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5',
'Active', N'thaibao', '2'),
(N'Bách', N'Nguyễn Hoàng', '5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5',
'Active', N'hoangbach', '2'),

(N'Công', N'Nguyễn Thành', '5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5',
'Active', N'thanhcong', '1'),
(N'Cường', N'Nguyễn Khắc', '5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5',
'Active', N'khaccuong', '2'),
(N'Đạt', N'Nguyễn Tiến', '5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5',
'Active', N'tiendat', '2'),
(N'Hùng', N'Nguyễn Mạnh', '5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5',
'Locked', N'manhhung', '2'),
(N'Hoàng', N'Nguyễn Huy', '5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5',
'Locked', N'huyhoang', '2'),

(N'Kiên', N'Nguyễn Trung', '5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5',
'Active', N'trungkien', '2'),
(N'Khải', N'Nguyễn Hoàng', '5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5',
'Active', N'hoangkhai', '2'),

(N'Nam', N'Nguyễn Bảo', '5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5',
'Active', N'baonam', '2'),
(N'Ngọc', N'Nguyễn Hoàng', '5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5',
'Active', N'hoangngoc', '2'),

(N'Nghĩa', N'Nguyễn Đức', '5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5',
'Active', N'ducnghia', '2'),
(N'Phong', N'Nguyễn Đình', '5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5',
'Active', N'dinhphong', '2');

insert into category(name)
values(N'Tất cả'), (N'Sách văn học'), (N'Sách kinh tế'), (N'Sách kiến thức tổng hợp'),
	(N'Sách giáo khoa - giáo trình');

insert into product(name, price, image, category_id, quantity)
values(N'Cây cam ngọt của tôi', '108000',
	'https://salt.tikicdn.com/cache/750x750/ts/product/5e/18/24/2a6154ba08df6ce6161c13f4303fa19e.jpg.webp',
	'2', 500),
	(N'Thần thoại hy lạp', '191000',
	'https://salt.tikicdn.com/cache/280x280/ts/product/e3/cf/5a/20dc3660d781c125bdfa88699fc2ef14.jpg.webp',
	'2', 600),
	(N'Khởi nghiệp bán lẻ', '120000',
	'https://salt.tikicdn.com/cache/280x280/ts/product/87/89/7d/5bf87088791bcd0d58e3400636f472c4.png.webp',
	'3', 700),
	(N'Từ tốt đến vĩ đại', '89000',
	'https://salt.tikicdn.com/cache/280x280/ts/product/84/f5/a2/8cd3d96f65304037a1f038c982e718e9.jpg.webp',
	'3', 800),
	(N'Tâm lý học tội phạm', '198500',
	'https://salt.tikicdn.com/cache/280x280/ts/product/e1/e9/00/c26d84377d6cae13f244d63e6a8ce59c.jpg.webp',
	'4', 900),
	(N'Binh pháp tôn tử', '128000',
	'https://salt.tikicdn.com/cache/280x280/ts/product/a9/83/7c/6664136e604227071213793e7a2091d9.jpg.webp',
	'4', 1000),
	(N'Đại số tuyến tính', '94500',
	'https://salt.tikicdn.com/cache/280x280/ts/product/53/0b/0e/222c54554897fa471f8a178e85862f6b.jpg.webp',
	'5', 2000),
	(N'Xác suất thống kê', '83700',
	'https://salt.tikicdn.com/cache/280x280/ts/product/10/c9/8c/aff4e7f3ddc44caa680f9b832a9d1b4b.jpg.webp',
	'5', 3000);