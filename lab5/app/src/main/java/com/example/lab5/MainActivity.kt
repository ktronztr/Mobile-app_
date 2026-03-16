package com.example.lab5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab5.data.Dog
import com.example.lab5.data.dogs
import com.example.lab5.ui.theme.Lab5Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lab5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    WoofApp()
                }
            }
        }
    }
}

@Composable
fun WoofApp() {

    Scaffold(
        topBar = { WoofTopBar() }
    ) { padding ->

        LazyColumn(
            contentPadding = padding
        ) {

            items(dogs) { dog ->
                DogItem(
                    dog = dog,
                    modifier = Modifier.padding(
                        dimensionResource(R.dimen.padding_small)
                    )
                )
            }

        }
    }
}

@Composable
fun DogItem(
    dog: Dog,
    modifier: Modifier = Modifier
) {

    Card(modifier = modifier) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small))
        ) {

            DogImage(dog.imageResourceId)

            DogInfo(
                dog.name,
                dog.age
            )

        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun WoofTopBar(modifier: Modifier = Modifier) {

    CenterAlignedTopAppBar(

        title = {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(R.drawable.ic_woof_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small))
                )

                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )

            }

        },
        modifier = modifier
    )
}

@Composable
fun DogImage(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {

    Image(
        painter = painterResource(image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small)
    )
}

@Composable
fun DogInfo(
    @StringRes name: Int,
    age: Int,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {

        Text(
            text = stringResource(name),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(
                top = dimensionResource(R.dimen.padding_small)
            )
        )

        Text(
            text = stringResource(R.string.years_old, age),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun WoofPreview() {
    Lab5Theme {
        WoofApp()
    }
}