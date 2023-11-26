package com.daangn.leejihun.gallery.presentation.ui.component

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
import com.daangn.leejihun.gallery.presentation.R
import com.daangn.leejihun.gallery.presentation.ui.theme.TextLMedium

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
}

@Preview(showBackground = true)
@Composable
fun SearchTextFieldPreview() {
    SearchTextField(
        searchQuery = TextFieldValue(""),
        updateSearchQuery = {},
        onSearchQuery = {},
    )
}
