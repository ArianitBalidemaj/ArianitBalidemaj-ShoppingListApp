package com.example.arianitbalidemaj_shoppinglistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.arianitbalidemaj_shoppinglistapp.ui.theme.ArianitBalidemajShoppingListAppTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArianitBalidemajShoppingListAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShoppingListApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

data class ShoppingItem(val name: String, val quantity: String, val isChecked: Boolean)

@Composable
fun ShoppingListApp( modifier: Modifier = Modifier) {
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }
    val shoppingList = remember { mutableStateListOf<ShoppingItem>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Item Name") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = itemQuantity,
            onValueChange = { itemQuantity = it },
            label = { Text("Quantity") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                shoppingList.add(ShoppingItem(itemName, itemQuantity, false))
                itemName = ""
                itemQuantity = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Item")
        }

        LazyColumn {
            items(shoppingList.size) { index ->
                val item = shoppingList[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    ) {
                        Column {
                            Text(item.name)
                            Text("Quantity: ${item.quantity}")
                        }
                        Checkbox(
                            checked = item.isChecked,
                            onCheckedChange = { isChecked ->
                                shoppingList[index] = item.copy(isChecked = isChecked)
                            }
                        )
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArianitBalidemajShoppingListAppTheme {
        ShoppingListApp()
    }
}