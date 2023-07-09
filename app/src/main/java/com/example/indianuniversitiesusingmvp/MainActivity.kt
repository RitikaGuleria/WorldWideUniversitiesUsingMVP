
package com.example.indianuniversitiesusingmvp

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.indianuniversitiesusingmvp.contracts.MainActivityContracts
import com.example.indianuniversitiesusingmvp.model.MainModel
import com.example.indianuniversitiesusingmvp.network.api.APIService
import com.example.indianuniversitiesusingmvp.network.model.UniversityDTO
import com.example.indianuniversitiesusingmvp.presenter.MainPresenter
import com.example.indianuniversitiesusingmvp.ui.theme.IndianUniversitiesUsingMVPTheme
import com.google.android.material.search.SearchBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() , MainActivityContracts.View{

    @Inject
    lateinit var apiService: APIService
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            presenter = MainPresenter(this,MainModel(apiService = apiService))
            IndianUniversitiesUsingMVPTheme {
                Searchview()
            }
        }
    }

    override fun onLoading() {
        TODO("Not yet implemented")
    }

    override fun onSuccess(list: List<UniversityDTO>) {
        TODO("Not yet implemented")
    }

    override fun onError(message: String) {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Searchview()
{
    var text by remember{ mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var items = remember {
        mutableStateListOf("India","USA")
    }


   Scaffold {
       SearchBar(
           modifier= Modifier.fillMaxWidth(),
           query =text ,
           onQueryChange = {text=it},
           onSearch = {
               items.add(text)
               active=false
                text=""}
           ,
           active = active,
           onActiveChange = {active=it},
           placeholder = {Text("Search")},
           leadingIcon = { androidx.compose.material3.Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon") },
           trailingIcon = {
               if(active)
               {
                   androidx.compose.material3.Icon(modifier=Modifier.clickable{ if(text.isNotEmpty()){
                           text=""
                       }else{
                           active=false
                       }},imageVector = Icons.Default.Close, contentDescription = "Close Icon")
               }
           }
       )
       {
           items.forEach{
               Row(modifier=Modifier.padding(14.dp))
               {
                    androidx.compose.material3.Icon(imageVector = Icons.Default.History, contentDescription = "")
               }
           }
       }
   }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IndianUniversitiesUsingMVPTheme {
        Searchview()
}}