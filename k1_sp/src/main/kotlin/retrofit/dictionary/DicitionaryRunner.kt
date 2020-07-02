package retrofit.dictionary

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl("https://dictionary.yandex.net")
    .addConverterFactory(SimpleXmlConverterFactory.create())
    .build()

val yandexDictionaryService = retrofit.create(YandexDictionaryService::class.java)

val retrofitJson = Retrofit.Builder()
    .baseUrl("https://dictionary.yandex.net")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val yandexDictionaryServiceJson = retrofitJson.create(YandexDictionaryService::class.java)

fun main() {

    val response = yandexDictionaryService.lookUp("en-ru", "fighter jet").execute()
    if (response.isSuccessful) {
        val result = response.body()
        println(
            result?.defs?.get(0)?.trs?.get(0)?.text
        )
    }

    val responseJson = yandexDictionaryServiceJson.lookUpJson("en-ru", "fighter jet").execute()
    if (responseJson.isSuccessful) {
        val result = responseJson.body()
        println(
            result?.def?.get(0)?.tr?.get(0)?.text
        )
    }

}