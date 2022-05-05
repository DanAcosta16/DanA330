package com.example.worddictionary.search


import androidx.lifecycle.*
import com.example.worddictionary.database.Word
import org.json.JSONArray

class SearchViewModel : ViewModel() {
    private var imageURL = "https://www.merriam-webster.com/assets/mw/static/art/dict/"

    private val _suggestedWords = MutableLiveData<List<String>>()

//    val suggestedWords: LiveData<List<String>>
//        get() = _suggestedWords



    fun parseJsontoWord(wordId: String, jsonString: String): Word {
        val json = JSONArray(jsonString)
        val entry = json.getJSONObject(0)
        val shortDef = entry.getJSONArray("shortdef")
        if(entry.has("art")) {
            val art = entry.getJSONObject("art")
            val artID = art.getString("artid")
            val artURL = "$imageURL$artID.gif"
            return constructWord(wordId, artURL, shortDef)
        }
        return constructWord(wordId, null, shortDef)

    }

    private fun constructWord(wordId: String, image: String?, json: JSONArray): Word {
        val word = when (json.length()) {
            0 -> Word(wordId, null, "No definition available")
            1 -> Word(wordId, image, json.getString(0))
            2 -> Word(wordId, image, json.getString(0), json.getString(1))
            else -> Word(
                wordId, image,
                json.getString(0),
                json.getString(1),
                json.getString(2)
            )

        }
        return word
    }

    fun setSuggestedWords(searchList: MutableList<String>){
        _suggestedWords.value = searchList
    }
}