package com.example.rentals.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Card
import androidx.compose.ui.text.style.TextOverflow

data class AddItemFormState(
    val name: String = "",
    val category: String = "",
    val price: String = "",
    val description: String = "",
    val inStock: String = "",
    val totalItems: String = "",
    val hasErrors: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItem(onItemAdded: () -> Unit = {}, onNavigateBack: () -> Unit = {}) {
    var formState by remember { mutableStateOf(AddItemFormState()) }

    Scaffold(
        topBar = { AddItemTopBar(onNavigateBack = onNavigateBack) }
    ) { paddingValues ->
        AddItemContent(
            formState = formState,
            onFormStateChange = { formState = it },
            onItemAdded = onItemAdded,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddItemTopBar(onNavigateBack: () -> Unit) {
    TopAppBar(
        title = { 
            Text(
                "Add New Item",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        windowInsets = WindowInsets(top = 10.dp)
    )
}

@Composable
private fun AddItemContent(
    formState: AddItemFormState,
    onFormStateChange: (AddItemFormState) -> Unit,
    onItemAdded: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item { PhotoSection() }
            item { BasicInformationSection(formState, onFormStateChange) }
            item { PricingAndInventorySection(formState, onFormStateChange) }
            item { AddItemButton(formState, onFormStateChange, onItemAdded) }
        }
    }
}

@Composable
private fun PhotoSection() {
    FormSection(title = "Item Photo") {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            ImageSelector()
        }
    }
}

@Composable
private fun BasicInformationSection(
    formState: AddItemFormState,
    onFormStateChange: (AddItemFormState) -> Unit
) {
    FormSection(title = "Basic Information") {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FormTextField(
                value = formState.name,
                onValueChange = { onFormStateChange(formState.copy(name = it)) },
                label = "Item Name",
                icon = Icons.Default.Style,
                isError = formState.name.isEmpty() && formState.hasErrors,
                helperText = "Enter a descriptive name for your item"
            )
            FormTextField(
                value = formState.category,
                onValueChange = { onFormStateChange(formState.copy(category = it)) },
                label = "Category",
                icon = Icons.Default.Category,
                isError = formState.category.isEmpty() && formState.hasErrors,
                helperText = "Select a category for your item"
            )
            FormTextField(
                value = formState.description,
                onValueChange = { onFormStateChange(formState.copy(description = it)) },
                label = "Description",
                icon = Icons.Default.Description,
                singleLine = false,
                maxLines = 5,
                helperText = "Provide details about your item"
            )
        }
    }
}

@Composable
private fun PricingAndInventorySection(
    formState: AddItemFormState,
    onFormStateChange: (AddItemFormState) -> Unit
) {
    FormSection(title = "Pricing & Inventory") {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FormTextField(
                value = formState.price,
                onValueChange = { onFormStateChange(formState.copy(price = it)) },
                label = "Price per day",
                icon = Icons.Default.MonetizationOn,
                keyboardType = KeyboardType.Number,
                isError = formState.price.isEmpty() && formState.hasErrors,
                helperText = "Enter the daily rental price"
            )
            FormTextField(
                value = formState.inStock,
                onValueChange = { onFormStateChange(formState.copy(inStock = it)) },
                label = "Available Items",
                icon = Icons.Default.Inventory2,
                keyboardType = KeyboardType.Number,
                isError = formState.inStock.isEmpty() && formState.hasErrors,
                helperText = "Number of items currently available"
            )
            FormTextField(
                value = formState.totalItems,
                onValueChange = { onFormStateChange(formState.copy(totalItems = it)) },
                label = "Total Items",
                icon = Icons.Default.Numbers,
                keyboardType = KeyboardType.Number,
                isError = formState.totalItems.isEmpty() && formState.hasErrors,
                helperText = "Total number of items in inventory"
            )
        }
    }
}

@Composable
private fun FormSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column {
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}

@Composable
private fun AddItemButton(
    formState: AddItemFormState,
    onFormStateChange: (AddItemFormState) -> Unit,
    onItemAdded: () -> Unit
) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                val hasErrors = formState.name.isEmpty() || 
                            formState.category.isEmpty() || 
                            formState.price.isEmpty()
                onFormStateChange(formState.copy(hasErrors = hasErrors))
                if (!hasErrors) onItemAdded()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                "Add Item",
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun ImageSelector() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clickable { /* TODO: Implement image picking */ },
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                Icons.Default.AddAPhoto,
                contentDescription = "Add Photo",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Upload Item Photo",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                "Add a clear photo of your item",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    isError: Boolean = false,
    helperText: String? = null
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            leadingIcon = { 
                Icon(
                    icon,
                    contentDescription = label,
                    tint = if (isError) MaterialTheme.colorScheme.error 
                           else MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = singleLine,
            maxLines = maxLines,
            shape = RoundedCornerShape(12.dp),
            isError = isError,
            supportingText = if (isError) {
                { Text("This field is required") }
            } else null
        )
        if (helperText != null && !isError) {
            Text(
                text = helperText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddItemScreenPreview() {
    MaterialTheme {
        AddItem()
    }
}
