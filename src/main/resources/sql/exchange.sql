DROP TABLE if exists `exchange`;
CREATE TABLE `exchange`
(
    `id`            varchar(10) NOT NULL,
    `baseCurrency`  varchar(3)  NOT NULL,
    `toCurrency`    varchar(3)  NOT NULL,
    `rate`          DOUBLE      NOT NULL,
    `rateDate`      DATE        NOT NULL,
    `rateTimeStamp` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `createdAt`     timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updatedAt`     timestamp            DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
