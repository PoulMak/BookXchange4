package com.example.bookxchange.presentation.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.bookxchange.R
import com.example.bookxchange.data.AppDatabase
import com.example.bookxchange.data.ChatMessage
import com.example.bookxchange.ui.theme.Grey1
import com.example.bookxchange.ui.theme.Purple1
import com.example.bookxchange.ui.theme.Purple2

// Модель данных сообщения
data class ChatMessage(val text: String, val sender: String)

// Примерный список сообщений
var chatMessages = listOf(
    ChatMessage("Hi, how are you?", "Alice"),
    ChatMessage("I'm good, thanks. How about you?", "Bob"),
    ChatMessage("I'm doing pretty well, thanks for asking!", "Alice"),
    ChatMessage("Good to hear", "Bob")
)

@Composable
fun ChatScreen(navController: NavHostController) {
    val message = remember { mutableStateOf(TextFieldValue()) }

    Scaffold(containerColor = Purple1,
        topBar = {
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .background(Purple1)
                    .background(
                        color = Purple2,
                        shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp)
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.nophoto),
                    contentDescription = "Profile Picture",
                    alignment = Alignment.TopStart,
                    modifier = Modifier
                        .height(44.dp)
                        .width(44.dp)
                        .padding(start = 5.dp, top = 3.dp, bottom = 3.dp),
                    contentScale = ContentScale.Crop,
                )
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp),
                    text = stringResource(id = R.string.chat_with_Bob),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        },
        content = { padding->
            Column(
                modifier = Modifier
                    .padding(top=50.dp, bottom = 15.dp, start = 10.dp, end = 10.dp)
                    .fillMaxHeight()
                    .clip(shape = RoundedCornerShape(10.dp))
                    .verticalScroll(rememberScrollState(), reverseScrolling = true)
                    .background(color = Grey1),
                verticalArrangement = Arrangement.Bottom
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                ) {
                    chatMessages.forEach { message ->
                        if (message.sender == "Alice") {
                            Card(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.End)
                                    .width(200.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(defaultElevation = 15.dp),
                                shape = RoundedCornerShape(15.dp)
                            ) {
                                Row(modifier = Modifier.padding(5.dp)) {
                                    Text(
                                        text = message.text,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = Color.Black
                                    )
                                }
                            }

                        } else {
                            Card(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.Start)
                                    .width(200.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(defaultElevation = 15.dp),
                                shape = RoundedCornerShape(15.dp)
                            ) {
                                Row(modifier = Modifier.padding(5.dp)) {
                                    Text(
                                        text = message.text,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = Color.Blue
                                    )
                                }
                            }
                        }
                    }
                }

                Row(modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)) {
                    OutlinedTextField(
                        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                        value = message.value,
                        onValueChange = { message.value = it },
                        label = { Text(text = "Type a message") },
                        modifier = Modifier.weight(1f)
                    )
                    Button(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        onClick = {
                            // Добавляет сообщение в список сообщений
                            chatMessages += ChatMessage(message.value.text, "Alice")

                            // Очищает строку ввода
                            message.value = TextFieldValue("")
                        }
                    ) {
                        Text(text = "Send")
                    }
                }
            }
        }
    )
}
@Composable
fun ChatScreen2(db: AppDatabase) {
    val messageList = remember { mutableStateListOf<ChatMessage>() }

    LaunchedEffect(Unit) {
        val messages = db.messageDao().getAllMessages()
        messageList.addAll(messages)
    }

    val textState = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(messageList) { message ->
                ChatMessageRow(
                    message = message
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    val message = ChatMessage(
                        senderName = "Me",
                        datetime = "сегодня",
                        message = textState.value.text,
                        id=1
                    )

                    db.messageDao().addMessage(message)
                    messageList.add(message)
                    textState.value = TextFieldValue("")
                }
            ) {
                Text("Send")
            }
        }
    }
}

@Composable
fun ChatMessageRow(message: ChatMessage) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "${message.senderName}: ${message.message}",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = if (message.senderName == "Me") FontWeight.Bold else FontWeight.Normal
        )
        Text(
            text = message.datetime,
            style = MaterialTheme.typography.displaySmall
        )
    }
}