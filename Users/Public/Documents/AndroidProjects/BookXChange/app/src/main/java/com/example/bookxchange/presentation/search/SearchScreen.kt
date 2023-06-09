package com.example.bookxchange.presentation.search

import android.app.LocaleManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.bookxchange.R
import com.example.bookxchange.ui.theme.Purple1
import com.example.bookxchange.ui.theme.Purple2
import com.example.bookxchange.ui.theme.Purple3

data class Book(
    val title: String,
    val author: String,
    val genre: String,
    val imageId: Int,
    val id: Int,
    val description: String

) {
    companion object {
        val ALL = listOf(
            Book(
                "1984",
                "Джордж Оруэлл",
                "Классика",
                R.drawable.oruel_book,
                1,
                "это роман о том, как репрессивная машина тоталитарного государства может уничтожить любую личность. Даже Смит, который до последнего сопротивлялся тирании, в конце концов сдался."
            ),
            Book(
                "Послемрак",
                "Харуки Мураками",
                "Классика",
                R.drawable.after_murakami,
                2,
                "Всё действие романа происходит в течение одной ночи в центре Токио. Главная героиня — 19-летняя студентка Мари, проводящая ночь в кофейне, читая книжку. Там она встречает Такахаси, студента, играющего на тромбоне. Такахаси знает сестру Мари — Эри Асаи, между тем, сама Эри сейчас в глубоком сне. Пути Мари пересекаются с женщиной, бывшим бойцом, сейчас работающей администратором в лав-отеле; китайской проституткой, которая была избита и ограблена; компьютерным экспертом с садистскими наклонностями. Сюжет разворачивается между сном и реальностью."
            ),
            Book(
                "Кафка на пляже",
                "Харуки Мураками",
                "Классика",
                R.drawable.kafka_murakami,
                3,
                "психологический триллер, метафорическая феерия! Где реальность переплетается с миром духов, где действуют мифические персонажи и помогают Кафке — Тамуре. Но главная идея в том, что человек не выбирает свою судьбу, судьба выбирает человека. Семейное проклятие должно сбыться. Но как и когда?"
            ),
            Book(
                "Мой любимый спутник",
                "Харуки Мураками",
                "Классика",
                R.drawable.sputnik_murakami,
                4,
                "Этот роман - один из моих любимых у Мураками. Необычная история с вкраплением других небольших необычных историй. Открытый конец, но все-таки с определенным намеком - на мой взгляд, хорошее решение для романа. Как всегда, переплетение разных миров, поиск себя, тема одиночества."
            ),
            Book(
                "На юг от границы, на запад от солнца",
                "Харуки Мураками",
                "Классика",
                R.drawable.south_murakami,
                5,
                "Теплая, уютная, но наивная трагедия о дружбе, эгоизме и одиночестве.О том, как взрослые люди так и не смогли найти себя во взрослом мире. Детская дружба навсегда соединила их сердца, в которых после разлуки образовалась пустота. Подростковый эгоизм и еще одна поломанная судьба..."
            ),
            Book(
                "Послемрак",
                "Харуки Мураками",
                "Классика",
                R.drawable.after_murakami,
                6,
                "Прежде чем мы начнём собирать Мураками–пазл, коснёмся сюжета произведения. Он берёт начало в ресторане «Денниз», где девушка-студентка поздним вечером, коротая время, читает книгу. Спустя несколько часов, по пути на репетицию в музыкальной группе, сюда заглядывает Тэ́цуя Такаха́си и узнаёт в незнакомке младшую сестру своей бывшей одноклассницы Эри Аса́и — Мари́. Совершенно случайная встреча. Но именно она стала отправной точкой очень запутанной и странной ночной истории! Впрочем, в этом нет ничего удивительного, ведь «после полуночи время идёт иначе. И с этим ничего не поделать»."
            ),
            Book(
                "Кафка на пляже",
                "Харуки Мураками",
                "Классика",
                R.drawable.after_murakami,
                7,
                "Прежде чем мы начнём собирать Мураками–пазл, коснёмся сюжета произведения. Он берёт начало в ресторане «Денниз», где девушка-студентка поздним вечером, коротая время, читает книгу. Спустя несколько часов, по пути на репетицию в музыкальной группе, сюда заглядывает Тэ́цуя Такаха́си и узнаёт в незнакомке младшую сестру своей бывшей одноклассницы Эри Аса́и — Мари́. Совершенно случайная встреча. Но именно она стала отправной точкой очень запутанной и странной ночной истории! Впрочем, в этом нет ничего удивительного, ведь «после полуночи время идёт иначе. И с этим ничего не поделать»."
            )
        )
    }
}

@Composable
fun SearchScreen(navController: NavHostController) {
    val books = Book.ALL
    var searchString by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.background(Purple1).fillMaxSize(),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Purple1)
                    .background(
                        color = Purple2,
                        shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp)
                    ), contentAlignment = Alignment.Center
            ) {
                Column() {
                    Text(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .align(CenterHorizontally),
                        text = stringResource(id = R.string.book_list),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White,
                    )
                    var localeFocus = LocalFocusManager.current
                    OutlinedTextField(
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.Black,
                            disabledLabelColor = Color.White
                        ),
                        value = searchString,
                        onValueChange = { searchString = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Поиск") },
                        keyboardActions = KeyboardActions{ localeFocus.clearFocus()},
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                    )
                }
            }
        },
        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(color = Purple1)
            ) {
                items(books.filter { it.title.lowercase().contains(searchString.lowercase()) })
                { book ->
                    BookListItem(book = book, navController = navController)
                }
            }
        }
    )
}

@Composable
fun BookListItem(book: Book, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 15.dp
        ),
        colors = CardDefaults.cardColors(contentColor = Color.Black, containerColor = Purple2)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 15.dp
                )
            ) {
                Image(
                    painter = painterResource(id = book.imageId),
                    contentDescription = book.title,
                    modifier = Modifier
                        .height((320 / 2).dp)
                        .width((210 / 2).dp),
                    contentScale = ContentScale.Crop,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(color = Purple3)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(8.dp)
                ) {
                    Text(
                        text = book.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = book.author,
                        fontSize = 14.sp
                    )
                    Text(
                        text = book.genre,
                        fontSize = 14.sp
                    )
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                        Button(
                            onClick = { navController.navigate("Info?bookId=${book.id}") },
                        ) {
                            Text(text = stringResource(id = R.string.info))
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BookitemPreview() {
    SearchScreen(rememberNavController())
}
