package com.kenshi.gallery.presentation.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kenshi.gallery.presentation.R
import com.kenshi.gallery.presentation.ui.theme.TextLMedium

@Composable
fun SearchTextField(
    searchQuery: TextFieldValue,
    updateSearchQuery: (TextFieldValue) -> Unit,
    onSearchQuery: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = searchQuery,
        singleLine = true,
        onValueChange = updateSearchQuery,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(R.string.search_icon),
            )
        },
        trailingIcon = {
            if (searchQuery.text.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Outlined.Cancel,
                    contentDescription = stringResource(R.string.search_icon),
                    modifier = Modifier.clickable {
                        updateSearchQuery(TextFieldValue(""))
                    },
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchQuery(searchQuery)
                keyboardController?.hide()
            },
        ),
        placeholder = {
            Text(
                text = stringResource(R.string.author_search),
                style = TextLMedium,
            )
        },
    )

//    BasicTextField(
//        value = searchQuery,
//        onValueChange = updateSearchQuery,
//        singleLine = true,
//        modifier = modifier
//            .height(46.dp)
//            .fillMaxWidth()
//            .padding(horizontal = 20.dp),
//        keyboardOptions = KeyboardOptions(
//            imeAction = ImeAction.Search,
//        ),
//        keyboardActions = KeyboardActions(
//            onSearch = {
//                onSearchQuery(searchQuery)
//                keyboardController?.hide()
//            },
//        ),
//        decorationBox = { innerTextField ->
//            Row(
//                modifier = Modifier
//                    .background(color = Color.White, shape = RoundedCornerShape(67.dp))
//                    .border(
//                        border = BorderStroke(width = 1.dp, color = Color(0xFFBABABA)),
//                        shape = RoundedCornerShape(67.dp),
//                    ),
//                verticalAlignment = Alignment.CenterVertically,
//            ) {
//                Spacer(modifier = Modifier.width(16.dp))
//                Icon(
//                    imageVector = Icons.Default.Search,
//                    contentDescription = "",
//                    tint = Color.DarkGray,
//                )
//                Spacer(modifier = Modifier.width(width = 8.dp))
//                Box {
//                    if (searchQuery.text.isEmpty()) {
//                        Text(
//                            text = stringResource(R.string.author_search),
//                            style = TextLMedium,
//                        )
//                    }
//                    innerTextField()
//                }
//                Spacer(modifier = Modifier.weight(1f))
//                if (searchQuery.text.isNotEmpty()) {
//                    Icon(
//                        imageVector = Icons.Outlined.Cancel,
//                        contentDescription = stringResource(R.string.search_icon),
//                        modifier = Modifier.clickable {
//                            updateSearchQuery(TextFieldValue(""))
//                        },
//                    )
//                }
//                Spacer(modifier = Modifier.width(16.dp))
//            }
//        },
//    )

//    OutlinedTextField(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        value = searchQuery,
//        singleLine = true,
//        onValueChange = updateSearchQuery,
//        leadingIcon = {
//            Icon(
//                imageVector = Icons.Filled.Search,
//                contentDescription = stringResource(R.string.search_icon),
//            )
//        },
//        trailingIcon = {
//            if (searchQuery.text.isNotEmpty()) {
//                Icon(
//                    imageVector = Icons.Outlined.Cancel,
//                    contentDescription = stringResource(R.string.search_icon),
//                    modifier = Modifier.clickable {
//                        updateSearchQuery(TextFieldValue(""))
//                    },
//                )
//            }
//        },
//        keyboardOptions = KeyboardOptions(
//            imeAction = ImeAction.Search
//        ),
//        keyboardActions = KeyboardActions(
//            onSearch = {
//                onSearchQuery(searchQuery)
//                keyboardController?.hide()
//            },
//        ),
//        placeholder = {
//            Text(
//                text = stringResource(R.string.author_search),
//                style = TextLMedium,
//            )
//        },
//    )
}

@Preview
@Composable
fun SearchTextFieldPreview() {
    SearchTextField(
        searchQuery = TextFieldValue(""),
        updateSearchQuery = {},
        onSearchQuery = {},
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchTextFieldPreview_DarkMode() {
    SearchTextField(
        searchQuery = TextFieldValue(""),
        updateSearchQuery = {},
        onSearchQuery = {},
    )
}
