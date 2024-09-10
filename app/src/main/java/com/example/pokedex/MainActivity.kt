package com.example.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pokedex.ui.theme.PokedexTheme
import com.example.pokedex.viewmodel.PokedexViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexTheme {
                PokedexScreen()
            }
        }
    }
}

@Composable
fun PokedexScreen(pokedexViewModel: PokedexViewModel = viewModel()) {

    val pokemonState by pokedexViewModel.pokemonState.observeAsState()
    var searchTerm by remember {
        mutableStateOf("")
    }

    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            AsyncImage(
                model = pokemonState?.image ?: "https://e7.pngegg.com/pngimages/324/645/png-clipart-pokeball-pokeball-thumbnail.png",
                contentDescription = "Pokemon",
                modifier = Modifier.size(180.dp)
            )
            Text(text = pokemonState?.name ?: "None", fontSize = 30.sp)
            Text(text = "Attack")
            Text(text = pokemonState?.attack ?: "0", fontSize = 30.sp)
            Text(text = "Defense")
            Text(text = pokemonState?.defense ?: "0", fontSize = 30.sp)
            Text(text = "Health")
            Text(text = pokemonState?.health ?: "0", fontSize = 30.sp)
            Text(text = "Speed")
            Text(text = pokemonState?.speed ?: "0", fontSize = 30.sp)

            TextField(value = searchTerm, onValueChange = {searchTerm = it})
            Button(onClick = {
                pokedexViewModel.getPokemon(searchTerm)
            }) {
                Text(text = "Test")
            }
        }
    }
}

@Composable
fun Stat(label:String, value:String){
    Column {
        Text(text = label)
        Text(text = value, fontSize = 30.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokedexTheme {

    }
}