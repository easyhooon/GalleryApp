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
import com.kenshi.gallery.presentation.util.extension.clearFocusOnKeyboardDismiss

// https://stackoverflow.com/questions/68389802/how-to-clear-textfield-focus-when-closing-the-keyboard-and-prevent-two-back-pres
@Composable
fun SearchTextField(
    searchQuery: TextFieldValue,
    updateSearchQuery: (TextFieldValue) -> Unit,
    onSearchQuery: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    // TODO 포커스가 해제되지 않는 문제 아직 해결되지 않았음
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clearFocusOnKeyboardDismiss(),
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
