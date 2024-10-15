package com.rickaban.android.getrandomusers.screen.random_user_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rickaban.android.getrandomusers.R
import com.rickaban.android.getrandomusers.app.ui.AlignedRow
import com.rickaban.android.getrandomusers.screen.random_user_list.model.RandomUser

@Composable
fun RandomUserDetailSheet(
    randomUser: RandomUser,
    state: RandomUserDetailState,
    action: (RandomUserDetailAction) -> Unit
) {
    Scaffold { padding ->
        Box{
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(padding),
               horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfilePicture(randomUser)
                Spacer(modifier = Modifier.height(17.dp))
                MainDetails(randomUser)
                Spacer(modifier = Modifier.height(17.dp))
                ContactDetails(randomUser)
                OtherDetails(randomUser)
            }

            IconButton(
                onClick = { action(RandomUserDetailAction.Dismiss) },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close"
                )
            }
        }
    }
}

@Composable
private fun ProfilePicture(
    randomUser: RandomUser
) {
    Box(
        modifier = Modifier
            .size(90.dp)
            .clip(CircleShape)
            .background(Color.Gray.copy(alpha = 0.2f))
    ) {
        val painter = rememberAsyncImagePainter(
            model = randomUser.profilePictureUrl,
            onError = {
                it.result.throwable.printStackTrace()
            }
        )
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1F),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun MainDetails(
    randomUser: RandomUser
) {
    Text(
        text = "${randomUser.firstName} ${randomUser.lastName}",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    Text(
        text = randomUser.username,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurface.copy(0.6F),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun ContactDetails(
    randomUser: RandomUser
) {
    Column(modifier = Modifier.padding(17.dp)) {
        Text(
            text = stringResource(id = R.string.contact_details)
        )
        Spacer(modifier = Modifier.height(7.dp))
        Card {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AlignedRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "email",
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = randomUser.email,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                AlignedRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "phone",
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = randomUser.phone,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                AlignedRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    Icon(
                        imageVector = Icons.Default.PhoneAndroid,
                        contentDescription = "cell",
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = randomUser.cell,
                         style = MaterialTheme.typography.labelLarge
                    )
                }
                AlignedRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "address",
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = randomUser.address,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Composable
private fun OtherDetails(
    randomUser: RandomUser
) {
    Column(modifier = Modifier.padding(17.dp)) {
        Text(
            text = stringResource(id = R.string.other_details)
        )
        Spacer(modifier = Modifier.height(7.dp))
        Card {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AlignedRow {
                    Text(
                        text = stringResource(id = R.string.birthday),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = randomUser.birthday,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                HorizontalDivider()
                AlignedRow {
                    Text(
                        text = stringResource(id = R.string.nationality),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = stringResource(id = randomUser.nationalityStringResourceID),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}