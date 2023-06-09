package com.example.bookxchange.presentation.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.bookxchange.R
import com.example.bookxchange.ui.theme.Purple1
import com.example.bookxchange.ui.theme.Purple2

// Модель данных чата
data class Chat(
    val name: String, val message: String, val imageId: Int
)

// Примерный список чатов
val chatList = listOf(
    Chat("Alice", "Hi, how are you?", R.drawable.nophoto),
    Chat("Bob", "I'm good, thanks. How about you?", R.drawable.nophoto),
    Chat("Charlie", "What are you up to this weekend?", R.drawable.nophoto),
    Chat("Dave", "Just hanging out at home. You?", R.drawable.nophoto),
    Chat("Eve", "I'm thinking of going for a hike on Saturday.", R.drawable.nophoto)
)

@Composable
fun ChatList(navController: NavHostController) {
    Scaffold(containerColor = Purple1, topBar = {
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(Purple1)
                .background(
                    color = Purple2, shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp)
                ), contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
                text = stringResource(id = R.string.chat_list),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }, content = { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .background(color = Purple1)
                .fillMaxHeight()
        ) {
            items(chatList) { chatList ->
                ChatCard(chatList = chatList, navController = navController)
            }
        }
    })
}

@Composable
fun ChatCard(chatList: Chat, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = { navController.navigate("Chat") }),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 15.dp
        ),
        colors = CardDefaults.cardColors(contentColor = Color.Black, containerColor = Purple2)
    ) {
        Row(
            modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Image(
                    painter = painterResource(id = chatList.imageId),
                    contentDescription = "${chatList.name} Profile Picture",
                    modifier = Modifier
                        .size(60.dp)
                        .shadow(elevation = 15.dp, shape = CircleShape, clip = true)
                        .clip(shape = CircleShape),
                    contentScale = ContentScale.Crop,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(color = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(8.dp)
                ) {
                    Text(text = chatList.name, style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = chatList.message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Blue,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}