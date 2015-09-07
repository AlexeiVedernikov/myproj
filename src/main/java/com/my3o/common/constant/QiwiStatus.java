package com.my3o.common.constant;

public enum QiwiStatus {
    // Новый, ожидает оплаты, оплачен, отклонен, ошибка при проведении оплаты,
    // время жизни счета истекло
    New, waiting, paid, rejected, unpaid, expired
}