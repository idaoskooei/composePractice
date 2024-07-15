package com.myapp.composepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapp.composepractice.ui.theme.ComposePracticeTheme
import com.myapp.composepractice.ui.theme.Purple40

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePracticeTheme {
                Greeting()
            }
        }
    }
}

@Composable
fun Greeting() {
    val historyItems = listOf(
        HistoryData(title = "android developer", jobNumber = "1", dates = "from 2023-2025"),
        HistoryData(title = "android developer", jobNumber = "2", dates = "from 2023-2025"),
        HistoryData(title = "android developer", jobNumber = "3", dates = "from 2023-2025"),
        HistoryData(title = "android developer", jobNumber = "4", dates = "from 2023-2025"),
        HistoryData(title = "android developer", jobNumber = "5", dates = "from 2023-2025"),
        HistoryData(title = "android developer", jobNumber = "6", dates = "from 2023-2025"),
        HistoryData(title = "android developer", jobNumber = "7", dates = "from 2023-2025"),
        HistoryData(title = "android developer", jobNumber = "8", dates = "from 2023-2025"),
        HistoryData(title = "android developer", jobNumber = "9", dates = "from 2023-2025"),
        HistoryData(title = "android developer", jobNumber = "10", dates = "from 2023-2025")
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Handle FAB click */ },
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        topBar = {
            var search by rememberSaveable { mutableStateOf("") }
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = search, onValueChange = { search = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .background(Color.Transparent),
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = ""
                        )
                    },
                    label = {
                        Text(text = "search candidate")
                    }
                )
            }
        },
        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                item { HeaderCardView(name = "Ida Oskooei", title = "Software Engineer") }
                item { WorkExperience() }
                items(historyItems) { history ->
                    History(
                        title = history.title,
                        jobNumber = history.jobNumber,
                        dates = history.dates
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting()
}


//@Composable
//fun SearchBar() {
//    var text by rememberSaveable { mutableStateOf("") }
//    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
//        Icon(imageVector = Icons.Default.Search, contentDescription = "search")
//        TextField(value = text, onValueChange = { text = it })
//    }
//}

@Composable
fun HeaderCardView(name: String, title: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .border(border = BorderStroke(1.dp, Color.Black))
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            CircularImage(
                imageResource = R.drawable.ic_launcher_foreground
            )
            HeaderTextViews(name = name, title = title)
        }
    }
}

@Composable
fun WorkExperience() {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp)
    ) {
        Card(modifier = Modifier, RoundedCornerShape(16.dp)) {
            Text(text = "work experience", modifier = Modifier.padding(5.dp))
        }
    }
}

@Composable
fun History(title: String, jobNumber: String, dates: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color.DarkGray), shape = RoundedCornerShape(10.dp)),
            colors = CardColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black,
                disabledContentColor = Color.Red,
                disabledContainerColor = Purple40
            )
        ) {
            Text(text = "Job Number: $jobNumber", modifier = Modifier.padding(5.dp))
            Text(text = "title: $title", modifier = Modifier.padding(5.dp))
            Text(text = "dates: $dates", modifier = Modifier.padding(5.dp))
        }
    }
}

@Composable
fun HeaderTextViews(name: String, title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp), horizontalAlignment = Alignment.Start
    ) {
        Text(text = name)
        Text(text = title)
    }
}

@Composable
fun CircularImage(imageResource: Int) {
    Row(
        modifier = Modifier
            .size(100.dp)
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "profile pic",
            modifier = Modifier
                .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
                .clip(CircleShape)
        )
    }
}

data class HistoryData(val title: String, val jobNumber: String, val dates: String)