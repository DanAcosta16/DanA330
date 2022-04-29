package com.example.worddictionary

import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.worddictionary.network.DictionaryApi
import com.example.worddictionary.words.Word
import junit.framework.Assert.fail
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.json.JSONArray
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ApiInstrumentedTest {

    @Test
    fun getWord() = runBlocking {
        //Given

        //When
        val response = DictionaryApi.retrofitService.getWord("baseball")

        //then
        val jsonString = response.body()
        if (jsonString?.startsWith("[{\"meta\":") == true) {
            val word = parseJsonToWord("baseball", jsonString)
            assertThat(word, `is`(notNullValue()))
        } else
            fail("Unexpected response: $jsonString")
    }

    private fun parseJsonToWord(wordId: String, jsonStr: String): Word {

        val json = JSONArray(jsonStr)
        val entry = json.getJSONObject(0)
        val shortDef = entry.getJSONArray("shortdef")

        val word = when (shortDef.length()) {
            0 -> Word(wordId, "No definition available")
            1 -> Word(wordId, shortDef.getString(0))
            2 -> Word(wordId, shortDef.getString(0), shortDef.getString(1))
            else -> Word(
                wordId, shortDef.getString(0),
                shortDef.getString(1),
                shortDef.getString(2)
            )
        }
        return word
    }
}