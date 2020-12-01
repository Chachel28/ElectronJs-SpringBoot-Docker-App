/* Creaciรณn de BBDD*/
/*CREATE DATABASE sge_moviles;
USE sge_moviles;*/

/*Creaciรณn de Tablas*/
create table clientes
(
    idCliente      int auto_increment
        primary key,
    nombreCompleto varchar(65) not null,
    dniCif         varchar(9)  not null,
    iban           varchar(24) not null,
    email          varchar(30) not null unique,
    constraint uniqueDniCif
        unique (dniCif),
    constraint ck_dniCif_clientes
        check (`dniCif` REGEXP '[A-Z]{0,}[0-9]{7,}[A-Z]'),
    constraint ck_email_clientes
        check (`email` like '%@%')
);

create table facturas
(
    idFactura    int auto_increment
        primary key,
    fechaFactura timestamp default current_timestamp() not null on update current_timestamp(),
    descuentos   decimal                               not null,
    subtotal     float     default 0                   not null,
    iva          decimal                               not null,
    total        float     default 0                   not null
);

create table productos
(
    idProducto   int auto_increment
        primary key,
    precioVenta  float        not null,
    tipo         varchar(20)   not null,
    nombre       varchar(60)  not null,
    precioCompra float        not null,
    stock        int          not null,
    descripcion  varchar(100) not null,
    constraint ck_precio
        check (`precioCompra` > 0 and `precioVenta` > 0),
    constraint ck_stock
        check (`stock` >= 0),
    constraint ck_tipoProducto check (productos.tipo in ('SIMPLE','COMPUESTO','RECURSO','SERVICIO'))
);

create table proveedores
(
    idProveedor int auto_increment
        primary key,
    dniCif      varchar(9)  not null,
    nombre      varchar(45) not null,
    email       varchar(30) not null unique,
    constraint uniqueDniCifProveedor
        unique (dniCif),
    constraint ck_dniCif_proveedores
        check (`dniCif` REGEXP '[A-Z]{0,}[0-9]{7,}[A-Z]'),
    constraint ck_email_proveedores
        check (`email` like '%@%')
);

create table direcciones_cliente
(
    idDireccionCliente int auto_increment
        primary key,
    idCliente   int         not null,
    direccion   varchar(75) not null,
    constraint fk_direcciones_clientes
        foreign key (idCliente) references clientes (idCliente)
            on delete cascade

);

create table direcciones_proveedor
(
    idDireccionProveedor int auto_increment
        primary key,
    idProveedor int         not null,
    direccion   varchar(75) not null,
    constraint fk_direcciones_proveedor
        foreign key (idProveedor) references proveedores (idProveedor)
            on delete cascade
);

create table puestos
(
    idPuesto     int auto_increment
        primary key,
    nombrePuesto varchar(25) not null,
    seccion      varchar(15) not null,
    privilegio   tinyint     not null,

    constraint ck_seccion check (puestos.seccion in ('DIRECCION','COMERCIAL','PRODUCCION','ADMINISTRACION','FINANZAS'))
);

create table personal
(
    idPersonal     int auto_increment
        primary key,
    idPuesto       int         not null,
    nombreCompleto varchar(65) not null,
    email          varchar(30) not null unique,
    password       varchar(16) not null,
    telefono       int(12)     not null,
    constraint fk_personal_puesto
        foreign key (idPuesto) references puestos (idPuesto)
            on delete cascade,
    constraint ck_email_personal
        check (`email` like '%@%'),
    constraint ck_passwordPer
        check (CHAR_LENGTH(password) >= 6)
);

create table compras
(
    idCompra         int auto_increment
        primary key,
    idPersonal       int not null,
    idProveedor      int not null,
    idFactura        int not null,
    constraint fk_compras_factura
        foreign key (idFactura) references facturas (idFactura)
            on delete cascade,
    constraint fk_compras_personal
        foreign key (idPersonal) references personal (idPersonal)
            on delete cascade,
    constraint fk_compras_proveedor
        foreign key (idProveedor) references proveedores (idProveedor)
            on delete cascade
);

