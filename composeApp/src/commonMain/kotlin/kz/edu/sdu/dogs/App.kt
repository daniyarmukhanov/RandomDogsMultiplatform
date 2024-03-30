package kz.edu.sdu.dogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import randomdogs.composeapp.generated.resources.*
import kz.edu.sdu.dogs.theme.AppTheme
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun App(
    viewModel: MainViewModel = MainViewModel()
) = AppTheme {
    Column(
        modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(Res.string.title),
            fontFamily = FontFamily(Font(Res.font.IndieFlower_Regular)),
            style = MaterialTheme.typography.displayLarge
        )
        val icon = remember {
            Res.drawable.ic_cyclone
        }
        ElevatedButton(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            .widthIn(min = 200.dp),
            onClick = viewModel::showAnotherDog,
            content = {
                Icon(vectorResource(icon), contentDescription = null)
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(stringResource(Res.string.show_another_dog_button_text))
            })

        LazyVerticalGrid(
            columns = GridCells.Adaptive(200.dp)
        ) {
            items(viewModel.listOfDogsImageLink) {
                Box(modifier = Modifier.padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = rememberImagePainter(it),
                        contentDescription = null,
                        modifier = Modifier.width(200.dp).height(200.dp),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        }

    }
}


