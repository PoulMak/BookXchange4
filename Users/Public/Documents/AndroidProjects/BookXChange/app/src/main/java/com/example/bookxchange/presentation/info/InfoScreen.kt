import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.bookxchange.R
import com.example.bookxchange.presentation.info.InfoViewModel
import com.example.bookxchange.presentation.search.Book
import com.example.bookxchange.ui.theme.Grey1
import com.example.bookxchange.ui.theme.Purple1
import com.example.bookxchange.ui.theme.Purple2

@Composable
fun InfoScreen(
    navController: NavHostController,
    viewModel: InfoViewModel = hiltViewModel(),
) {
    val bookId = viewModel.bookId
    val book = Book.ALL.find { it.id == bookId }!!

    val state = rememberScrollState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Purple1,
        topBar = {
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .background(Purple1)
                    .background(
                        color = Purple2,
                        shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp)
                    ), contentAlignment = Alignment.Center
            ) {
                Button(onClick = { navController.navigateUp() }, modifier = Modifier.align(Alignment.CenterStart)) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp),
                    text = stringResource(id = R.string.book_info),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 70.dp, start = 10.dp, end = 10.dp)
                    .verticalScroll(rememberScrollState())
                    .background(color = Grey1)
            ) {
                    Image(
                        alignment = Alignment.Center,
                        painter = painterResource(id = book.imageId),
                        contentDescription = book.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(280.dp)
                            .width(168.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Column(
                        modifier = Modifier
                            .padding(8.dp), horizontalAlignment = Alignment.Start) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = "\"" + book.title + "\"",
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            color = Color.Black
                        )
                        Text(
                            modifier = Modifier.padding(15.dp),
                            text = stringResource(id = R.string.author) + " : \n" + book.author,
                            fontSize = 21.sp
                        )
                        Text(
                            modifier = Modifier.padding(15.dp),
                            text = stringResource(id = R.string.genre) + " : \n" + book.genre,
                            fontSize = 21.sp
                        )
                        Text(
                            modifier = Modifier.padding(15.dp),
                            text = stringResource(id = R.string.description) + " : \n" + book.description,
                            fontSize = 21.sp
                        )
                    }


            }
        }
    )
}