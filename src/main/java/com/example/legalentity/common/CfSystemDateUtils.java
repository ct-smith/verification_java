package com.example.legalentity.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 現在時刻・現在日付を取得し返却するクラス
 * 
 * @author wild idea
 */
public class CfSystemDateUtils {

    /**
     * 現在日時を返却する
     * 
     * @return 現在日時
     */
    public static LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 現在日付を返却する
     * 
     * @return 現在日付
     */
    public static LocalDate getDate() {
        return LocalDate.now();
    }

    /**
     * 現在日付をLocalDateTimeインスタンス(yyyy/mm/dd 00:00:00)で返却する
     * 
     * @return 現在日付(yyyy/mm/dd 00:00:00)
     */
    public static LocalDateTime getDateReturnsLocalDateTime() {
        return LocalDateTime.of(getDate(), LocalTime.of(0, 0));
    }

}