create table lineas_compra
(
    idLineaCompra int auto_increment primary key,
    idCompra int,
    idProducto int,
    cantidadComprada int,
    constraint fk_lineascompra_producto
        foreign key (idProducto) references productos (idProducto)
            on delete cascade,
    constraint fk_lineascompra_compra
        foreign key (idCompra) references compras (idCompra)
            on delete cascade,
    constraint ck_cantidadCompra
        check (`cantidadComprada` > 0)
);

create table telefonos_cliente
(
    idTelefono  int auto_increment
        primary key,
    idCliente   int     not null,
    numTelefono int(12) not null,
    constraint fk_telefonos_clientes
        foreign key (idCliente) references clientes (idCliente)
            on delete cascade
);

create table telefonos_proveedor
(
    idTelefonoProv  int auto_increment
        primary key,
    idProveedor int  not null,
    numTelefono int(12) not null,
    constraint fk_telefonos_proveedor
        foreign key (idProveedor) references proveedores (idProveedor)
            on delete cascade
);

create table ventas
(
    idVenta         int auto_increment
        primary key,
    idPersonal      int not null,
    idFactura       int not null,
    idCliente       int not null,
    constraint fk_ventas_clientes
        foreign key (idCliente) references clientes (idCliente)
            on delete cascade,
    constraint fk_ventas_factura
        foreign key (idFactura) references facturas (idFactura)
            on delete cascade,
    constraint fk_ventas_personal
        foreign key (idPersonal) references personal (idPersonal)
            on delete cascade
);

create table lineas_venta
(
    idLineaVenta int auto_increment primary key,
    idVenta int,
    idProducto int,
    cantidadVendida int,
    constraint fk_lineasventa_producto
        foreign key (idProducto) references productos (idProducto)
            on delete cascade,
    constraint fk_lineasventa_venta
        foreign key (idVenta) references ventas (idVenta)
            on delete cascade,
    constraint ck_cantidadVendida
        check (`cantidadVendida` > 0)
);

/*Creaciรณn de mรณdulos extras de fabricaciรณn*/
create table escandallos
(
    idEscandallo int auto_increment primary key,
    idProducto   int         not null,
    nombreModelo varchar(40) not null,


    constraint fk_escandallo_producto foreign key (idProducto) references productos (idProducto) on delete cascade


);

create table produccion
(
    idProduccion            int auto_increment primary key,
    idPersonal              int not null,
    fechaProduccion         timestamp,
    unidadesFabricadas      int not null,

    constraint fk_produccion_personal foreign key (idPersonal) references personal (idPersonal) on delete cascade,
    constraint ck_unidades check (unidadesFabricadas > 0)
);

create table escandallosxproduccion
(
    idEscandallo int,
    idProduccion int,
    primary key (idEscandallo, idProduccion),

    constraint fk_EscandalloxProduccion_idEscandallo foreign key (idEscandallo) references escandallos (idEscandallo),
    constraint fk_EscandalloxProduccion_idProduccion foreign key (idProduccion) references produccion (idProduccion)
);

create table produccionxproducto
(
    idProducto   int,
    idProduccion int,
    primary key (idProducto, idProduccion),

    constraint fk_Produccionxproducto_idProducto foreign key (idProducto) references productos (idProducto),
    constraint fk_Produccionxproducto_idProduccion foreign key (idProduccion) references produccion (idProduccion)
);

create table escandallosxproducto
(
    idEscandallo int,
    idProducto   int,
    unidades int not null,
    primary key (idEscandallo, idProducto),
    constraint fk_Escandallosxproducto_idEscandallo foreign key (idEscandallo) references escandallos (idEscandallo),
    constraint fk_Escandallosxproducto_idProducto foreign key (idProducto) references productos (idProducto),
    constraint ck_unidades_encandallos check (unidades > 0)
);


