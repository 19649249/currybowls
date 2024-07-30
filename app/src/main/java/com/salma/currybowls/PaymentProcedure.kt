
package com.salma.currybowls

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class PaymentActivity : ComponentActivity() {


}

@Composable
fun LabelTextFormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
        )
        androidx.compose.material.TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Enter $label") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewOfPaymentScreen() {
    PaymentActivity().apply {
        setContent {
            Surface {
                LabelTextFormField(
                    label = "Bank Name",
                    value = "",
                    onValueChange = {}
                )
            }
        }
    }
}
