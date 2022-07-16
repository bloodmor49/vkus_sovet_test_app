package com.example.vkus_sovet_test_app.domain.util

/** Вспомогательный класс для передачи данных в соответствии с результатами их получения.
 * Если они получены, то мы будем иметь Success + данные.
 * Если же была ошибка, то мы получим Error и сообщение ошибки.
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String?, data: T? = null) : Resource<T>(data,message)
}

