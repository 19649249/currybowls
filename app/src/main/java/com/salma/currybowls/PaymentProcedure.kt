
package com.salma.currybowls

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salma.currybowls.components.HeadingTextComponent

import com.salma.currybowls.ui.theme.Primary

class PaymentActivity : ComponentActivity() {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val context = LocalContext.current
            val gradient45 = Brush.linearGradient(
                colors = listOf(Color.White, Color.White),
                start = Offset(0f, Float.POSITIVE_INFINITY),
                end = Offset(Float.POSITIVE_INFINITY, 0f)
            )

            Surface(
                modifier = Modifier
                    .background(gradient45)
                    .fillMaxSize()
                    .padding(28.dp)
            ) {

                Column(
                    modifier = Modifier.fillMaxSize().background(gradient45),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(50.dp))

                    HeadingTextComponent(value = "Payment Details")
                    Spacer(modifier = Modifier.height(15.dp))

                    var bankName by remember { mutableStateOf("") }
                    var cardNumber by remember { mutableStateOf("") }
                    var sortCode by remember { mutableStateOf("") }
                    var cvvNumber by remember { mutableStateOf("") }

                    LabelTextFormField(
                        label = "Bank Name",
                        value = bankName,
                        onValueChange = { bankName = it }
                    )

                    LabelTextFormField(
                        label = "Card Number",
                        value = cardNumber,
                        onValueChange = { cardNumber = it }
                    )

                    LabelTextFormField(
                        label = "Sort Code",
                        value = sortCode,
                        onValueChange = { sortCode = it }
                    )

                    LabelTextFormField(
                        label = "3 Digits CVV Number",
                        value = cvvNumber,
                        onValueChange = { cvvNumber = it }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        modifier = Modifier
                            .wrapContentWidth()
                            .heightIn(48.dp),
                        onClick = {
                            context.startActivity(Intent(context, CurryOrderSuccess::class.java))
                        },
                        contentPadding = PaddingValues(),
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                        shape = RoundedCornerShape(50.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(48.dp)
                                .background(
                                    brush = Brush.horizontalGradient(listOf(Primary, Primary)),
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Proceed",
                                fontSize = 18.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
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
