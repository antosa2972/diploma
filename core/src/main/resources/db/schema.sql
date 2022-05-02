drop table if exists phone2color;
drop table if exists colors;
drop table if exists stocks;
drop table if exists phones;
drop table if exists orders;
drop table if exists orderItems;
drop table if exists employees;

create table colors
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50),
    UNIQUE (code)
);

create table phones
(
    id                    BIGINT AUTO_INCREMENT primary key,
    brand                 VARCHAR(50)  NOT NULL,
    model                 VARCHAR(254) NOT NULL,
    price                 FLOAT,
    discountPercent       SMALLINT,
    displaySizeInches     FLOAT,
    weightGr              SMALLINT,
    lengthMm              FLOAT,
    widthMm               FLOAT,
    heightMm              FLOAT,
    announced             DATETIME,
    deviceType            VARCHAR(50),
    os                    VARCHAR(100),
    displayResolution     VARCHAR(50),
    pixelDensity          SMALLINT,
    displayTechnology     VARCHAR(50),
    backCameraMegapixels  FLOAT,
    frontCameraMegapixels FLOAT,
    ramGb                 FLOAT,
    internalStorageGb     FLOAT,
    batteryCapacityMah    SMALLINT,
    talkTimeHours         FLOAT,
    standByTimeHours      FLOAT,
    bluetooth             VARCHAR(50),
    positioning           VARCHAR(100),
    imageUrl              VARCHAR(254),
    description           VARCHAR(4096),
    CONSTRAINT UC_phone UNIQUE (brand, model)
);

create table phone2color
(
    phoneId BIGINT,
    colorId BIGINT,
    CONSTRAINT FK_phone2color_phoneId FOREIGN KEY (phoneId) REFERENCES phones (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_phone2color_colorId FOREIGN KEY (colorId) REFERENCES colors (id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table stocks
(
    phoneId  BIGINT   NOT NULL,
    stock    SMALLINT NOT NULL,
    reserved SMALLINT NOT NULL,
    UNIQUE (phoneId),
    CONSTRAINT FK_stocks_phoneId FOREIGN KEY (phoneId) REFERENCES phones (id) ON DELETE CASCADE ON UPDATE CASCADE
);
create table orders
(
    id              BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    subtotal        FLOAT,
    deliveryPrice   FLOAT,
    totalPrice      FLOAT,
    firstName       VARCHAR(50),
    lastName        VARCHAR(50),
    deliveryAddress VARCHAR(100),
    contactPhoneNo  VARCHAR(15),
    additionalInfo  VARCHAR(500),
    status          ENUM ('NEW', 'DELIVERED', 'REJECTED'),
    date            TIMESTAMP
);
create table orderItems
(
    id       BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    phoneId  BIGINT NOT NULL,
    orderId  BIGINT NOT NULL,
    quantity BIGINT NOT NULL,
    CONSTRAINT FK_orderItems_phoneId FOREIGN KEY (phoneId) REFERENCES phones (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_orderItems_orderId FOREIGN KEY (orderId) REFERENCES orders (id) ON DELETE CASCADE ON UPDATE CASCADE
);
create table employees
(
    id        BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName  VARCHAR(50) NOT NULL,
    position  VARCHAR(50) NOT NULL,
    employeeType ENUM('MALE','FEMALE','GEEK') NOT NULL
);
